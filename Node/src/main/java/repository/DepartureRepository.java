package repository;

import host.dto.RideDto;
import model.DeparturesDataBase;
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


    private Map<String, Ride> getCollection(String origin) {
        return switch (origin) {
            case "cityA" -> DeparturesDataBase.cityADepartures;
            case "cityB" -> DeparturesDataBase.cityBDepartures;
            default -> throw new IllegalArgumentException("Missing " + origin);
        };
    }
}
