package service;

import model.CitiesDataBase;
import model.City;
import model.Ride;

import java.util.ArrayList;
import java.util.List;

public class PdCalculation {
    Ride ride;

    public PdCalculation(Ride ride) {
        this.ride = ride;
    }

    public List<City> calculate() {
        var originX = CitiesDataBase.cities.get(ride.origin).x;
        var originY = CitiesDataBase.cities.get(ride.origin).y;
        var destinationX = CitiesDataBase.cities.get(ride.destination).x;
        var destinationY = CitiesDataBase.cities.get(ride.destination).y;

        List<City> pdCities = new ArrayList<>();
        for(City city: CitiesDataBase.cities.values()){
            if (city.name.equalsIgnoreCase(ride.origin) || city.name.equalsIgnoreCase(ride.destination)){
                continue;
            }

            float numerator = Math.abs(Math.multiplyExact((destinationX - originX), (originY - city.y)) - Math.multiplyExact((originX - city.x), (destinationY - originY)));
            float denominator = (float) Math.sqrt(Math.pow((destinationX - originX), 2) + Math.pow((destinationY - originY), 2));
//
            if (denominator != 0) {
                float distance = numerator / denominator;
                if (distance <= ride.pd) {
                    pdCities.add(city);
                }
            }
        }

        return pdCities;
    }
}
