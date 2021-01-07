package model;

import java.util.Date;


public class Ride {
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String origin;
    private final String destination;
    private final Date departureDate;
    private int vacancies;
    private final float pd;

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
