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

    public void addNewRideId(Ride ride) {
        DestinationRide destinationsRidesResult = (exists(ride.destination)) ?
                this.ridesByDestination.get(ride.destination) :
                new DestinationRide();

        destinationsRidesResult.addNewRideId(ride);
        this.ridesByDestination.put(ride.destination, destinationsRidesResult);
    }

    private boolean exists(String destination) {
        return this.ridesByDestination.containsKey(destination);
    }
}
