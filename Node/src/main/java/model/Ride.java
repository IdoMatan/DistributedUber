package model;

import java.util.Date;


public class Ride {
    public final String firstName;
    public final String lastName;
    public final String phoneNumber;
    public final String origin;
    public final String destination;
    public final Date departureDate;
    public int vacancies;
    public final float pd;

    public Ride(String first, String last, String phone, String origin, String dest, Date departureDate, int seats, float pd) {
        this.firstName = first;
        this.lastName = last;
        this.phoneNumber = phone;
        this.origin = origin;
        this.destination = dest;
        this.departureDate = departureDate;
        this.vacancies = seats;
        this.pd = pd;
    }

    public String buildUniqueKey() {
        return origin + "_" + departureDate.toString() + "_" + firstName + "_" + lastName;
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
                '}';
    }
}
