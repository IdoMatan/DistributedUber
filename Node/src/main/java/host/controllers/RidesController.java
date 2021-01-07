package host.controllers;

import api.ZkService;
import host.dto.RideDto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repository.CityRepository;
import repository.LiveMapRepository;

/**
 * @author "IdoGlanzMatanWeks" 01/01/21
 */
@RestController
public class RidesController {

    //    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private ZkService zkService;
    @Autowired
    private CityRepository citiesRepository = new CityRepository();
    @Autowired
    LiveMapRepository liveMapRepository;
    @Value("${my_city}")
    public String my_city;

    @Value(("$server.port"))
    public int port;

    @PostMapping(value = "/new_ride")
    public ResponseEntity<String> newRide(@RequestBody RideDto rideDto, RedirectAttributes attributes) {
        // create city object from the origin of the ride posted
        var originCity = citiesRepository.getCity(rideDto.origin);
        // get the leader node of the relevant city
        var leaderNodeData = zkService.getLeaderNodeData(originCity.shard);

        if (!leaderNodeData.equals("localhost:" + port)) {
            // todo redirect to the leader server
        } else {
            // case where I am the leader and thus need to add it to rides repo and update the other servers in shard
            // and also to all leaders of relevant cities (that ride is able to pick up from
            liveMapRepository.addNew(rideDto);
            // todo gRPC method add_ride_in_shard and method post_ride to leaders of other (relevant) cities
            // todo
        }
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
