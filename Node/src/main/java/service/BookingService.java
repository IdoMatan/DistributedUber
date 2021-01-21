package service;

import host.dto.PassengerDto;
import model.Passenger;
import model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DepartureRepository;
import repository.PassengersRepository;

@Service
public class BookingService {
    @Autowired
    PassengersRepository passengersRepository;
    @Autowired
    DepartureRepository departuresRepository;

    public BookingService(){}
    public BookingService(PassengersRepository passengersRepository,   DepartureRepository departuresRepository) {
        this.passengersRepository = passengersRepository;
        this.departuresRepository = departuresRepository;
    }
    public Ride book(PassengerDto passengerDto, String rideId) {
        var ps = new Passenger(passengerDto);
        var ride = departuresRepository.book(ps, rideId);
        if ((ride != null) && (ride.passengerExist(ps)) && (ride.origin.equals(ps.origin))) {
            passengersRepository.addNewPassenger(ps);
        }
        return ride;
    }

    public Passenger book(Passenger passenger) {
        passengersRepository.addNewPassenger(passenger);
        return passenger;
    }

    public Passenger removeBookedDuplicates(Passenger passenger) {
        passengersRepository.removePassenger(passenger);
        return passenger;
    }

    public Ride unBook(PassengerDto passengerDto, String rideId) {
        var ps = new Passenger(passengerDto);
        var ride = departuresRepository.unBook(ps, rideId);
        ps.UpdateRideId(rideId);
        if (!rideId.equals("NA")) {
            passengersRepository.removePassenger(ps);
        }
        return ride;
    }

}
