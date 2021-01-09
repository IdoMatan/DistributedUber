package host;

import api.ZkService;
import host.controllers.Sender;
import host.dto.RideDto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DepartureRepository;
import repository.LiveMapRepository;

import java.util.List;

@Service
public class LocalRideDistributionService {
    @Autowired
    private ZkService zkService;
    @Autowired
    private DepartureRepository departureRepository;
    @Autowired
    private LiveMapRepository liveMapRepository;

    public void updatePDRide(RideDto dto, String addressedTo){
        var ride = liveMapRepository.addNew(dto, addressedTo);
        String shard = System.getProperty("shard");

        List<String> followers = zkService.getFollowers(shard);
        var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");
        for (String target : followers) {
            if (!myFullURI.equals(target)) {
                String target_grpc = zkService.getZNodeData("/SHARDS/" + shard + "/liveNodes/" + target);
                ManagedChannel channel = ManagedChannelBuilder.forTarget(target_grpc).usePlaintext().build();
                Sender client = new Sender(channel);
                // Call server streaming call
                client.updateFollower(dto, addressedTo);
            }
        }
    }
}
