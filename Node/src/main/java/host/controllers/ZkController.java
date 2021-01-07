package host.controllers;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import model.City;
import api.ZkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static util.ZkConfig.getHostPostOfServer;
import static util.ZkConfig.isEmpty;

/** @author "IdoGlanzMatanWeks" 01/01/21 */
@RestController
public class ZkController {

    private RestTemplate restTemplate = new RestTemplate();
    private ZkService zkService;

    public City my_city = new City("A", 0, 0);

    @PutMapping("/ride/{name}")
    public ResponseEntity<String> savePerson(
            HttpServletRequest request,
            @PathVariable("name") String name) {

        String requestFrom = request.getHeader("request_from");
        // todo decompose message and pass to next line origin city not host
        String leader = zkService.getLeaderNodeData(my_city.getShard());
        // todo add here redirection if needed

        if (!isEmpty(requestFrom) && requestFrom.equalsIgnoreCase(leader)) {
            // TODO add ride to data structure and broadcast to all relevant
            return ResponseEntity.ok("SUCCESS");
        }
        // If I am leader I will broadcast data to all live node, else forward request to leader
        if (amILeader()) {
            List<String> shard_nodes = zkService.getShardNodes(my_city.getShard());
            int successCount = 0;
            for (String node : shard_nodes) {
                // todo send by gRPC to all shard replicas
                System.out.println(node);
            }
            return ResponseEntity.ok()
                    .body("Successfully add ride");
        } else {
            // todo send to leader to update (by gRPC? or Redirect?)
            return ResponseEntity.ok("Not the leader but its handled");
        }
    }

    private boolean amILeader() {
        String leader = zkService.getLeaderNodeData(my_city.getShard());
        return getHostPostOfServer().equals(leader);
    }

    @GetMapping("/cities_served")
    public ResponseEntity<List<String>> get_cities_served() {

        return ResponseEntity.ok(zkService.getCities());
    }

    @GetMapping("/test")
    public String testing() {
        String target = "localhost:8980";

        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
        Sender client = new Sender(channel);
        // Call server streaming call
        client.senderTest1(110,0);

        System.out.println("Waiting");
//

        return "ok";
    }

}
