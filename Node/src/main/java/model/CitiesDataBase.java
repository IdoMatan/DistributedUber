package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitiesDataBase {
    public static Map<String, City> cities = setup();

    private static Map<String, City> setup() {
        var em = new HashMap<String, City>();
        em.put("cityA", new City("cityA", "A", 0, 0));
        em.put("cityB", new City("cityB", "A", 1, 2));
        em.put("cityC", new City("cityC", "A", 30, 30));
        em.put("cityD", new City("cityD", "B", 100, 60));
        em.put("cityE", new City("cityE", "B", 300, 300));
        em.put("cityF", new City("cityF", "B", 100, 100));
        return em;
    }

    public static List<String> getCitiesName(){
        List<String> cities = null;
        cities.add("cityA");
        cities.add("cityB");
        cities.add("cityC");
        cities.add("cityD");
        cities.add("cityE");
        cities.add("cityF");
        return cities;
    }
}
