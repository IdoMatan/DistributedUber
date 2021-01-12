package model;

import generated.PassengerProto;
import host.dto.PassengerDto;

public class Passenger {
    public  String firstName;
    public  String lastName;
    public  String origin;
    public  String destination;
    public  String departureDate;
    public String rideId;

    public Passenger(PassengerDto dto) {
        this.firstName = dto.firstName;
        this.lastName = dto.lastName;
        this.origin = dto.origin;
        this.destination = dto.destination;
        this.departureDate = dto.departureDate;
    }

    public void UpdateRideId(String rideId) {
        this.rideId = rideId;
    }

    public PassengerProto toProto(){
        return PassengerProto.newBuilder().setFirstName(firstName).setLastName(lastName)
                .setOrigin(origin).setDestination(destination)
                .setDepartureDate(departureDate).build();
    }
}
