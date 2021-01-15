package host.controllers;

import generated.*;
import host.dto.RideDto;
import io.grpc.Channel;
import model.Passenger;

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

    public void updateFollower(RideDto rideDto, String addressedTo){
        var proto = rideDto.toProto();
        UpdateNewRideMessage send_msg = UpdateNewRideMessage.newBuilder().setRide(proto).setAddressedTo(addressedTo).build();
        Id back = blockingStub.updateFollower(send_msg);
    }

    public Id updatePDCities(RideDto rideDto, String addressedTo){
        var proto = rideDto.toProto();
        UpdateNewRideMessage send_msg = UpdateNewRideMessage.newBuilder().setRide(proto).setAddressedTo(addressedTo).build();
        return blockingStub.updatePDRide(send_msg);
    }

    public BookResult bookRide(Passenger ps, String rideId){
        PassengerProto proto = ps.toProto();
        BookingRequestMessage send_msg = BookingRequestMessage.newBuilder().setPassenger(proto).setRideId(rideId).build();
        return blockingStub.bookRide(send_msg);
    }

    public BookResult bookTripRide(Passenger ps){
        PassengerProto proto = ps.toProto();
        BookingRequestMessage send_msg = BookingRequestMessage.newBuilder().setPassenger(proto).build();
        return blockingStub.bookTripRide(send_msg);
    }

    public BookResult bookRideInTrip(Passenger ps, String rideId){
        PassengerProto proto = ps.toProto();
        BookingRequestMessage send_msg = BookingRequestMessage.newBuilder().setPassenger(proto).setRideId(rideId).build();
        return blockingStub.bookRideInTrip(send_msg);
    }


    public BookResult unBookTripRide(Passenger ps, String rideId){
        PassengerProto proto = ps.toProto();
        BookingRequestMessage send_msg = BookingRequestMessage.newBuilder().setPassenger(proto).setRideId(rideId).build();
        return blockingStub.unBookTripRide(send_msg);
    }

    public BookResult BookTripRideApproval(Passenger ps, String rideId, RideProto rideProto){
        PassengerProto proto = ps.toProto();
//        RideProto rideProto = new RideDto(ride).toProto();
        BookingApprovalMessage send_msg = BookingApprovalMessage.newBuilder().setPassenger(proto).setRideId(rideId).setRideProto(rideProto).build();
        return blockingStub.bookTripRideApproval(send_msg);
    }

    public IsEmptyAgreement liveMapIsEmpty(String origin, String destination, String departureDate) {
        LiveMapIsEmptyMessage send_msg = LiveMapIsEmptyMessage.newBuilder()
                .setOrigin(origin).setDestination(destination).setDepartureDate(departureDate).build();
        return blockingStub.liveMapIsEmpty(send_msg);
    }
}
