package host;

import generated.*;
import host.dto.RideDto;
import io.grpc.stub.StreamObserver;
import repository.DepartureRepository;
import repository.LiveMapRepository;

public class ReceiverService extends RouteGuideGrpc.RouteGuideImplBase {

    private final int input;

    public ReceiverService() {
        this.input = 5;
    }

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
    public void updateFollower(UpdateNewRideMessage ride, StreamObserver<Id> id) {
        RideProto rideToAdd = ride.getRide();
        var dto = new RideDto(rideToAdd);
        departureRepository.addNew(dto);
        liveMapRepository.addNew(dto);
    }
}
