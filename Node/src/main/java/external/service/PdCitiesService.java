package external.service;

import api.ZkService;
import host.LocalRideDistributionService;
import host.controllers.Sender;
import host.dto.RideDto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdCitiesService {
    @Autowired
    private ZkService zkService;
    @Autowired
    private LocalRideDistributionService localRideDistributionService;

//    @PostConstruct
    public void distributeNewRide(List<City> pdCities, RideDto ride){
        for(City city: pdCities){
            var pdCityLeaderIp = zkService.getLeaderNodeGRPChost(city.shard, city.name);
            var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("grpc.port");
            if (myFullURI.equals(pdCityLeaderIp)) {
                localRideDistributionService.updatePDRide(ride, city.name);
                continue;
                }
            ManagedChannel channel = ManagedChannelBuilder.forTarget(pdCityLeaderIp).usePlaintext().build();
            Sender client = new Sender(channel);
            // Call server streaming call
            client.updatePDCities(ride, city.name);
        }
    }
}
