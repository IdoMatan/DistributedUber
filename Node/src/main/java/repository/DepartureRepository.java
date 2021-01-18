package repository;

import host.dto.PassengerDto;
import host.dto.RideDto;
import model.DeparturesDataBase;
import model.Passenger;
import model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class DepartureRepository {
    @Autowired
    PassengersRepository passengersRepository;

    public String getSnapshot(String currentCity) {
        StringBuilder snapshot = new StringBuilder("Rides of " + currentCity + " :\n");
        var rides = getCollection(currentCity).values();
        var index = 0;
        if (rides == null) {
            return snapshot.toString();
        }
        for (Ride r : rides) {
            snapshot.append(++index).append(". ").append(r.toString()).append("\n");
        }

        return snapshot.toString();
    }

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
            ps.UpdateRideId(ride.buildUniqueKey());
            if (!ride.passengerExist(ps)) {
                ride.book(ps);
                passengersRepository.addNewPassenger(ps);
                return ride;
            }
        }
        return null;
    }

    private String parseOrigin(String rideId) {
        return rideId.split("_")[0];
    }

    public Ride unBook(PassengerDto passengerDto, String rideId) {
        var ps = new Passenger(passengerDto);
        Ride ride = getCollection(passengerDto.origin).get(rideId);
        if (rideId.equals("NA")) {
            return ride;
        }
        ps.UpdateRideId(rideId);
        ride.unBook(ps);
        passengersRepository.removePassenger(ps);
        return ride;
    }

    private Map<String, Ride> getCollection(String origin) {
        return switch (origin) {
            case "cityA" -> DeparturesDataBase.cityADepartures;
            case "cityB" -> DeparturesDataBase.cityBDepartures;
            case "cityC" -> DeparturesDataBase.cityCDepartures;
            case "cityD" -> DeparturesDataBase.cityDDepartures;
            case "cityE" -> DeparturesDataBase.cityEDepartures;
            case "cityF" -> DeparturesDataBase.cityFDepartures;
            default -> throw new IllegalArgumentException("Missing " + origin);
        };
    }

    public int getSize(String origin) {
        return switch (origin) {
            case "cityA" -> DeparturesDataBase.cityADepartures.size();
            case "cityB" -> DeparturesDataBase.cityBDepartures.size();
            case "cityC" -> DeparturesDataBase.cityCDepartures.size();
            case "cityD" -> DeparturesDataBase.cityDDepartures.size();
            case "cityE" -> DeparturesDataBase.cityEDepartures.size();
            case "cityF" -> DeparturesDataBase.cityFDepartures.size();
            default -> throw new IllegalArgumentException("Missing " + origin);

        };
    }
}
