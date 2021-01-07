package model;

import api.ZkService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/** @author "IdoGlanzMatanWeks" 01/01/21 */
@Getter
@AllArgsConstructor
public class Ride {
    private final String First_name;
    private final String Last_name;
    private final String Phone_number;
    private final String Origin;
    private final String Destination;
    private final Date Departure_date;
    private int Vacancies;
    private final float PD;
    @Autowired private ZkService zkService;

    public Ride(String first, String last, String phone, String origin, String dest, Date departure_date, int seats, float pd){
        this.First_name = first;
        this.Last_name = last;
        this.Phone_number = phone;
        this.Origin = origin;
        this.Destination = dest;
        this.Departure_date = departure_date;
        this.Vacancies = seats;
        this.PD = pd;
    }
    public int updateSeats() {
        this.Vacancies--;
        return this.Vacancies;
    }
    public LinkedList<String> relevant_for(List<Integer> origin,List<Integer> destination){
        List<String> all_cities = zkService.getCities();
        LinkedList<String> relevant_cities = new LinkedList<String>();
        for (String other_city: all_cities){
            Object temp = JSONValue.parse(other_city);
            JSONObject city_json = (JSONObject) temp;
            int city_x = (int)city_json.get("x");
            int city_y = (int)city_json.get("y");
            int P1x = origin.get(0);
            int P1y = origin.get(1);
            int P2x = destination.get(0);
            int P2y = destination.get(1);

            float numerator = Math.abs(Math.multiplyExact((P2x-P1x),(P1y-city_y)) - Math.multiplyExact((P1x-city_x),(P2y-P1y)));
            float denominator = (float) Math.sqrt(Math.pow((P2x-P1x),2) +Math.pow((P2y-P1y),2));

            if (denominator != 0){
                float distance = numerator/denominator;
                if (distance<=this.PD){
                    relevant_cities.add((String)city_json.get("name"));
                }
            }
        }
        return relevant_cities;
    }
    @Override
    public String toString() {
        return "Ride{" +
                "First_name='" + First_name + '\'' +
                ", Last_name='" + Last_name + '\'' +
                ", Phone_number='" + Phone_number + '\'' +
                ", origin='" + Origin + '\'' +
                ", destination='" + Destination + '\'' +
                ", Departure_date=" + Departure_date +
                ", Vacancies=" + Vacancies +
                ", PD=" + PD +
                '}';
    }
}
