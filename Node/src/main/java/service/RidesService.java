package service;

import host.dto.RideDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RidesRepository;

@Service
public class RidesService {
    @Autowired
    RidesRepository ridesRepository;

    void addNewRide(RideDto rideDto) {
        ridesRepository.addNew(rideDto);
    }

}
