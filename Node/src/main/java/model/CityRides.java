package model;

import java.util.HashMap;
import java.util.Map;

public class CityRides {
    public Map<String, DestinationRide> ridesByDestination = new HashMap<>();


    public void addNewRide(Ride ride) {
        DestinationRide destinationsRidesResult = (exists(ride.destination)) ?
                this.ridesByDestination.get(ride.destination) :
                new DestinationRide();

        destinationsRidesResult.addNewRide(ride);
        this.ridesByDestination.put(ride.destination, destinationsRidesResult);
    }

    private boolean exists(String destination) {
        return this.ridesByDestination.containsKey(destination);
    }
}
