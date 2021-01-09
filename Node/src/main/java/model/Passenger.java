package model;

import host.dto.PassengerDto;

public class Passenger {
    public  String firstName;
    public  String lastName;
    public  String origin;
    public  String destination;
    public  String departureDate;

    public Passenger(PassengerDto dto) {
        this.firstName = dto.firstName;
        this.lastName = dto.lastName;
        this.origin = dto.origin;
        this.destination = dto.destination;
        this.departureDate = dto.departureDate;
    }
}
