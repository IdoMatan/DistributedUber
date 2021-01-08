package host.controllers;

import api.ZkService;
import com.google.gson.Gson;
import host.dto.PassengerDto;
import host.dto.RideDto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import model.LiveMapsDatabase;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author "IdoGlanzMatanWeks" 01/01/21
 */
@RestController
public class RidesController {

    //    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private ZkService zkService;
    @Autowired
    private CityRepository citiesRepository;
    @Autowired
    LiveMapRepository liveMapRepository;
    @Autowired
    DepartureRepository departureRepository;
    @Value("${city}")
    public String city;

    @Value(("${server.port}"))
    public String port;

    @PostMapping(value = "/new_ride")
    public ResponseEntity<String> newRide(@RequestBody RideDto rideDto) {
//        System.out.println(rideDto.toString());
//        //  create city object from the origin of the ride posted
//        var originCity = citiesRepository.getCity(rideDto.origin);
//        // get the leader node of the relevant city
//        var leaderNodeData = zkService.getLeaderNodeData(originCity.shard);
//
//        if (!leaderNodeData.equals(System.getProperty("server.ip") + ":" + port)) {
//            // todo redirect to the leader server
//        } else {
            // case where I am the leader and thus need to add it to rides repo and update the other servers in shard
            // and also to all leaders of relevant cities (that ride is able to pick up from
        departureRepository.addNew(rideDto);
        liveMapRepository.addNew(rideDto);

        // gRPC method add_ride_in_shard and method post_ride to leaders of other (relevant) cities
        String target = "localhost:8980";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
        Sender client = new Sender(channel);
        // Call server streaming call
        client.updateFollower(rideDto);
        System.out.println("Waiting");
        return "ok";

            // get shard ports.


            // todo
//        }

        // ============================================================================================================

//        ridesRepository.addNew(rideDto);
        // if I am the leader of the shard of the relevant city - handle it, otherwise:
        // find port or leader of relevant shard of city:
        // forward to him

//        String requestFrom = request.getHeader("request_from");
//        // todo decompose message and pass to next line origin city not host
//        String leader = zkService.getLeaderNodeData(my_city.getShard());
//        // todo add here redirection if needed
//
//        if (!isEmpty(requestFrom) && requestFrom.equalsIgnoreCase(leader)) {
//            // TODO add ride to leaderNodeData structure and broadcast to all relevant
//            return ResponseEntity.ok("SUCCESS");
//        }
//        // If I am leader I will broadcast leaderNodeData to all live node, else forward request to leader
//        if (amILeader()) {
//            List<String> shard_nodes = zkService.getShardNodes(my_city.getShard());
//            int successCount = 0;
//            for (String node : shard_nodes) {
//                // todo send by gRPC to all shard replicas
//                System.out.println(node);
//            }
//            return ResponseEntity.ok()
//                    .body("Successfully add ride");
//        } else {
//            // todo send to leader to update (by gRPC? or Redirect?)
//            return ResponseEntity.ok("Not the leader but its handled");
//        }
        return ResponseEntity.ok(LiveMapsDatabase.cityARides.toString());
    }
//
//    private boolean amILeader() {
//        String leader = zkService.getLeaderNodeData(my_city.getShard());
//        return getHostPostOfServer().equals(leader);
//    }
//
//    @GetMapping("/cities_served")
//    public ResponseEntity<List<String>> get_cities_served() {
//
//        return ResponseEntity.ok(zkService.getCities());
//    }

    @PostMapping("/ride/book/single")
    public ResponseEntity<String> newPassenger(@RequestBody PassengerDto passengerDto) {
        //  create city object from the origin of the ride posted
        var originCity = citiesRepository.getCity(passengerDto.origin);
        // get the leader node of the relevant city
        var leaderNodeData = zkService.getLeaderNodeData(originCity.shard);

        if (!leaderNodeData.equals(System.getProperty("server.ip") + ":" + port)) {
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


    @PostMapping("/new_ride_request")
    public ModelAndView redirectPostToPost(HttpServletRequest request) throws IOException {
        request.setAttribute(
                View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        RideDto rideDto = new Gson().fromJson(request.getReader(), RideDto.class);
        var originCity = citiesRepository.getCity(rideDto.origin);
            // get the leader node of the relevant city
        var leaderNodeData = zkService.getLeaderNodeData(originCity.shard);
        String redirect =  "redirect:http://localhost:"+"8081"+"/new_ride"; // todo redirect follow leader node
        return new ModelAndView(redirect);
    }

    @GetMapping("/test")
    public String testing() {
        String target = "localhost:8980";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
        Sender client = new Sender(channel);
        // Call server streaming call
        client.senderTest1(110, 0);
        System.out.println("Waiting");
        return "ok";
    }
}
