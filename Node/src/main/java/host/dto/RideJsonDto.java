package host.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RideJsonDto {
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String origin;
    public String destination;
    public String departure_date;
    public int vacancies;
    public float pd;
    public List<PassengerDto> passengers;


    public RideJsonDto() {
    }
}
//
//    public RideJsonDto(Ride ride) {
//        this.firstName = ride.firstName;
//        this.lastName = ride.lastName;
//        this.phoneNumber = ride.phoneNumber;
//        this.origin = ride.origin;
//        this.destination = ride.destination;
//        this.departureDate = ride.departureDate;
//        this.vacancies = ride.vacancies;
//        this.pd = ride.pd;
//        this.passengers = ride.passengerList.stream().map(PassengerDto::new).collect(Collectors.toList());
//    }
//
//    public RideJsonDto(RideProto rideProto) {
//        this.firstName = rideProto.getFirstName();
//        this.lastName = rideProto.getLastName();
//        this.phoneNumber = rideProto.getPhoneNumber();
//        this.origin = rideProto.getOrigin();
//        this.destination = rideProto.getDestination();
//        this.departureDate = rideProto.getDepartureDate();
//        this.vacancies = rideProto.getVacancies();
//        this.pd = rideProto.getPd();
//    }
//
//    public RideProto toProto() {
//        return RideProto.newBuilder()
//                .setFirstName(firstName).setLastName(lastName)
//                .setPhoneNumber(phoneNumber).setOrigin(origin)
//                .setDepartureDate(departureDate).setDestination(destination)
//                .setVacancies(vacancies).setPd(pd)
//                .build();
//    }
//
//    @Override
//    public String toString() {
//        return "RideDto={ firstName = " + firstName + ",\nlastName = " + lastName + ",\nphoneNumber = " + phoneNumber +
//                ",\norigin = " + origin + ",\ndestination = " + destination + ",\ndepartureDate = " + departureDate + ",\nvacancies = " + vacancies
//                + ",\npd = " + pd + " }";
//    }
//}