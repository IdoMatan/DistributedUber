package model;

import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DestinationRide {
    public Map<String, Set<String>> ridesByDate = new HashMap<>();

    public Set<String> getRideIds(String date) {
        if (!ridesByDate.containsKey(date)) {
            return Sets.newHashSet();
        }

        return ridesByDate.get(date);
    }

    public void addNewRideId(Ride ride) {
        Set<String> ridesResult = (exists(ride.departureDate)) ?
                this.ridesByDate.get(ride.departureDate) :
                Sets.newHashSet();

        ridesResult.add(ride.buildUniqueKey());

        this.ridesByDate.put(ride.departureDate, ridesResult);
    }

    private boolean exists(String dateStr) {
        return this.ridesByDate.containsKey(dateStr);
    }
}
