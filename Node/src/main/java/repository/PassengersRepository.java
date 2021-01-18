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

    public void removePassenger(Passenger ps) {
        getCollection(ps.origin).remove(ps);
    }

    public String getSnapshot(String currentCity) {
        StringBuilder snapshot = new StringBuilder("Passengers of " + currentCity + " :\n");
        var passengers = getCollection(currentCity);
        var index = 0;
        if (passengers == null){return snapshot.toString();}
        for(Passenger ps: passengers){
            snapshot.append(++index).append(". ").append(ps.toString()).append("\n");
        }
        return snapshot.toString();
    }
    private List<Passenger> getCollection(String origin) {
        return switch (origin) {
            case "cityA" -> PassengersDatabase.cityAPassengers;
            case "cityB" -> PassengersDatabase.cityBPassengers;
            case "cityC" ->  PassengersDatabase.cityCPassengers;
            case "cityD" -> PassengersDatabase.cityDPassengers;
            case "cityE" -> PassengersDatabase.cityEPassengers;
            case "cityF" -> PassengersDatabase.cityFPassengers;
            default -> throw new IllegalArgumentException("Missing " + origin);
        };
    }

}
