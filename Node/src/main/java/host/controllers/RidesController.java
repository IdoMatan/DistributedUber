package host.controllers;

import host.dto.RideDto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import repository.CityRepository;
import repository.RidesRepository;

/**
 * @author "IdoGlanzMatanWeks" 01/01/21
 */
@RestController
public class RidesController {

//    private RestTemplate restTemplate = new RestTemplate();
//    @Autowired
//    private ZkService zkService;
    @Autowired
    private CityRepository citiesRepository = new CityRepository();
    @Autowired
    RidesRepository ridesRepository;
    @Value("${my_city}")
    public static String my_city;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Not the leader but its handled");
    }

    @PostMapping(value ="/new_ride")
    public ResponseEntity<String> newRide(@RequestBody RideDto rideDto) {
        var myCity = citiesRepository.getCity(my_city);
        if myCity.
        rideDto.origin
//        ridesRepository.addNew(rideDto);
        // todo parse request body json
        // if I am the leader of the shard of the relevant city - handle it, otherwise:
        // find port or leader of relevant shard of city:
        // forward to him
        // todo h

//        String requestFrom = request.getHeader("request_from");
//        // todo decompose message and pass to next line origin city not host
//        String leader = zkService.getLeaderNodeData(my_city.getShard());
//        // todo add here redirection if needed
//
//        if (!isEmpty(requestFrom) && requestFrom.equalsIgnoreCase(leader)) {
//            // TODO add ride to data structure and broadcast to all relevant
//            return ResponseEntity.ok("SUCCESS");
//        }
//        // If I am leader I will broadcast data to all live node, else forward request to leader
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
        return ResponseEntity.ok("Not the leader but its handled");
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

    @GetMapping("/test")
    public String testing() {
        String target = "localhost:8980";

        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
        Sender client = new Sender(channel);
        // Call server streaming call
        client.senderTest1(110, 0);

        System.out.println("Waiting");
//

        return "ok";
    }

}
