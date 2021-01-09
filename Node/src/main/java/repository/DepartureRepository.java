package repository;

import host.dto.PassengerDto;
import host.dto.RideDto;
import model.DeparturesDataBase;
import model.Passenger;
import model.Ride;

import java.util.Map;

public class DepartureRepository {
    public Ride addNew(RideDto rideDto) {
        var ride = new Ride(
                rideDto.firstName,
                rideDto.lastName,
                rideDto.phoneNumber,
                rideDto.origin,
                rideDto.destination,
                rideDto.departureDate,
                rideDto.vacancies,
                rideDto.pd
        );

        getCollection(ride.origin).put(ride.buildUniqueKey(), ride);

        return ride;
    }

    public Boolean exists(RideDto rideDto) {
        var ride = new Ride(
                rideDto.firstName,
                rideDto.lastName,
                rideDto.phoneNumber,
                rideDto.origin,
                rideDto.destination,
                rideDto.departureDate,
                rideDto.vacancies,
                rideDto.pd
        );

        return getCollection(ride.origin).containsKey(ride.buildUniqueKey());
    }

    public Ride book(PassengerDto passengerDto, String ridId) {
        var ps = new Passenger(passengerDto);
        Ride ride = getCollection(passengerDto.origin).get(ridId);
        if (ride.available()) {
            ride.book(ps);
            return ride;
        } else return null;

    }

    private Map<String, Ride> getCollection(String origin) {
        return switch (origin) {
            case "cityA" -> DeparturesDataBase.cityADepartures;
            case "cityB" -> DeparturesDataBase.cityBDepartures;
            default -> throw new IllegalArgumentException("Missing " + origin);
        };
    }
}
