package host.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PassengerPathDto {
    public String firstName;
    public String lastName;
    public List<String> origin;
    public List<String> destination;
    public List<String> departureDate;


//    PassengerPathDto() {
//    }

//    PassengerPathDto(Passenger passenger) {
//        this.firstName = passenger.firstName;
//        this.lastName = passenger.lastName;
//        this.origin = passenger.origin;
//        this.destination = passenger.destination;
//        this.departureDate = passenger.departureDate;
//    }
//
    public PassengerDto toPassengerDto(int i) {
        PassengerDto passengerDto = new PassengerDto();
        passengerDto.firstName = this.firstName;
        passengerDto.lastName = this.lastName;
        passengerDto.origin = this.origin.get(i);
        passengerDto.destination = this.destination.get(i);
        passengerDto.departureDate = this.departureDate.get(i);
        return passengerDto;
    }
}