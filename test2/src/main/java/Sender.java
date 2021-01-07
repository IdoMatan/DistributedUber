import generated.Msg1;
import generated.Msg2;
import generated.RouteGuideGrpc;
import io.grpc.Channel;

public class Sender {

    private final RouteGuideGrpc.RouteGuideBlockingStub blockingStub;

    public Sender(Channel channel) {
        blockingStub = RouteGuideGrpc.newBlockingStub(channel);
    }

    public void senderTest1(int a, int b) {

        Msg1 send_msg = Msg1.newBuilder()
                .setA(a).setB(b)
                .build();

        Msg2 back = blockingStub.senderTest1(send_msg);
        System.out.println("you received - ");
        System.out.println(back.getC());
    }
}
