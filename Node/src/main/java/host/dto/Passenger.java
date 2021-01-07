package host.dto;

import api.ZkService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class Passenger {
    private final String First_name;
    private final String Last_name;
    private final String Phone_number;
    private final String Origin;
    private final String Destination;
    private final Date Departure_date;
    private int Vacancies;
    private final float PD;
    @Autowired
    private ZkService zkService;

    public Passenger(String first, String last, String phone, String origin, String dest, Date departure_date, int seats, float pd){
        this.First_name = first;
        this.Last_name = last;
        this.Phone_number = phone;
        this.Origin = origin;
        this.Destination = dest;
        this.Departure_date = departure_date;
        this.Vacancies = seats;
        this.PD = pd;
    }

}
