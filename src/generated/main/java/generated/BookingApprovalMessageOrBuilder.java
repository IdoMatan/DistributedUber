// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: scheme.proto

package generated;

public interface BookingApprovalMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:routeguide.BookingApprovalMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.routeguide.PassengerProto passenger = 1;</code>
   * @return Whether the passenger field is set.
   */
  boolean hasPassenger();
  /**
   * <code>.routeguide.PassengerProto passenger = 1;</code>
   * @return The passenger.
   */
  generated.PassengerProto getPassenger();
  /**
   * <code>.routeguide.PassengerProto passenger = 1;</code>
   */
  generated.PassengerProtoOrBuilder getPassengerOrBuilder();

  /**
   * <code>string rideId = 2;</code>
   * @return The rideId.
   */
  java.lang.String getRideId();
  /**
   * <code>string rideId = 2;</code>
   * @return The bytes for rideId.
   */
  com.google.protobuf.ByteString
      getRideIdBytes();

  /**
   * <code>.routeguide.RideProto rideProto = 3;</code>
   * @return Whether the rideProto field is set.
   */
  boolean hasRideProto();
  /**
   * <code>.routeguide.RideProto rideProto = 3;</code>
   * @return The rideProto.
   */
  generated.RideProto getRideProto();
  /**
   * <code>.routeguide.RideProto rideProto = 3;</code>
   */
  generated.RideProtoOrBuilder getRideProtoOrBuilder();
}
