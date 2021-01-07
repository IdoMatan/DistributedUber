package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitiesDataBase {
    public static Map<String, City> cities = getAllCities();

    private static Map<String, City> getAllCities() {
        return List<City>(
                new City(0,0, "A"),
                )
//        { "cityA": {"x": 0,"y": 0, "shard": "A"},
//            "cityB": {"x": 10,"y": 20, "shard": "A"},
//            "cityC": {"x": 30,"y": 30, "shard": "A"},
//            "cityD": {"x": 100,"y": 60, "shard": "B"},
//            "cityE": {"x": 300,"y": 300, "shard": "B"},
//            "cityF": {"x": 100,"y": 100, "shard": "B"}
//        }
//        File file;
//        {
//            try {
//                file = ResourceUtils.getFile("resources/json/CitiesMap");
//                Object temp = JSONValue.parse(file.toString());
//                JSONObject CitiesMap = (JSONObject) temp;
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
        return new HashMap<>();
    }
}
