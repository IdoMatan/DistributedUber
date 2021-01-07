package model;

/** @author "IdoGlanzMatanWeks" 01/01/21 */
public class City {
    public int x;
    public int y;
    public String shard;
    public String name;

    public City(String name, String shard, int x, int y) {
        this.x = x;
        this.y = y;
        this.shard = shard;
    }
//    private final String city_name;
//    JSONObject location = new JSONObject();
//    private final String shard = "A";
//

//    public String get_name(){
//        return city_name;
//    }
//    public String getShard(){
//        return "/".concat(this.shard);
//    }
//    @Override
//    public String toString(){
//        return location.toString();
//    }
//    public City load_from_string(String city){
//        Object temp = JSONValue.parse(city);
//        JSONObject city_json = (JSONObject) temp;
//        return new City((String) city_json.get("name"), (int) city_json.get("x"), (int) city_json.get("y"));
//    }
}
