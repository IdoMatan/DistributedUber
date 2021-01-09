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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.stream.Collectors;

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
        var ride = departureRepository.addNew(rideDto);
        liveMapRepository.addNew(rideDto, rideDto.origin);

        List<String> followers = zkService.getFollowers(shard);
        var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");

        System.out.println("Waiting");
        var pdCities = (new PdCalculation(ride)).calculate();
        for(City c: pdCities){
            liveMapRepository.addPDRide(ride.buildUniqueKey(), ride.origin, c.name, ride.departureDate);
        }

        var pdCityNames = pdCities.stream().map(city -> city.name).collect(Collectors.toList());
        for (String target : followers) {
            if (!myFullURI.equals(target)) {
                String target_grpc = zkService.getZNodeData("/SHARDS/" + shard + "/liveNodes/" + target);
                ManagedChannel channel = ManagedChannelBuilder.forTarget(target_grpc).usePlaintext().build();
                Sender client = new Sender(channel);
                client.updateFollower(rideDto, rideDto.origin);
            }
        }
        pdCitiesService.distributeNewRide(pdCities, rideDto);

        return ResponseEntity.ok(LiveMapsDatabase.cityARides.toString());
    }


// ------------------------------------------------ Book passenger -----------------------------------------------------

    @PostMapping("/ride/book/single")
    public ResponseEntity<String> newPassenger(@RequestBody PassengerDto passengerDto) {
        //  create city object from the origin of the ride posted
        var originCity = citiesRepository.getCity(passengerDto.origin);
        // get the leader node of the relevant city
        var leaderNodeData = zkService.getLeaderNodeRESThost(originCity.shard, originCity.name);
        var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");
        if (!leaderNodeData.equals(myFullURI)) {
            // todo redirect to the leader server
        } else {
            // I am the leader, look for relevant ride in LiveMapDatabase
            var possibleRide = liveMapRepository.rideExists(passengerDto.origin, passengerDto.destination, passengerDto.departureDate);
            if (!possibleRide.isEmpty()) {
                // book first available ride
                for (String rideId : possibleRide) {
                    var bookedRide = departureRepository.book(passengerDto, rideId);
                    if (bookedRide != null) {
                        // todo gRPC method to update shard
                        return ResponseEntity.ok("Ride booked: " + bookedRide.toString());
                    }
                    ;
                }

            }
        }

        return ResponseEntity.ok("No available rides, try again later");
    }

    // ------------------------------------------------ Router/DNS --------------------------------------------------------
    @PostMapping("/new_ride")
    public ModelAndView redirectPostToPost(HttpServletRequest request) throws IOException {
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        RideDto rideDto = new Gson().fromJson(request.getReader(), RideDto.class);

        var originCity = citiesRepository.getCity(rideDto.origin);  // transform to city object
        var leaderNodeData = zkService.getLeaderNodeRESThost(originCity.shard, originCity.name); // get the REST ip of leader node of the relevant city
//        var leaderFullURI = "localhost" + ":" + System.getProperty("rest.port");

        String redirect = "redirect:http://" + leaderNodeData + "/redirected_new_ride"; // redirect to leader
//        String redirect =  "redirect:localhost:8083" + "/redirected_new_ride"; // redirect to leader

        return new ModelAndView(redirect);
    }
}
//
//    @GetMapping("/test")
//    public String testing() {
////        var zk = pdCitiesService.zkService;
////        String target = "localhost:8980";
////        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
////        Sender client = new Sender(channel);
//        // Call server streaming call
////        client.senderTest1(110, 0);
////        System.out.println(zk.getLeaderNodeGRPChost("A","cityA"));
//        return zk.getLeaderNodeGRPChost("A","cityA");
//    }
//}
