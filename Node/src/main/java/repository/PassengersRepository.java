package repository;

import model.Passenger;
import model.PassengersDatabase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PassengersRepository {
    public void addNewPassenger(Passenger ps) {
        getCollection(ps.origin).add(ps);
    }

    private List<Passenger> getCollection(String origin) {
        return switch (origin) {
            case "cityA" -> PassengersDatabase.cityAPassengers;
            case "cityB" -> PassengersDatabase.cityBPassengers;
            case "cityC" ->  PassengersDatabase.cityCPassengers;
            case "cityD" -> PassengersDatabase.cityDPassengers;
            default -> throw new IllegalArgumentException("Missing " + origin);
        };
    }

}
