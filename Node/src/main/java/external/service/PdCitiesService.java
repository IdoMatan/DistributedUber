package external.service;

import api.ZkService;
import host.ReceiverService;
import host.controllers.Sender;
import host.dto.RideDto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import model.City;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PdCitiesService {
    @Autowired
    public ZkService zkService;
    @Autowired
    public ReceiverService receiverService;

    public void distributeNewRide(List<City> pdCities, RideDto ride){
        for(City city: pdCities){
            var pdCityLeaderIp = zkService.getLeaderNodeGRPChost(city.shard, city.name);
            var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("grpc.port");
            if (myFullURI.equals(pdCityLeaderIp)) {
//                var proto = ride.toProto();
//                UpdateNewRideMessage send_msg = UpdateNewRideMessage.newBuilder().setRide(proto).setAddressedTo(city.name).build();
//                ReceiverService.updatePDRide(send_msg);
                continue;
                }

            ManagedChannel channel = ManagedChannelBuilder.forTarget(pdCityLeaderIp).usePlaintext().build();
            Sender client = new Sender(channel);
            // Call server streaming call

            client.updatePDCities(ride, city.name);
        }
    }
}
