package repository;

import model.City;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static model.CitiesDataBase.cities;

@Repository
public class CityRepository {
    public City getCity(String name) {
        return cities.get(name);
    }

    public List<String> getShardCities(String shard) {
        List<String> shardCities = new ArrayList();

        for (City c : cities.values()) {
            if (c.shard.equals(shard)) {
                shardCities.add(c.name);
            }
        }
        return shardCities;
    }
}
