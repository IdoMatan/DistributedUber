package host.controllers;

import api.ZkService;
import com.google.gson.Gson;
import external.service.PathPlaningService;
import external.service.PdCitiesService;
import generated.BookResult;
import generated.IsEmptyAgreement;
import host.dto.PassengerDto;
import host.dto.PassengerPathDto;
import host.dto.RideDto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import model.City;
import model.LiveMapsDatabase;
import model.Passenger;
import model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import repository.CityRepository;
import repository.DepartureRepository;
import repository.LiveMapRepository;
import repository.PassengersRepository;
import service.BookingService;
import service.PdCalculation;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static model.CitiesDataBase.cities;

/**
 * @author "IdoGlanzMatanWeks" 01/01/21
 */
@RestController
public class RidesController {

    // private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private ZkService zkService;

    @Autowired
    private CityRepository citiesRepository;
    @Autowired
    LiveMapRepository liveMapRepository;
    @Autowired
    DepartureRepository departureRepository;
    @Autowired
    PdCitiesService pdCitiesService;
    @Autowired
    PathPlaningService pathPlanning;
    @Autowired
    PassengersRepository passengersRepository;
    @Autowired
    BookingService bookingService;

    @Value("${shard}")
    public String shard;

    @Value(("${server.port}"))
    public String restPort;
    @Value(("${grpc.port}"))
    public String grpcPort;


    @GetMapping(value = "/snapshot")
    public ResponseEntity<String> snapshot() {
        StringBuilder dpSnapshot = new StringBuilder("Departure list: \n");
        StringBuilder psSnapshot = new StringBuilder("Passenger list: \n");
        for (String city : cities.keySet()) {
            psSnapshot.append(passengersRepository.getSnapshot(city)).append("\n");
            dpSnapshot.append(departureRepository.getSnapshot(city)).append("\n");
        }
        return ResponseEntity.ok(psSnapshot.toString() + "\n" + dpSnapshot.toString());
    }
// ---------------------------------------------- Handle new ride ------------------------------------------------------

    @PostMapping(value = "/redirected_new_ride")
    public ResponseEntity<String> newRide(@RequestBody RideDto rideDto) {
        System.out.println(rideDto.toString());
        var ride = departureRepository.upsertRide(rideDto);
        liveMapRepository.upsert(rideDto, rideDto.origin);
        zkService.updateLiveRidesSync(shard, ride.origin, String.valueOf(departureRepository.getSize(ride.origin)));

        var pdCities = (new PdCalculation(ride)).calculate();
        for (City c : pdCities) {
            liveMapRepository.addPDRide(ride.buildUniqueKey(), ride.origin, c.name, ride.departureDate);
        }

        updateCurrentCityFollowers(rideDto, null);
        pdCitiesService.distributeNewRide(pdCities, rideDto);

        return ResponseEntity.ok(LiveMapsDatabase.cityARides.toString());
    }

// ------------------------------------------------ Book passenger -----------------------------------------------------

    @PostMapping("/redirected_new_passenger/single")
    public ResponseEntity<String> newPassenger(@RequestBody PassengerDto passengerDto) {
        var optionalRides = liveMapRepository.rideExists(passengerDto.origin, passengerDto.destination, passengerDto.departureDate);
        if (optionalRides.isEmpty()) {
            return ResponseEntity.ok("No available rides, try again later\nthanks,\nAvishag");
        }
        Ride bookedRide = null;
        for (String rideId : optionalRides) {
            var rideOriginCity = parseOrigin(rideId);
            if (rideOriginCity.equals(passengerDto.origin)) {
                bookedRide = bookingService.book(passengerDto, rideId);
                if (bookedRide != null) {
                    break;
                }
            }
        }
        // booked ride by local driver.
        if (bookedRide != null) {
            var dto = new RideDto(bookedRide);
            updateCurrentCityFollowers(dto, new Passenger(passengerDto));
            return ResponseEntity.ok("You booked local ride");

        } else {
            var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("grpc.port");

            for (String rideId : optionalRides) {
                var rideOriginCity = parseOrigin(rideId);
                if (rideOriginCity.equals(passengerDto.origin)) {
                    continue;
                }
                var city = citiesRepository.getCity(rideOriginCity);
                var cityLeaderIp = zkService.getLeaderNodeGRPChost(city.shard, city.name);
                if (cityLeaderIp.equals(myFullURI)) {  // Im also the leader of that city
                    bookedRide = bookingService.book(passengerDto, rideId);
                    if (bookedRide != null) {
                        var dto = new RideDto(bookedRide);
                        updateCurrentCityFollowers(dto, new Passenger(passengerDto));
                        return ResponseEntity.ok("You booked an in shard ride");
                    }
                }
                ManagedChannel channel = ManagedChannelBuilder.forTarget(cityLeaderIp).usePlaintext().build();
                Sender client = new Sender(channel);
                BookResult bookResult = client.bookRide(new Passenger(passengerDto), rideId);
                channel.shutdown();

                if (bookResult.getSucceededToBook()) {
                    Passenger ps = new Passenger(passengerDto);
                    ps.UpdateRideId(rideId);
                    bookingService.book(ps);
                    return ResponseEntity.ok("You booked a ride originated in " + rideOriginCity);
                }
            }
            // todo: handle if no rides with origin = current city.
        }

        return ResponseEntity.ok("No available rides, try again later");
    }


    @PostMapping("/redirected_new_passenger/path_planning")
    public ResponseEntity<String> newPassengerPath(@RequestBody PassengerPathDto passengerPathDto) {
        // todo: get origin, destination as a list format
        String booked_path_planing = pathPlanning.bookRides(passengerPathDto);
        if (booked_path_planing.equals("booked")) {
            return ResponseEntity.ok("enjoy your trip");
        } else {
            return ResponseEntity.ok("No available rides, try again later");
        }
    }

    @PostMapping("/redirected_quick_check/response")
    public ResponseEntity<String> quick_check_response(@RequestBody PassengerDto passengerPathDto) {
        return ResponseEntity.ok("No available rides, try again later");
    }


    // ------------------------------------------------ Router/DNS --------------------------------------------------------
    public String parseOrigin(String rideId) {
        return rideId.split("_")[0];
    }

    private void updateCurrentCityFollowers(RideDto rideDto, Passenger passenger) {
        List<String> followers = zkService.getFollowers(shard);
        var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");

        for (String target : followers) {
            if (!myFullURI.equals(target)) {
                String target_grpc = zkService.getZNodeData("/SHARDS/" + shard + "/liveNodes/" + target);
                ManagedChannel channel = ManagedChannelBuilder.forTarget(target_grpc).usePlaintext().build();
                Sender client = new Sender(channel);
                // Call server streaming call
                if (passenger != null) {
                    client.updateFollower(rideDto, rideDto.origin, passenger);
                } else {
                    client.updateFollower(rideDto, rideDto.origin);
                }
//                client.updateFollowersPassengerList(passenger);
                channel.shutdown();

            }
        }
    }

    // ------------------------------------------------ Router/DNS --------------------------------------------------------
    @GetMapping("/test")
    public String testing() throws InterruptedException {
//        String syncCount = String.valueOf(departureRepository.getSize("cityA"));
//        String nRides = zkService.getLiveRidesSync(shard, "cityA");
//        return "from ZK: " + nRides + "\n from me: " + syncCount;

        int counter = 0;
        List<String> followers = zkService.getFollowers(shard);
        var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");

        for (String target : followers) {
            if (!myFullURI.equals(target)) {
                String target_grpc = zkService.getZNodeData("/SHARDS/" + shard + "/liveNodes/" + target);
                ManagedChannel channel = ManagedChannelBuilder.forTarget(target_grpc).usePlaintext().build();
                Sender client = new Sender(channel);
                IsEmptyAgreement isEmpty = client.liveMapIsEmpty("cityA", "cityB", "2021-1-1");

                if (!isEmpty.getIsEmpty()) {
                    counter++;
                    System.out.println(target_grpc);
                }
            }
            System.out.println(counter);
        }
        System.out.println(counter);
        return "1";
    }

    @PostMapping("/new_ride")
    public ModelAndView redirectNewRidePostToPost(HttpServletRequest request) throws IOException {
        return redirectRequest(request, "new_ride");
    }

    @PostMapping("/ride/book/single")
    public ModelAndView redirectNewPassengerPostToPost(HttpServletRequest request) throws IOException {
        return redirectRequest(request, "new_passenger/single");
    }

    @PostMapping("/ride/book/path_planning")
    public ModelAndView redirectNewPassengerTripPostToPost(HttpServletRequest request) throws IOException {
        return redirectRequest(request, "new_passenger/path_planning");
    }


    private ModelAndView redirectRequest(HttpServletRequest request, String redirectedRouteSuffix) throws
            IOException {
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        String origin;
        String destination = null;
        String departureDate = null;
        if (redirectedRouteSuffix.equals("new_passenger/path_planning")) {
            origin = (new Gson().fromJson(request.getReader(), PassengerPathDto.class)).origin.get(0);
        } else {
            RideDto json_request = (new Gson().fromJson(request.getReader(), RideDto.class));
            origin = json_request.origin;
            destination = json_request.destination;
            departureDate = json_request.departureDate;
        }
//            if (redirectedRouteSuffix.equals("new_passenger/single") && shard.equals(citiesRepository.getCity(origin).shard)) {
//                if (!quick_check_available(origin, destination, departureDate)) {
//                    redirectedRouteSuffix = "quick_check/response";
//                    String redirect = "redirect:http://" + System.getProperty("myIP") + "/redirected_" + redirectedRouteSuffix; // redirect to leader
//                    return new ModelAndView(redirect);
//                }
//            }

        var originCity = citiesRepository.getCity(origin);  // transform to city object
        var leaderNodeData = zkService.getLeaderNodeRESThost(originCity.shard, originCity.name); // get the REST ip of leader node of the relevant city

        String redirect = "redirect:http://" + leaderNodeData + "/redirected_" + redirectedRouteSuffix; // redirect to leader

        return new ModelAndView(redirect);
    }

    private boolean quick_check_available(String origin, String destination, String departureDate) {
//        String nRides = zkService.getLiveRidesSync(shard, origin);
        var optionalRides = liveMapRepository.rideExists(origin, destination, departureDate);
        if (optionalRides.isEmpty()) {
            // ask my follower friends if they agree with me:
            List<String> followers = zkService.getFollowers(shard);
            var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");

            for (String target : followers) {
                if (!myFullURI.equals(target)) {
                    String target_grpc = zkService.getZNodeData("/SHARDS/" + shard + "/liveNodes/" + target);
                    ManagedChannel channel = ManagedChannelBuilder.forTarget(target_grpc).usePlaintext().build();
                    Sender client = new Sender(channel);
                    // Call server streaming call

                    IsEmptyAgreement isEmpty = client.liveMapIsEmpty(origin, destination, departureDate);
                    if (!isEmpty.getIsEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
