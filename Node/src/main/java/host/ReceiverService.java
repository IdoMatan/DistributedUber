package host;

import api.ZkService;
import generated.*;
import host.controllers.Sender;
import host.dto.PassengerDto;
import host.dto.RideDto;
import impl.ZkServiceImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import model.City;
import org.springframework.stereotype.Service;
import repository.DepartureRepository;
import repository.LiveMapRepository;
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
        }
        var ride = liveMapRepository.addNew(dto, addressedTo);
        if (!isNewRide){
            var pdCities = (new PdCalculation(ride)).calculate();
            for(City c: pdCities){
                liveMapRepository.addPDRide(ride.buildUniqueKey(), ride.origin, c.name, ride.departureDate);
            }
        }

        Id rideId = Id.newBuilder().setRideId(ride.buildUniqueKey()).build();
        id.onNext(rideId);
        id.onCompleted();
    }


    @Override
    public void updatePDRide(UpdateNewRideMessage rideMessage, StreamObserver<Id> id){
        RideProto pdRideToAdd = rideMessage.getRide();
        var dto = new RideDto(pdRideToAdd);
        String addressedTo = rideMessage.getAddressedTo();
        var ride = liveMapRepository.addNew(dto, addressedTo);
        Id rideId = Id.newBuilder().setRideId(ride.buildUniqueKey()).build();

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

        id.onNext(rideId);
        id.onCompleted();
    }

    @Override
    public void bookRide(BookingRequestMessage message, StreamObserver<BookResult> bookResultStreamObserver) {
        var rideId = message.getRideId();
        var passenger = message.getPassenger();
        var r = departureRepository.book(new PassengerDto(passenger), rideId);
        var bookingSucceeded = r != null;
        var bookResult = BookResult.newBuilder().setSucceededToBook(bookingSucceeded).build();

        if (bookingSucceeded){
            var dto = new RideDto(r);
            String shard = System.getProperty("shard");

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
    public void bookTripRide(BookingRequestMessage message, StreamObserver<BookResult> bookResultStreamObserver){
        var rideId = message.getRideId();
        var passenger = message.getPassenger();
        var r = departureRepository.book(new PassengerDto(passenger), rideId);
        var bookingSucceeded = r != null;
        var bookResult = BookResult.newBuilder().setSucceededToBook(bookingSucceeded).setRide(r.toProto).build();

        bookResultStreamObserver.onNext(bookResult);
        bookResultStreamObserver.onCompleted();
    }

    @Override
    public void unBookTripRide(BookingRequestMessage message, StreamObserver<BookResult> bookResultStreamObserver){
        var rideId = message.getRideId();
        var passenger = message.getPassenger();
        var r = departureRepository.unBook(new PassengerDto(passenger), rideId);
        var bookResult = BookResult.newBuilder().setRide(r.toProto).build();
        bookResultStreamObserver.onNext(bookResult);
        bookResultStreamObserver.onCompleted();
    }

    @Override
    public void bookTripRideApproval(BookingApprovalMessage message, StreamObserver<BookResult> bookResultStreamObserver){
        RideDto dto = new RideDto(message.getRideProto());
        String shard = System.getProperty("shard");
        List<String> followers = zkService.getFollowers(shard);
        var myFullURI = System.getProperty("myIP") + ":" + System.getProperty("rest.port");
        for (String target : followers) {
            if (!myFullURI.equals(target)) {
                String target_grpc = zkService.getZNodeData("/SHARDS/" + shard + "/liveNodes/" + target);
                ManagedChannel channel = ManagedChannelBuilder.forTarget(target_grpc).usePlaintext().build();
                Sender client = new Sender(channel);
                client.updateFollower(dto, dto.origin);
            }
        }
        var bookResult = BookResult.newBuilder().build();
        bookResultStreamObserver.onNext(bookResult);
        bookResultStreamObserver.onCompleted();
    }

}
