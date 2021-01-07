package model;

import java.util.*;

public class DestinationRide {
    public Map<String, List<Ride>> ridesByDate = new HashMap<>();

    public void addNewRide(Ride ride) {
        List<Ride> ridesResult = (exists(ride.departureDate)) ?
                this.ridesByDate.get(ride.departureDate) :
                new ArrayList<>();

        ridesResult.add(ride);

        this.ridesByDate.put(ride.departureDate, ridesResult);
    }

    private boolean exists(String dateStr) {
        return this.ridesByDate.containsKey(dateStr);
    }
}
