package model;

import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CityRides {
    public Map<String, DestinationRide> ridesByDestination = new HashMap<>();

    public Set<String> getRideIds(String destination, String date) {
        if (!ridesByDestination.containsKey(destination)) {
            return Sets.newHashSet();
        }

        return ridesByDestination.get(destination).getRideIds(date);

    }

    public void addNewRideId(String rideId, String destination, String departureDate){
        DestinationRide destinationsRidesResult = (exists(destination)) ?
                this.ridesByDestination.get(destination) :
                new DestinationRide();

        destinationsRidesResult.addNewRideId(rideId, departureDate);
        this.ridesByDestination.put(destination, destinationsRidesResult);
    }

    private boolean exists(String destination) {
        return this.ridesByDestination.containsKey(destination);
    }
}
