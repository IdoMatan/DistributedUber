package host.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PassengerDto {
    public  String firstName;
    public  String lastName;
    public  String origin;
    public  String destination;
    public  String departureDate;
}