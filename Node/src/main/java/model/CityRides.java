package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityRides {
    public Map<String, DestinationRide> ridesByDestination = new HashMap<>();

//    @Override
//    public String toString() {
//
//    }
    public List<String> getRideIds(String destination, String date) {
        if (!ridesByDestination.containsKey(destination)) {
            return Collections.emptyList();
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
