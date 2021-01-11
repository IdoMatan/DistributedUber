// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: scheme.proto

package generated;

public final class RouteGuideProto {
  private RouteGuideProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_routeguide_PassengerProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_routeguide_PassengerProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_routeguide_BookingRequestMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_routeguide_BookingRequestMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_routeguide_RideProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_routeguide_RideProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_routeguide_UpdateNewRideMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_routeguide_UpdateNewRideMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_routeguide_Id_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_routeguide_Id_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_routeguide_BookingApprovalMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_routeguide_BookingApprovalMessage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_routeguide_BookResult_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_routeguide_BookResult_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_routeguide_Msg1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_routeguide_Msg1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_routeguide_Msg2_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_routeguide_Msg2_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014scheme.proto\022\nrouteguide\"q\n\016PassengerP" +
      "roto\022\021\n\tfirstName\030\002 \001(\t\022\020\n\010lastName\030\003 \001(" +
      "\t\022\016\n\006origin\030\004 \001(\t\022\023\n\013destination\030\005 \001(\t\022\025" +
      "\n\rdepartureDate\030\006 \001(\t\"V\n\025BookingRequestM" +
      "essage\022\016\n\006rideId\030\001 \001(\t\022-\n\tpassenger\030\002 \001(" +
      "\0132\032.routeguide.PassengerProto\"\241\001\n\tRidePr" +
      "oto\022\021\n\tfirstName\030\001 \001(\t\022\020\n\010lastName\030\002 \001(\t" +
      "\022\023\n\013phoneNumber\030\003 \001(\t\022\016\n\006origin\030\004 \001(\t\022\023\n" +
      "\013destination\030\005 \001(\t\022\026\n\016departure_date\030\006 \001" +
      "(\t\022\021\n\tvacancies\030\007 \001(\005\022\n\n\002pd\030\010 \001(\002\"P\n\024Upd" +
      "ateNewRideMessage\022#\n\004ride\030\001 \001(\0132\025.routeg" +
      "uide.RideProto\022\023\n\013AddressedTo\030\002 \001(\t\"\024\n\002I" +
      "d\022\016\n\006rideId\030\001 \001(\t\"\201\001\n\026BookingApprovalMes" +
      "sage\022-\n\tpassenger\030\001 \001(\0132\032.routeguide.Pas" +
      "sengerProto\022\016\n\006rideId\030\002 \001(\t\022(\n\trideProto" +
      "\030\003 \001(\0132\025.routeguide.RideProto\"Z\n\nBookRes" +
      "ult\022\027\n\017succeededToBook\030\001 \001(\010\022\016\n\006rideId\030\002" +
      " \001(\t\022#\n\004ride\030\003 \001(\0132\025.routeguide.RideProt" +
      "o\"\034\n\004Msg1\022\t\n\001a\030\001 \001(\005\022\t\n\001b\030\002 \001(\005\"\021\n\004Msg2\022" +
      "\t\n\001c\030\001 \001(\0052\206\004\n\nRouteGuide\0223\n\013SenderTest1" +
      "\022\020.routeguide.Msg1\032\020.routeguide.Msg2\"\000\022D" +
      "\n\016UpdateFollower\022 .routeguide.UpdateNewR" +
      "ideMessage\032\016.routeguide.Id\"\000\022B\n\014updatePD" +
      "Ride\022 .routeguide.UpdateNewRideMessage\032\016" +
      ".routeguide.Id\"\000\022G\n\010BookRide\022!.routeguid" +
      "e.BookingRequestMessage\032\026.routeguide.Boo" +
      "kResult\"\000\022K\n\014BookTripRide\022!.routeguide.B" +
      "ookingRequestMessage\032\026.routeguide.BookRe" +
      "sult\"\000\022M\n\016UnBookTripRide\022!.routeguide.Bo" +
      "okingRequestMessage\032\026.routeguide.BookRes" +
      "ult\"\000\022T\n\024BookTripRideApproval\022\".routegui" +
      "de.BookingApprovalMessage\032\026.routeguide.B" +
      "ookResult\"\000B$\n\tgeneratedB\017RouteGuideProt" +
      "oP\001\242\002\003RTGb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_routeguide_PassengerProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_routeguide_PassengerProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_routeguide_PassengerProto_descriptor,
        new java.lang.String[] { "FirstName", "LastName", "Origin", "Destination", "DepartureDate", });
    internal_static_routeguide_BookingRequestMessage_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_routeguide_BookingRequestMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_routeguide_BookingRequestMessage_descriptor,
        new java.lang.String[] { "RideId", "Passenger", });
    internal_static_routeguide_RideProto_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_routeguide_RideProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_routeguide_RideProto_descriptor,
        new java.lang.String[] { "FirstName", "LastName", "PhoneNumber", "Origin", "Destination", "DepartureDate", "Vacancies", "Pd", });
    internal_static_routeguide_UpdateNewRideMessage_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_routeguide_UpdateNewRideMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_routeguide_UpdateNewRideMessage_descriptor,
        new java.lang.String[] { "Ride", "AddressedTo", });
    internal_static_routeguide_Id_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_routeguide_Id_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_routeguide_Id_descriptor,
        new java.lang.String[] { "RideId", });
    internal_static_routeguide_BookingApprovalMessage_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_routeguide_BookingApprovalMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_routeguide_BookingApprovalMessage_descriptor,
        new java.lang.String[] { "Passenger", "RideId", "RideProto", });
    internal_static_routeguide_BookResult_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_routeguide_BookResult_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_routeguide_BookResult_descriptor,
        new java.lang.String[] { "SucceededToBook", "RideId", "Ride", });
    internal_static_routeguide_Msg1_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_routeguide_Msg1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_routeguide_Msg1_descriptor,
        new java.lang.String[] { "A", "B", });
    internal_static_routeguide_Msg2_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_routeguide_Msg2_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_routeguide_Msg2_descriptor,
        new java.lang.String[] { "C", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
