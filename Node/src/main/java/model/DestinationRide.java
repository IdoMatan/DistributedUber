package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DestinationRide {
    public Map<String, List<Ride>> ridesByDate = Map.of();

    public void addNewRide(Ride ride) {
        List<Ride> ridesResult = (exists(ride.departureDate.toString())) ?
                this.ridesByDate.get(ride.departureDate.toString()) :
                new ArrayList<>();

        ridesResult.add(ride);

        this.ridesByDate.put(ride.departureDate.toString(), ridesResult);
    }

    private boolean exists(String dateStr) {
        return this.ridesByDate.containsKey(dateStr);
    }
}
