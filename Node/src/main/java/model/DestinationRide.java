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

    public void upsertRideId(String rideId, String departureDate){
        Set<String> ridesResult = (exists(departureDate)) ?
                this.ridesByDate.get(departureDate) :
                Sets.newHashSet();


        ridesResult.add(rideId);

        this.ridesByDate.put(departureDate, ridesResult);
    }

    private boolean exists(String dateStr) {
        return this.ridesByDate.containsKey(dateStr);
    }
}
