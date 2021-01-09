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

    public void addNewRideId(String rideId, String departureDate){
        List<String> ridesResult = (exists(departureDate)) ?
                this.ridesByDate.get(departureDate) :
                new ArrayList<>();

        ridesResult.add(rideId);

        this.ridesByDate.put(departureDate, ridesResult);
    }

    private boolean exists(String dateStr) {
        return this.ridesByDate.containsKey(dateStr);
    }
}
