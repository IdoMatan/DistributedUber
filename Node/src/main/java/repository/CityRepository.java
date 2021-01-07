package repository;

import model.CitiesDataBase;
import model.City;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepository {
    public City getCity(String name) {
        return CitiesDataBase.cities.get(name);

    }
}
