package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.simple.JSONObject;

/** @author "IdoGlanzMatanWeks" 01/01/21 */
@Getter
@AllArgsConstructor
public class City {
    private final String city_name;
    JSONObject location = new JSONObject();
    private final String shard = "A";

    public City(String city, int x, int y) {
        this.city_name = city;
        this.location.put("name",city);
        this.location.put("shard",shard);
        this.location.put("x",x);
        this.location.put("y",y);
    }
    public String get_name(){
        return city_name;
    }
    public String getShard(){
        return "/".concat(this.shard);
    }
    @Override
    public String toString(){
        return location.toString();
    }
//    public City load_from_string(String city){
//        Object temp = JSONValue.parse(city);
//        JSONObject city_json = (JSONObject) temp;
//        return new City((String) city_json.get("name"), (int) city_json.get("x"), (int) city_json.get("y"));
//    }
}
