package model;



import java.util.ArrayList;
import java.util.List;

public class Ride {
    public final String firstName;
    public final String lastName;
    public final String phoneNumber;
    public final String origin;
    public final String destination;
    public final String departureDate;
    public int vacancies;
    public final float pd;
    public List<Passenger> passengerList = new ArrayList<>();

    public Ride(String first, String last, String phone, String origin, String dest, String departureDate, int seats, float pd) {
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
        return origin + "_" + departureDate + "_" + firstName + "_" + lastName;
    }

    public boolean available() {
        return vacancies > 0;
    }

    public void book(Passenger ps) {
        passengerList.add(ps);
        vacancies--;
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
                ", Passenger_list=" + passengerList.toString()+
                '}';
    }
}
