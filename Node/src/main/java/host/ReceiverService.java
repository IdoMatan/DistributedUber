package host;

import api.ZkService;
import generated.*;
import host.controllers.RidesController;
import host.controllers.Sender;
import host.dto.PassengerDto;
import host.dto.RideDto;
import impl.ZkServiceImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import model.City;
import model.Passenger;
import model.Ride;
import org.springframework.stereotype.Service;
import repository.CityRepository;
import repository.DepartureRepository;
import repository.LiveMapRepository;
import repository.PassengersRepository;
import service.PdCalculation;

import java.util.List;

@Service
public class ReceiverService extends RouteGuideGrpc.RouteGuideImplBase {

    public ZkService zkService = new ZkServiceImpl("localhost:2181");


//    private final int input;

//    public ReceiverService() {
//        this.input = 5;
//    }

    private DepartureRepository departureRepository = new DepartureRepository();
    private LiveMapRepository liveMapRepository = new LiveMapRepository();
    private PassengersRepository passengersRepository = new PassengersRepository();

    @Override
    public void senderTest1(Msg1 inputs, StreamObserver<Msg2> responseObserver) {
        Msg2 out = Msg2.newBuilder()
                .setC(10)
                .build();
        if (inputs.getA() >= 11) {
            System.out.println("bigger than 11");
        }
        responseObserver.onNext(out);
        responseObserver.onCompleted();
    }

    @Override
    public void updateFollower(UpdateNewRideMessage rideMessage, StreamObserver<Id> id) {
        RideProto rideToAdd = rideMessage.getRide();
        String addressedTo = rideMessage.getAddressedTo();

        var dto = new RideDto(rideToAdd);
        var isNewRide = false;
        if (addressedTo.equals(dto.origin)) {
            isNewRide = departureRepository.exists(dto);
            departureRepository.upsertRide(dto);
            try {
                PassengerProto passengerProto = rideMessage.getPassenger();
                Passenger ps = new Passenger(new PassengerDto(passengerProto));
                ps.UpdateRideId(new Ride(dto).buildUniqueKey());
                passengersRepository.addNewPassenger(ps);
            } catch (Exception e) {
//                System.out.println("Updating follower with new ride");
            }
        }
        var ride = liveMapRepository.upsert(dto, addressedTo);
        if (!isNewRide) {
            var pdCities = (new PdCalculation(ride)).calculate();
            for (City c : pdCities) {
                liveMapRepository.addPDRide(ride.buildUniqueKey(), ride.origin, c.name, ride.departureDate);
            }
        }

        Id rideId = Id.newBuilder().setRideId(ride.buildUniqueKey()).build();
        id.onNext(rideId);
        id.onCompleted();
    }


    @Override
    public void updatePDRide(UpdateNewRideMessage rideMessage, StreamObserver<Id> id) {
        RideProto pdRideToAdd = rideMessage.getRide();
        var dto = new RideDto(pdRideToAdd);
        String addressedTo = rideMessage.getAddressedTo();
        var ride = liveMapRepository.upsert(dto, addressedTo);
        Id rideId = Id.newBuilder().setRideId(ride.buildUniqueKey()).build();
        id.onNext(rideId);

        String shard = System.getProperty("shard");
        List<String> followers = zkService.getFollowers(shard);
        var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");
        for (String target : followers) {
            if (!myFullURI.equals(target)) {
                String target_grpc = zkService.getZNodeData("/SHARDS/" + shard + "/liveNodes/" + target);
                ManagedChannel channel = ManagedChannelBuilder.forTarget(target_grpc).usePlaintext().build();
                Sender client = new Sender(channel);
                // Call server streaming call
                client.updateFollower(dto, addressedTo);
            }
        }

        id.onCompleted();
    }

    @Override
    public void bookRide(BookingRequestMessage message, StreamObserver<BookResult> bookResultStreamObserver) {
        var rideId = message.getRideId();
        var passenger = message.getPassenger();
        var r = departureRepository.book(new PassengerDto(passenger), rideId);

        var bookingSucceeded = r != null;
        var bookResult = BookResult.newBuilder().setSucceededToBook(bookingSucceeded).build();

        if (bookingSucceeded) {
            var dto = new RideDto(r);
            String shard = System.getProperty("shard");
//            zkService.updateLiveRidesSync(shard, r.origin, String.valueOf(departureRepository.getSize(r.origin)));
            List<String> followers = zkService.getFollowers(shard);

            var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");
            for (String target : followers) {
                if (!myFullURI.equals(target)) {
                    String target_grpc = zkService.getZNodeData("/SHARDS/" + shard + "/liveNodes/" + target);
                    System.out.println("matanweks: " + target_grpc);
                    ManagedChannel channel = ManagedChannelBuilder.forTarget(target_grpc).usePlaintext().build();
                    Sender client = new Sender(channel);
                    client.updateFollower(dto, dto.origin);
                }
            }
        }
        bookResultStreamObserver.onNext(bookResult);
        bookResultStreamObserver.onCompleted();
    }

    @Override
    public void bookTripRide(BookingRequestMessage message, StreamObserver<BookResult> bookResultStreamObserver) {
        PassengerDto passengerDto = new PassengerDto(message.getPassenger());
        var optionalRides = liveMapRepository.rideExists(passengerDto.origin, passengerDto.destination, passengerDto.departureDate);
        if (optionalRides.isEmpty()) {
            BookResult bookResult = BookResult.newBuilder().setSucceededToBook(false).build();
            bookResultStreamObserver.onNext(bookResult);
            bookResultStreamObserver.onCompleted();
            return;
        }
        Ride bookedRide = null;
        var myFullURI = zkService.getLeaderNodeGRPChost(System.getProperty("shard"), passengerDto.origin);
        for (String rideId : optionalRides) {
            var rideOriginCity = new RidesController().parseOrigin(rideId);
//            if (rideOriginCity.equals("myCity")) {
//                bookedRide = departureRepository.book(passengerDto, rideId);
//                if (bookedRide != null) {
//                    BookResult bookResult = BookResult.newBuilder().setSucceededToBook(true).setRide(bookedRide.toRideProto()).setRideId(rideId).build();
//                    bookResultStreamObserver.onNext(bookResult);
//                    bookResultStreamObserver.onCompleted();
//                    return;
//                }
//            } else {
            var city = new CityRepository().getCity(rideOriginCity);
            var cityLeaderIp = zkService.getLeaderNodeGRPChost(city.shard, city.name);
            if (!myFullURI.equals(cityLeaderIp)) {
                ManagedChannel channel = ManagedChannelBuilder.forTarget(cityLeaderIp).usePlaintext().build();
                Sender client = new Sender(channel);
                BookResult bookResultReceive = client.bookRideInTrip(new Passenger(passengerDto), rideId);
                if (bookResultReceive.getSucceededToBook()) {
                    BookResult bookResult = BookResult.newBuilder().setSucceededToBook(true)
                            .setRide(bookResultReceive.getRide()).setRideId(bookResultReceive.getRideId()).build();
                    bookResultStreamObserver.onNext(bookResult);
                    bookResultStreamObserver.onCompleted();
                    return;
                }
            } else {
                bookedRide = departureRepository.book(passengerDto, rideId);
                if (bookedRide != null) {
                    BookResult bookResult = BookResult.newBuilder().setSucceededToBook(true)
                            .setRide(bookedRide.toRideProto()).setRideId(rideId).build();
                    bookResultStreamObserver.onNext(bookResult);
                    bookResultStreamObserver.onCompleted();
                    return;
                }
            }
//            }
//        // booked ride by local driver.
//        if (bookedRide != null) {
//
//
//
//        } else {
//        for (String rideId : optionalRides) {
//            var rideOriginCity = new RidesController().parseOrigin(rideId);
//            if (rideOriginCity.equals("myCity")) {
//                continue;
//            }

//            }
//        }
        }
        BookResult bookResult = BookResult.newBuilder().setSucceededToBook(false).build();
        bookResultStreamObserver.onNext(bookResult);
        bookResultStreamObserver.onCompleted();
    }

    @Override
    public void bookRideInTrip(BookingRequestMessage message, StreamObserver<BookResult> bookResultStreamObserver) {
        BookResult bookResult;
        var rideId = message.getRideId();
        var passenger = message.getPassenger();
        var r = departureRepository.book(new PassengerDto(passenger), rideId);
        var bookingSucceeded = r != null;
        if (bookingSucceeded) {
            bookResult = BookResult.newBuilder().setSucceededToBook(bookingSucceeded).setRide(r.toRideProto()).setRideId(rideId).build();
        } else {
            bookResult = BookResult.newBuilder().setSucceededToBook(bookingSucceeded).build();
        }
        bookResultStreamObserver.onNext(bookResult);
        bookResultStreamObserver.onCompleted();

    }


    @Override
    public void unBookTripRide(BookingRequestMessage message, StreamObserver<BookResult> bookResultStreamObserver) {
        var rideId = message.getRideId();
        var passenger = message.getPassenger();
        var r = departureRepository.unBook(new PassengerDto(passenger), rideId);
        BookResult bookResult;
        if (r != null) {
            bookResult = BookResult.newBuilder().setRide(r.toRideProto()).build();
        } else {
            bookResult = BookResult.newBuilder().build();
        }
        bookResultStreamObserver.onNext(bookResult);
        bookResultStreamObserver.onCompleted();
    }

    @Override
    public void bookTripRideApproval(BookingApprovalMessage message, StreamObserver<BookResult> bookResultStreamObserver) {
        RideDto dto = new RideDto(message.getRideProto());
        String shard = System.getProperty("shard");
        List<String> followers = zkService.getFollowers(shard);
//        zkService.updateLiveRidesSync(shard, dto.origin, String.valueOf(departureRepository.getSize(dto.origin)));
        Passenger passenger = new Passenger(new PassengerDto(message.getPassenger()));
        var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");
        for (String target : followers) {
            if (!myFullURI.equals(target)) {
                String target_grpc = zkService.getZNodeData("/SHARDS/" + shard + "/liveNodes/" + target);
                ManagedChannel channel = ManagedChannelBuilder.forTarget(target_grpc).usePlaintext().build();
                Sender client = new Sender(channel);
                client.updateFollower(dto, dto.origin, passenger);
            }
        }
        var bookResult = BookResult.newBuilder().build();
        bookResultStreamObserver.onNext(bookResult);
        bookResultStreamObserver.onCompleted();
    }

    @Override
    public void liveMapIsEmpty(LiveMapIsEmptyMessage message, StreamObserver<IsEmptyAgreement> isEmptyAgreementStreamObserver) {
        String origin =  message.getOrigin();
        String destination =  message.getDestination();
        String departureDate = message.getDepartureDate();

        var optionalRides = liveMapRepository.rideExists(origin, destination, departureDate);
        IsEmptyAgreement isEmptyAgreement = IsEmptyAgreement.newBuilder().setIsEmpty(optionalRides.isEmpty()).build();
        isEmptyAgreementStreamObserver.onNext(isEmptyAgreement);
        isEmptyAgreementStreamObserver.onCompleted();
    }


    @Override
    public void getSyncParam(CityMessage cityMessage, StreamObserver<SyncParam> SyncParamStreamObserver){
        String city =  cityMessage.getCity();

        String SyncParamInt = String.valueOf(departureRepository.getSize(city));

        SyncParam syncParam = SyncParam.newBuilder().setSyncParamProto(SyncParamInt).build();
        SyncParamStreamObserver.onNext(syncParam);
        SyncParamStreamObserver.onCompleted();
    }

    @Override
    public void updateFollowersPassengerList(BookingRequestMessage message, StreamObserver<Id> IdStreamObserver) {
        var rideId = message.getRideId();
        var passengerProto = message.getPassenger();
        Passenger ps = new Passenger(new PassengerDto(passengerProto));
        passengersRepository.addNewPassenger(ps);

        Id psId = Id.newBuilder().setRideId(ps.toString()).build();
        IdStreamObserver.onNext(psId);
        IdStreamObserver.onCompleted();

    }
}
