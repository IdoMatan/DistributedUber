package external.service;

import api.ZkService;
import generated.BookResult;
import generated.RideProto;
import host.controllers.Sender;
import host.dto.PassengerPathDto;
import host.dto.RideDto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import model.Passenger;
import model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import repository.CityRepository;
import repository.DepartureRepository;
import repository.LiveMapRepository;
import service.BookingService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathPlaningService {
    @Autowired
    private ZkService zkService;
    @Autowired
    private CityRepository citiesRepository;
    @Autowired
    private BookingService bookingService;
    @Autowired
    LiveMapRepository liveMapRepository;
    @Autowired
    DepartureRepository departureRepository;
    @Autowired
    PdCitiesService pdCitiesService;
    @Value("${shard}")
    public String shard;

    @Value(("${server.port}"))
    public String restPort;
    @Value(("${grpc.port}"))
    public String grpcPort;

//    private List<Boolean> booked;
//    public PathPlaningService() {
//    }

    public String bookRides(PassengerPathDto passengerPathDto) {

        List<String> bookedRidesID = new ArrayList<String>(Collections.nCopies(passengerPathDto.origin.size(), "NA"));
        List<Ride> bookedRidesRide = new ArrayList<Ride>(Collections.nCopies(passengerPathDto.origin.size(), null));
        List<RideProto> bookedRidesProtoRide = new ArrayList<RideProto>(Collections.nCopies(passengerPathDto.origin.size(), null));
        var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("grpc.port");

        for (int i = 0; i < passengerPathDto.origin.size(); i++) {
            var city = citiesRepository.getCity(passengerPathDto.origin.get(i));
            var cityLeaderIp = zkService.getLeaderNodeGRPChost(city.shard, city.name);

            if (passengerPathDto.origin.get(i).equals(passengerPathDto.origin.get(0)) || myFullURI.equals(cityLeaderIp)) {                    // local origin
                var optionalRides = liveMapRepository.rideExists(passengerPathDto.origin.get(i),
                        passengerPathDto.destination.get(i), passengerPathDto.departureDate.get(i));
                if (optionalRides.isEmpty()) {
                    if (i == 0) {
                        return "No available ride";
                    }
                    break;
                }
                Ride bookedRide = null;
                for (String rideId : optionalRides) { //
                    var rideOriginCity = parseOrigin(rideId);
                    if (rideOriginCity.equals(passengerPathDto.origin.get(i))) {
                        bookedRide = bookingService.book(passengerPathDto.toPassengerDto(i), rideId);
                        if (bookedRide != null) {
                            bookedRidesID.set(i, rideId);
                            bookedRidesProtoRide.set(i, bookedRide.toRideProto());
                            break;
                        }
                    }
                }
                if (bookedRide == null) {
                    for (String rideId : optionalRides) {
                        var rideOriginCity = parseOrigin(rideId);
                        if (rideOriginCity.equals(passengerPathDto.origin.get(i))) {
                            continue;
                        }
                        city = citiesRepository.getCity(rideOriginCity);
                        cityLeaderIp = zkService.getLeaderNodeGRPChost(city.shard, city.name);
                        if (cityLeaderIp.equals(myFullURI)) {
                            bookedRide = bookingService.book(passengerPathDto.toPassengerDto(i), rideId);
                            if (bookedRide != null) {
                                bookedRidesID.set(i, rideId);
                                bookedRidesProtoRide.set(i, bookedRide.toRideProto());
                                break;
                            }
                        }
                        ManagedChannel channel = ManagedChannelBuilder.forTarget(cityLeaderIp).usePlaintext().build();
                        Sender client = new Sender(channel);
//                        BookResult bookResult = client.bookRide(new Passenger(passengerPathDto.toPassengerDto(i)), rideId);
                        BookResult bookResult = client.bookTripRide(new Passenger(passengerPathDto.toPassengerDto(i)));
                        channel.shutdown();

                        if (bookResult.getSucceededToBook()) {
//                            return "You booked a ride originated in " + rideOriginCity;
                            bookedRidesID.set(i, rideId);
                            bookedRidesProtoRide.set(i, bookResult.getRide());
                            break;
                        }
                    }
                }
            } else {
                city = citiesRepository.getCity(passengerPathDto.origin.get(i)); //todo check if "passengerPathDto.origin.get(i)" is the right format to get the City city
                cityLeaderIp = zkService.getLeaderNodeGRPChost(city.shard, city.name);
                if (!myFullURI.equals(cityLeaderIp)) {
                    ManagedChannel channel = ManagedChannelBuilder.forTarget(cityLeaderIp).usePlaintext().build();
                    Sender client = new Sender(channel);
                    BookResult bookResult = client.bookTripRide(new Passenger(passengerPathDto.toPassengerDto(i)));
                    channel.shutdown();

                    if (bookResult.getSucceededToBook()) {
                        bookedRidesID.set(i, bookResult.getRideId());
                        bookedRidesProtoRide.set(i, bookResult.getRide());
                    }
                }
            }
        }

        boolean cancelBooking = bookedRidesID.contains("NA");
        for (int i = 0; i < passengerPathDto.origin.size(); i++) {
            var rideOriginCity = parseOrigin(bookedRidesID.get(i));
            if (!bookedRidesID.get(i).equals("NA")) {
                var city = citiesRepository.getCity(passengerPathDto.origin.get(i)); //todo check if "passengerPathDto.origin.get(i)" is the right format to get the City city
                var cityLeaderIp = zkService.getLeaderNodeGRPChost(city.shard, city.name);

                Passenger ps = new Passenger(passengerPathDto.toPassengerDto(i));
                if (rideOriginCity.equals(passengerPathDto.origin.get(0)) || myFullURI.equals(cityLeaderIp)) {
                    if (cancelBooking && (!bookedRidesID.get(i).equals("NA"))) { //  cancel ride, not all booked)
                        bookingService.unBook(passengerPathDto.toPassengerDto(i), bookedRidesID.get(i));
                    } else {
                        var dto = new RideDto(bookedRidesProtoRide.get(i));
                        ps.UpdateRideId(bookedRidesID.get(i));
                        bookingService.removeBookedDuplicates(ps);
//                        if (myFullURI.equals(cityLeaderIp)) {
                        ps.UpdateRideId(bookedRidesID.toString());
                        updateCurrentCityFollowers(dto, ps, i);
                        if (i == 0) {
                            bookingService.book(ps);
                        }
//                        }
//                    zkService.updateLiveRidesSync(shard, rideOriginCity, String.valueOf(departureRepository.getSize(rideOriginCity)));
                    }
                } else {
                    ManagedChannel channel = ManagedChannelBuilder.forTarget(cityLeaderIp).usePlaintext().build();
                    Sender client = new Sender(channel);
                    if (cancelBooking && (!bookedRidesID.get(i).equals("NA"))) {
                        client.unBookTripRide(new Passenger(passengerPathDto.toPassengerDto(i)), bookedRidesID.get(i));
                    } else {
                        client.BookTripRideApproval(new Passenger(passengerPathDto.toPassengerDto(i)), bookedRidesID.get(i), bookedRidesProtoRide.get(i));
                    }
                    channel.shutdown();
                }
            }
        }
        if (cancelBooking) {
            return "No trip for you";
        }
        return "booked";
    }


    private String parseOrigin(String rideId) {
        return rideId.split("_")[0];
    }

    private void updateCurrentCityFollowers(RideDto rideDto, Passenger passenger, int i) {
        List<String> followers = zkService.getFollowers(shard);
        var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");

        for (String target : followers) {
            if (!myFullURI.equals(target)) {
                String target_grpc = zkService.getZNodeData("/SHARDS/" + shard + "/liveNodes/" + target);
                ManagedChannel channel = ManagedChannelBuilder.forTarget(target_grpc).usePlaintext().build();
                Sender client = new Sender(channel);
                // Call server streaming call
                if (i == 0){ client.updateFollower(rideDto, rideDto.origin, passenger);}
                else {client.updateFollower(rideDto, rideDto.origin);}
                channel.shutdown();
            }
        }
    }


}
