package host.controllers;

import api.ZkService;
import com.google.gson.Gson;
import external.service.PdCitiesService;
import host.dto.PassengerDto;
import host.dto.RideDto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import model.City;
import model.LiveMapsDatabase;
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
import service.PdCalculation;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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
    @Value("${shard}")
    public String shard;

    @Value(("${server.port}"))
    public String restPort;
    @Value(("${grpc.port}"))
    public String grpcPort;

// ---------------------------------------------- Handle new ride ------------------------------------------------------

    @PostMapping(value = "/redirected_new_ride")
    public ResponseEntity<String> newRide(@RequestBody RideDto rideDto) {
        System.out.println(rideDto.toString());
        var ride = departureRepository.upsertRide(rideDto);
        liveMapRepository.addNew(rideDto, rideDto.origin);

        List<String> followers = zkService.getFollowers(shard);
        var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");

        System.out.println("Waiting");
        var pdCities = (new PdCalculation(ride)).calculate();
        for(City c: pdCities){
            liveMapRepository.addPDRide(ride.buildUniqueKey(), ride.origin, c.name, ride.departureDate);
        }

        updateCurrentCityFollowers(rideDto);
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
            if (rideOriginCity.equals("myCity")) {
                bookedRide = departureRepository.book(passengerDto, rideId);
                if (bookedRide != null) {
                    break;
                }
            }
        }
        // booked ride by local driver.
        if (bookedRide != null) {
            var dto = new RideDto(bookedRide);
            updateCurrentCityFollowers(dto);
        } else {
            // avishag todo: handle if no rides with origin = current city.
        }

        return ResponseEntity.ok("No available rides, try again later");
    }

    // ------------------------------------------------ Router/DNS --------------------------------------------------------
    private String parseOrigin(String rideId) {
        return rideId.split("_")[0];
    }

    private void updateCurrentCityFollowers(RideDto rideDto) {
        List<String> followers = zkService.getFollowers(shard);
        var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");

        for (String target : followers) {
            if (!myFullURI.equals(target)) {
                String target_grpc = zkService.getZNodeData("/SHARDS/" + shard + "/liveNodes/" + target);
                ManagedChannel channel = ManagedChannelBuilder.forTarget(target_grpc).usePlaintext().build();
                Sender client = new Sender(channel);
                // Call server streaming call
                client.updateFollower(rideDto, rideDto.origin);
            }
        }
    }

    // ------------------------------------------------ Router/DNS --------------------------------------------------------
    @GetMapping("/test")
    public String testing() {
        return "test";
    }

    @PostMapping("/new_ride")
    public ModelAndView redirectNewRidePostToPost(HttpServletRequest request) throws IOException {
        return redirectRequest(request, "new_ride");
    }

    @PostMapping("/ride/book/single")
    public ModelAndView redirectNewPassengerPostToPost(HttpServletRequest request) throws IOException {
        return redirectRequest(request, "new_passenger/single");
    }

    private ModelAndView redirectRequest(HttpServletRequest request, String redirectedRouteSuffix) throws IOException {
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        RideDto rideDto = new Gson().fromJson(request.getReader(), RideDto.class);

        var originCity = citiesRepository.getCity(rideDto.origin);  // transform to city object
        var leaderNodeData = zkService.getLeaderNodeRESThost(originCity.shard, originCity.name); // get the REST ip of leader node of the relevant city

        String redirect = "redirect:http://" + leaderNodeData + "/redirected_" + redirectedRouteSuffix; // redirect to leader

        return new ModelAndView(redirect);
    }
}
