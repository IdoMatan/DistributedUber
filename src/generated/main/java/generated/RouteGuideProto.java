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
    internal_static_routeguide_Msg1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_routeguide_Msg1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_routeguide_Msg2_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_routeguide_Msg2_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_routeguide_NewRideDto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_routeguide_NewRideDto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_routeguide_Id_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_routeguide_Id_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014scheme.proto\022\nrouteguide\"\034\n\004Msg1\022\t\n\001a\030" +
      "\001 \001(\005\022\t\n\001b\030\002 \001(\005\"\021\n\004Msg2\022\t\n\001c\030\001 \001(\005\"2\n\nN" +
      "ewRideDto\022$\n\004ride\030\001 \001(\0132\026.routeguide.New" +
      "RideDto\"\024\n\002Id\022\016\n\006rideId\030\001 \001(\t2\277\001\n\nRouteG" +
      "uide\0223\n\013SenderTest1\022\020.routeguide.Msg1\032\020." +
      "routeguide.Msg2\"\000\022:\n\016UpdateFollower\022\026.ro" +
      "uteguide.NewRideDto\032\016.routeguide.Id\"\000\022@\n" +
      "\024UpdateRelevantCities\022\026.routeguide.NewRi" +
      "deDto\032\016.routeguide.Id\"\000B$\n\tgeneratedB\017Ro" +
      "uteGuideProtoP\001\242\002\003RTGb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_routeguide_Msg1_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_routeguide_Msg1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_routeguide_Msg1_descriptor,
        new java.lang.String[] { "A", "B", });
    internal_static_routeguide_Msg2_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_routeguide_Msg2_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_routeguide_Msg2_descriptor,
        new java.lang.String[] { "C", });
    internal_static_routeguide_NewRideDto_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_routeguide_NewRideDto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_routeguide_NewRideDto_descriptor,
        new java.lang.String[] { "Ride", });
    internal_static_routeguide_Id_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_routeguide_Id_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_routeguide_Id_descriptor,
        new java.lang.String[] { "RideId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
