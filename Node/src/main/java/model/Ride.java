package model;


import generated.RideProto;

import java.util.ArrayList;
import java.util.List;

public class Ride {
    public final String firstName;
    public final String lastName;
    public final String phoneNumber;
    public final String origin;
    public final String destination;
    public final String departureDate;
    public final int vacancies;
    public final float pd;
    public List<Passenger> passengerList;

    public Ride(String first, String last, String phone, String origin, String dest, String departureDate, int seats, float pd, List<Passenger> passengerList) {
        this.firstName = first;
        this.lastName = last;
        this.phoneNumber = phone;
        this.origin = origin;
        this.destination = dest;
        this.departureDate = departureDate;
        this.vacancies = seats;
        this.pd = pd;
        this.passengerList = passengerList == null ? new ArrayList<>() : passengerList;
    }

    public String buildUniqueKey() {
        return origin + "_" + destination + "_" + departureDate + "_" + firstName + "_" + lastName;
    }

    public boolean available() {
        return vacancies - passengerList.size() > 0;
    }

    public void book(Passenger ps) {
        passengerList.add(ps);
    }

    public boolean passengerExist(Passenger ps){
        return passengerList.contains(ps);
    }
    public void unBook(Passenger ps) {
        passengerList.remove(ps); //
    }

    @Override
    public String toString() {
        return "Ride{" +
                "First_name='" + firstName + '\'' +
                ", Last_name='" + lastName + '\'' +
                ", Phone_number='" + phoneNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", Departure_date=" + departureDate +
                ", Vacancies=" + vacancies +
                ", PD=" + pd +
                ", Passenger_list=" + passengerList.toString() +
                '}';
    }

    public RideProto toRideProto(){
        return RideProto.newBuilder().setFirstName(this.firstName)
                .setLastName(this.lastName).setPhoneNumber(this.phoneNumber)
                .setOrigin(this.origin).setDestination(this.destination)
                .setDepartureDate(this.departureDate).setVacancies(this.vacancies)
                .setPd(this.pd).build();
    }
}
