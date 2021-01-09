package repository;

import host.dto.RideDto;
import model.CityRides;
import model.LiveMapsDatabase;
import model.Passenger;
import model.Ride;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class LiveMapRepository {
    public Ride addNew(RideDto rideDto, String cityToAddTo) {
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

        getCollection(cityToAddTo).addNewRideId( ride.buildUniqueKey(), ride.destination,ride.departureDate);
        return ride;
    }

    public void addPDRide(String rideId, String myCity, String dest, String date){
        getCollection(myCity).addNewRideId( rideId, dest,date);
    }

    public Set<String> rideExists(String origin, String destination, String departureDate) {
        return getCollection(origin).getRideIds(destination, departureDate);
    }

    private CityRides getCollection(String origin) {
        return switch (origin) {
            case "cityA" -> LiveMapsDatabase.cityARides;
            case "cityB" -> LiveMapsDatabase.cityBRides;
            case "cityC" -> LiveMapsDatabase.cityCRides;
            case "cityD" -> LiveMapsDatabase.cityDRides;
            default -> throw new IllegalArgumentException("Missing " + origin);
        };
    }


//
//
//    public LinkedList<String> relevant_for(List<Integer> origin, List<Integer> destination) {
//        List<String> all_cities = zkService.getCities();
//        LinkedList<String> relevant_cities = new LinkedList<String>();
//        for (String other_city : all_cities) {
//            Object temp = JSONValue.parse(other_city);
//            JSONObject city_json = (JSONObject) temp;
//            int city_x = (int) city_json.get("x");
//            int city_y = (int) city_json.get("y");
//            int P1x = origin.get(0);
//            int P1y = origin.get(1);
//            int P2x = destination.get(0);
//            int P2y = destination.get(1);
//
//            float numerator = Math.abs(Math.multiplyExact((P2x - P1x), (P1y - city_y)) - Math.multiplyExact((P1x - city_x), (P2y - P1y)));
//            float denominator = (float) Math.sqrt(Math.pow((P2x - P1x), 2) + Math.pow((P2y - P1y), 2));
//
//            if (denominator != 0) {
//                float distance = numerator / denominator;
//                if (distance <= this.pd) {
//                    relevant_cities.add((String) city_json.get("name"));
//                }
//            }
//        }
//        return relevant_cities;
//    }

}
