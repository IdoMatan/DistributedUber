package host.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import model.Passenger;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PassengerDto {
    public  String firstName;
    public  String lastName;
    public  String origin;
    public  String destination;
    public  String departureDate;

    PassengerDto(){ }

    PassengerDto(Passenger passenger) {
        this.firstName = passenger.firstName;
        this.lastName = passenger.lastName;
        this.origin = passenger.origin;
        this.destination = passenger.destination;
        this.departureDate = passenger.departureDate;
    }
}