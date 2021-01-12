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

    @Override
    public String toString() {
        return "Passenger{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", Departure_date=" + departureDate +
                ", rideId=" + rideIs +
                ", PD=" + pd +
                ", Passenger_list=" + passengerList.toString() +
                '}';
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
