package host.controllers;

import generated.*;
import host.dto.RideDto;
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

    public void updateFollower(RideDto rideDto){
        var proto = rideDto.toProto();
        UpdateNewRideMessage send_msg = UpdateNewRideMessage.newBuilder().setRide(proto).build();
        Id back = blockingStub.updateFollower(send_msg);

    }
}
