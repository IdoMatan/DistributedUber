package model;

import java.util.*;

public class DestinationRide {
    public Map<String, List<String>> ridesByDate = new HashMap<>();

    public List<String> getRideIds(String date) {
        if (!ridesByDate.containsKey(date)) {
            return Collections.emptyList();
        }

        return ridesByDate.get(date);
    }

    public void addNewRideId(Ride ride) {
        List<String> ridesResult = (exists(ride.departureDate)) ?
                this.ridesByDate.get(ride.departureDate) :
                new ArrayList<>();

        ridesResult.add(ride.buildUniqueKey());

        this.ridesByDate.put(ride.departureDate, ridesResult);
    }

    private boolean exists(String dateStr) {
        return this.ridesByDate.containsKey(dateStr);
    }
}
