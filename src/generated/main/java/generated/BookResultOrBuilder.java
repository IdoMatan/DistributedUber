// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: scheme.proto

package generated;

public interface BookResultOrBuilder extends
    // @@protoc_insertion_point(interface_extends:routeguide.BookResult)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bool succeededToBook = 1;</code>
   * @return The succeededToBook.
   */
  boolean getSucceededToBook();

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
   * <code>.routeguide.RideProto ride = 3;</code>
   * @return Whether the ride field is set.
   */
  boolean hasRide();
  /**
   * <code>.routeguide.RideProto ride = 3;</code>
   * @return The ride.
   */
  generated.RideProto getRide();
  /**
   * <code>.routeguide.RideProto ride = 3;</code>
   */
  generated.RideProtoOrBuilder getRideOrBuilder();
}
