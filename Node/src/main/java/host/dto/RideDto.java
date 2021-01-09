package host.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import generated.RideProto;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RideDto {
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String origin;
    public String destination;
    public String departureDate;
    public int vacancies;
    public float pd;

    public RideDto(RideProto rideProto) {
        this.firstName = rideProto.getFirstName();
        this.lastName = rideProto.getLastName();
        this.phoneNumber = rideProto.getPhoneNumber();
        this.origin = rideProto.getDestination();
        this.destination = rideProto.getDepartureDate();
        this.vacancies = rideProto.getVacancies();
        this.pd = rideProto.getPd();
    }

    public RideProto toProto() {
        return RideProto.newBuilder().setFirstName(firstName).setLastName(lastName).setPhoneNumber(phoneNumber).setOrigin(origin).setDepartureDate(departureDate).setDestination(destination).setVacancies(vacancies).setPd(pd).build();
    }
}