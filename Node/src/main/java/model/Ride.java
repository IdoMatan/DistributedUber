package model;


import generated.RideProto;
import host.dto.RideDto;

import java.util.ArrayList;
import java.util.List;

public class Ride {
    public final String firstName;
    public final String lastName;
    public String phoneNumber;
    public final String origin;
    public final String destination;
    public final String departureDate;
    public int vacancies;
    public int vacancies_initial;
    public float pd;
    public List<Passenger> passengerList;
    public List<String> passengerListString;

    public Ride(String first, String last, String phone, String origin, String dest, String departureDate, int seats, float pd, List<Passenger> passengerList) {
        this.firstName = first;
        this.lastName = last;
        this.phoneNumber = phone;
        this.origin = origin;
        this.destination = dest;
        this.departureDate = departureDate;
        this.vacancies = seats;
        this.vacancies_initial = seats;
        this.pd = pd;
        this.passengerList = passengerList == null ? new ArrayList<>() : passengerList;
        this.passengerListString = passengerListString == null ? new ArrayList<>() : passengerListString;
    }

    public Ride(RideDto rideDto) {
        this.firstName = rideDto.firstName;
        this.lastName = rideDto.lastName;
        this.origin = rideDto.origin;
        this.destination = rideDto.destination;
        this.departureDate = rideDto.departureDate;
    }

    public String buildUniqueKey() {
        return origin + "_" + destination + "_" + departureDate + "_" + firstName + "_" + lastName;
    }

    public boolean available() {
//        return vacancies - passengerList.size() > 0;
        return this.vacancies > 0;
    }

    public void book(Passenger ps) {
        this.vacancies--;
        passengerList.add(ps);
        passengerListString.add(ps.toUID());
    }

    public boolean passengerExist(Passenger ps){
        return passengerListString.contains(ps.toUID());
//        return passengerList.contains(ps);
    }

    public void unBook(Passenger ps) {
        this.vacancies++;
        passengerList.remove(ps); //
        passengerListString.remove(ps.toUID());
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
                ", Passenger_list=" + passengerListString +
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
