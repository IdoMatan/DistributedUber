package repository;

import host.dto.PassengerDto;
import host.dto.RideDto;
import model.DeparturesDataBase;
import model.Passenger;
import model.Ride;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartureRepository {
    public Ride upsertRide(RideDto rideDto) {
        var ride = new Ride(
                rideDto.firstName,
                rideDto.lastName,
                rideDto.phoneNumber,
                rideDto.origin,
                rideDto.destination,
                rideDto.departureDate,
                rideDto.vacancies,
                rideDto.pd,
                rideDto.passengers == null ? new ArrayList<>() : rideDto.passengers.stream().map(Passenger::new).collect(Collectors.toList())
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
                rideDto.pd,
                rideDto.passengers == null ? new ArrayList<>() : rideDto.passengers.stream().map(Passenger::new).collect(Collectors.toList())
        );

        return getCollection(ride.origin).containsKey(ride.buildUniqueKey());
    }

    public Ride book(PassengerDto passengerDto, String ridId) {
        var ps = new Passenger(passengerDto);
        Ride ride = getCollection(parseOrigin(ridId)).get(ridId);
        if (ride.available()) {
            ride.book(ps);

            return ride;
        } else return null;

    }

    private String parseOrigin(String rideId) {
        return rideId.split("_")[0];
    }

    public Ride unBook(PassengerDto passengerDto, String rideId){
        var ps = new Passenger(passengerDto);
        Ride ride = getCollection(passengerDto.origin).get(rideId);
        if (rideId.equals("NA")) {
            return ride;
        }
        ride.unBook(ps);
        return ride;
    }

    private Map<String, Ride> getCollection(String origin) {
        return switch (origin) {
            case "cityA" -> DeparturesDataBase.cityADepartures;
            case "cityB" -> DeparturesDataBase.cityBDepartures;
            case "cityC" -> DeparturesDataBase.cityCDepartures;
            default -> throw new IllegalArgumentException("Missing " + origin);
        };
    }
}
