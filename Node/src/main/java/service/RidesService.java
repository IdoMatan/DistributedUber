package service;

import host.dto.RideDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.LiveMapRepository;

@Service
public class RidesService {
    @Autowired
    LiveMapRepository liveMapRepository;

    void addNewRide(RideDto rideDto) {
        liveMapRepository.addNew(rideDto);
    }

}
