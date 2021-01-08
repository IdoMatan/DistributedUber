package host.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import generated.NewRideDto;

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

    public NewRideDto toProto() {
        return
    }
}