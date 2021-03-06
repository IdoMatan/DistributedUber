import generated.Msg1;
import generated.Msg2;
import generated.RouteGuideGrpc;
import io.grpc.stub.StreamObserver;

public class ReceiverService extends RouteGuideGrpc.RouteGuideImplBase {

    private final int input;

    public ReceiverService() {
        this.input = 5;
    }

    @Override
    public void senderTest1(Msg1 inputs, StreamObserver<Msg2> responseObserver){
        Msg2 out = Msg2.newBuilder()
                .setC(inputs.getA()+inputs.getB())
                .build();
        out.newBuilderForType().setC(9);
        if (inputs.getA() >= 10){System.out.println("bigger than 10");}
        responseObserver.onNext(out);
        responseObserver.onCompleted();
    }
}
