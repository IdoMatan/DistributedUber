package generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.0)",
    comments = "Source: scheme.proto")
public final class RouteGuideGrpc {

  private RouteGuideGrpc() {}

  public static final String SERVICE_NAME = "routeguide.RouteGuide";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.Msg1,
      generated.Msg2> getSenderTest1Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SenderTest1",
      requestType = generated.Msg1.class,
      responseType = generated.Msg2.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.Msg1,
      generated.Msg2> getSenderTest1Method() {
    io.grpc.MethodDescriptor<generated.Msg1, generated.Msg2> getSenderTest1Method;
    if ((getSenderTest1Method = RouteGuideGrpc.getSenderTest1Method) == null) {
      synchronized (RouteGuideGrpc.class) {
        if ((getSenderTest1Method = RouteGuideGrpc.getSenderTest1Method) == null) {
          RouteGuideGrpc.getSenderTest1Method = getSenderTest1Method =
              io.grpc.MethodDescriptor.<generated.Msg1, generated.Msg2>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SenderTest1"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Msg1.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Msg2.getDefaultInstance()))
              .setSchemaDescriptor(new RouteGuideMethodDescriptorSupplier("SenderTest1"))
              .build();
        }
      }
    }
    return getSenderTest1Method;
  }

  private static volatile io.grpc.MethodDescriptor<generated.UpdateNewRideMessage,
      generated.Id> getUpdateFollowerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateFollower",
      requestType = generated.UpdateNewRideMessage.class,
      responseType = generated.Id.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.UpdateNewRideMessage,
      generated.Id> getUpdateFollowerMethod() {
    io.grpc.MethodDescriptor<generated.UpdateNewRideMessage, generated.Id> getUpdateFollowerMethod;
    if ((getUpdateFollowerMethod = RouteGuideGrpc.getUpdateFollowerMethod) == null) {
      synchronized (RouteGuideGrpc.class) {
        if ((getUpdateFollowerMethod = RouteGuideGrpc.getUpdateFollowerMethod) == null) {
          RouteGuideGrpc.getUpdateFollowerMethod = getUpdateFollowerMethod =
              io.grpc.MethodDescriptor.<generated.UpdateNewRideMessage, generated.Id>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateFollower"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.UpdateNewRideMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Id.getDefaultInstance()))
              .setSchemaDescriptor(new RouteGuideMethodDescriptorSupplier("UpdateFollower"))
              .build();
        }
      }
    }
    return getUpdateFollowerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.UpdateNewRideMessage,
      generated.Id> getUpdatePDRideMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updatePDRide",
      requestType = generated.UpdateNewRideMessage.class,
      responseType = generated.Id.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.UpdateNewRideMessage,
      generated.Id> getUpdatePDRideMethod() {
    io.grpc.MethodDescriptor<generated.UpdateNewRideMessage, generated.Id> getUpdatePDRideMethod;
    if ((getUpdatePDRideMethod = RouteGuideGrpc.getUpdatePDRideMethod) == null) {
      synchronized (RouteGuideGrpc.class) {
        if ((getUpdatePDRideMethod = RouteGuideGrpc.getUpdatePDRideMethod) == null) {
          RouteGuideGrpc.getUpdatePDRideMethod = getUpdatePDRideMethod =
              io.grpc.MethodDescriptor.<generated.UpdateNewRideMessage, generated.Id>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updatePDRide"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.UpdateNewRideMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.Id.getDefaultInstance()))
              .setSchemaDescriptor(new RouteGuideMethodDescriptorSupplier("updatePDRide"))
              .build();
        }
      }
    }
    return getUpdatePDRideMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.BookingRequestMessage,
      generated.BookResult> getBookRideMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BookRide",
      requestType = generated.BookingRequestMessage.class,
      responseType = generated.BookResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.BookingRequestMessage,
      generated.BookResult> getBookRideMethod() {
    io.grpc.MethodDescriptor<generated.BookingRequestMessage, generated.BookResult> getBookRideMethod;
    if ((getBookRideMethod = RouteGuideGrpc.getBookRideMethod) == null) {
      synchronized (RouteGuideGrpc.class) {
        if ((getBookRideMethod = RouteGuideGrpc.getBookRideMethod) == null) {
          RouteGuideGrpc.getBookRideMethod = getBookRideMethod =
              io.grpc.MethodDescriptor.<generated.BookingRequestMessage, generated.BookResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BookRide"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.BookingRequestMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.BookResult.getDefaultInstance()))
              .setSchemaDescriptor(new RouteGuideMethodDescriptorSupplier("BookRide"))
              .build();
        }
      }
    }
    return getBookRideMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.BookingRequestMessage,
      generated.BookResult> getBookTripRideMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BookTripRide",
      requestType = generated.BookingRequestMessage.class,
      responseType = generated.BookResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.BookingRequestMessage,
      generated.BookResult> getBookTripRideMethod() {
    io.grpc.MethodDescriptor<generated.BookingRequestMessage, generated.BookResult> getBookTripRideMethod;
    if ((getBookTripRideMethod = RouteGuideGrpc.getBookTripRideMethod) == null) {
      synchronized (RouteGuideGrpc.class) {
        if ((getBookTripRideMethod = RouteGuideGrpc.getBookTripRideMethod) == null) {
          RouteGuideGrpc.getBookTripRideMethod = getBookTripRideMethod =
              io.grpc.MethodDescriptor.<generated.BookingRequestMessage, generated.BookResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BookTripRide"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.BookingRequestMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.BookResult.getDefaultInstance()))
              .setSchemaDescriptor(new RouteGuideMethodDescriptorSupplier("BookTripRide"))
              .build();
        }
      }
    }
    return getBookTripRideMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.BookingRequestMessage,
      generated.BookResult> getUnBookTripRideMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnBookTripRide",
      requestType = generated.BookingRequestMessage.class,
      responseType = generated.BookResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.BookingRequestMessage,
      generated.BookResult> getUnBookTripRideMethod() {
    io.grpc.MethodDescriptor<generated.BookingRequestMessage, generated.BookResult> getUnBookTripRideMethod;
    if ((getUnBookTripRideMethod = RouteGuideGrpc.getUnBookTripRideMethod) == null) {
      synchronized (RouteGuideGrpc.class) {
        if ((getUnBookTripRideMethod = RouteGuideGrpc.getUnBookTripRideMethod) == null) {
          RouteGuideGrpc.getUnBookTripRideMethod = getUnBookTripRideMethod =
              io.grpc.MethodDescriptor.<generated.BookingRequestMessage, generated.BookResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnBookTripRide"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.BookingRequestMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.BookResult.getDefaultInstance()))
              .setSchemaDescriptor(new RouteGuideMethodDescriptorSupplier("UnBookTripRide"))
              .build();
        }
      }
    }
    return getUnBookTripRideMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.BookingApprovalMessage,
      generated.BookResult> getBookTripRideApprovalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BookTripRideApproval",
      requestType = generated.BookingApprovalMessage.class,
      responseType = generated.BookResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.BookingApprovalMessage,
      generated.BookResult> getBookTripRideApprovalMethod() {
    io.grpc.MethodDescriptor<generated.BookingApprovalMessage, generated.BookResult> getBookTripRideApprovalMethod;
    if ((getBookTripRideApprovalMethod = RouteGuideGrpc.getBookTripRideApprovalMethod) == null) {
      synchronized (RouteGuideGrpc.class) {
        if ((getBookTripRideApprovalMethod = RouteGuideGrpc.getBookTripRideApprovalMethod) == null) {
          RouteGuideGrpc.getBookTripRideApprovalMethod = getBookTripRideApprovalMethod =
              io.grpc.MethodDescriptor.<generated.BookingApprovalMessage, generated.BookResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BookTripRideApproval"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.BookingApprovalMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.BookResult.getDefaultInstance()))
              .setSchemaDescriptor(new RouteGuideMethodDescriptorSupplier("BookTripRideApproval"))
              .build();
        }
      }
    }
    return getBookTripRideApprovalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.BookingRequestMessage,
      generated.BookResult> getBookRideInTripMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BookRideInTrip",
      requestType = generated.BookingRequestMessage.class,
      responseType = generated.BookResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.BookingRequestMessage,
      generated.BookResult> getBookRideInTripMethod() {
    io.grpc.MethodDescriptor<generated.BookingRequestMessage, generated.BookResult> getBookRideInTripMethod;
    if ((getBookRideInTripMethod = RouteGuideGrpc.getBookRideInTripMethod) == null) {
      synchronized (RouteGuideGrpc.class) {
        if ((getBookRideInTripMethod = RouteGuideGrpc.getBookRideInTripMethod) == null) {
          RouteGuideGrpc.getBookRideInTripMethod = getBookRideInTripMethod =
              io.grpc.MethodDescriptor.<generated.BookingRequestMessage, generated.BookResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BookRideInTrip"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.BookingRequestMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.BookResult.getDefaultInstance()))
              .setSchemaDescriptor(new RouteGuideMethodDescriptorSupplier("BookRideInTrip"))
              .build();
        }
      }
    }
    return getBookRideInTripMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.LiveMapIsEmptyMessage,
      generated.IsEmptyAgreement> getLiveMapIsEmptyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiveMapIsEmpty",
      requestType = generated.LiveMapIsEmptyMessage.class,
      responseType = generated.IsEmptyAgreement.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.LiveMapIsEmptyMessage,
      generated.IsEmptyAgreement> getLiveMapIsEmptyMethod() {
    io.grpc.MethodDescriptor<generated.LiveMapIsEmptyMessage, generated.IsEmptyAgreement> getLiveMapIsEmptyMethod;
    if ((getLiveMapIsEmptyMethod = RouteGuideGrpc.getLiveMapIsEmptyMethod) == null) {
      synchronized (RouteGuideGrpc.class) {
        if ((getLiveMapIsEmptyMethod = RouteGuideGrpc.getLiveMapIsEmptyMethod) == null) {
          RouteGuideGrpc.getLiveMapIsEmptyMethod = getLiveMapIsEmptyMethod =
              io.grpc.MethodDescriptor.<generated.LiveMapIsEmptyMessage, generated.IsEmptyAgreement>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiveMapIsEmpty"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.LiveMapIsEmptyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.IsEmptyAgreement.getDefaultInstance()))
              .setSchemaDescriptor(new RouteGuideMethodDescriptorSupplier("LiveMapIsEmpty"))
              .build();
        }
      }
    }
    return getLiveMapIsEmptyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generated.CityMessage,
      generated.SyncParam> getGetSyncParamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSyncParam",
      requestType = generated.CityMessage.class,
      responseType = generated.SyncParam.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.CityMessage,
      generated.SyncParam> getGetSyncParamMethod() {
    io.grpc.MethodDescriptor<generated.CityMessage, generated.SyncParam> getGetSyncParamMethod;
    if ((getGetSyncParamMethod = RouteGuideGrpc.getGetSyncParamMethod) == null) {
      synchronized (RouteGuideGrpc.class) {
        if ((getGetSyncParamMethod = RouteGuideGrpc.getGetSyncParamMethod) == null) {
          RouteGuideGrpc.getGetSyncParamMethod = getGetSyncParamMethod =
              io.grpc.MethodDescriptor.<generated.CityMessage, generated.SyncParam>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSyncParam"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.CityMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.SyncParam.getDefaultInstance()))
              .setSchemaDescriptor(new RouteGuideMethodDescriptorSupplier("GetSyncParam"))
              .build();
        }
      }
    }
    return getGetSyncParamMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RouteGuideStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RouteGuideStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RouteGuideStub>() {
        @java.lang.Override
        public RouteGuideStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RouteGuideStub(channel, callOptions);
        }
      };
    return RouteGuideStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RouteGuideBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RouteGuideBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RouteGuideBlockingStub>() {
        @java.lang.Override
        public RouteGuideBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RouteGuideBlockingStub(channel, callOptions);
        }
      };
    return RouteGuideBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RouteGuideFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RouteGuideFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RouteGuideFutureStub>() {
        @java.lang.Override
        public RouteGuideFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RouteGuideFutureStub(channel, callOptions);
        }
      };
    return RouteGuideFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class RouteGuideImplBase implements io.grpc.BindableService {

    /**
     */
    public void senderTest1(generated.Msg1 request,
        io.grpc.stub.StreamObserver<generated.Msg2> responseObserver) {
      asyncUnimplementedUnaryCall(getSenderTest1Method(), responseObserver);
    }

    /**
     */
    public void updateFollower(generated.UpdateNewRideMessage request,
        io.grpc.stub.StreamObserver<generated.Id> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateFollowerMethod(), responseObserver);
    }

    /**
     */
    public void updatePDRide(generated.UpdateNewRideMessage request,
        io.grpc.stub.StreamObserver<generated.Id> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdatePDRideMethod(), responseObserver);
    }

    /**
     */
    public void bookRide(generated.BookingRequestMessage request,
        io.grpc.stub.StreamObserver<generated.BookResult> responseObserver) {
      asyncUnimplementedUnaryCall(getBookRideMethod(), responseObserver);
    }

    /**
     */
    public void bookTripRide(generated.BookingRequestMessage request,
        io.grpc.stub.StreamObserver<generated.BookResult> responseObserver) {
      asyncUnimplementedUnaryCall(getBookTripRideMethod(), responseObserver);
    }

    /**
     */
    public void unBookTripRide(generated.BookingRequestMessage request,
        io.grpc.stub.StreamObserver<generated.BookResult> responseObserver) {
      asyncUnimplementedUnaryCall(getUnBookTripRideMethod(), responseObserver);
    }

    /**
     */
    public void bookTripRideApproval(generated.BookingApprovalMessage request,
        io.grpc.stub.StreamObserver<generated.BookResult> responseObserver) {
      asyncUnimplementedUnaryCall(getBookTripRideApprovalMethod(), responseObserver);
    }

    /**
     */
    public void bookRideInTrip(generated.BookingRequestMessage request,
        io.grpc.stub.StreamObserver<generated.BookResult> responseObserver) {
      asyncUnimplementedUnaryCall(getBookRideInTripMethod(), responseObserver);
    }

    /**
     */
    public void liveMapIsEmpty(generated.LiveMapIsEmptyMessage request,
        io.grpc.stub.StreamObserver<generated.IsEmptyAgreement> responseObserver) {
      asyncUnimplementedUnaryCall(getLiveMapIsEmptyMethod(), responseObserver);
    }

    /**
     */
    public void getSyncParam(generated.CityMessage request,
        io.grpc.stub.StreamObserver<generated.SyncParam> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSyncParamMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSenderTest1Method(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.Msg1,
                generated.Msg2>(
                  this, METHODID_SENDER_TEST1)))
          .addMethod(
            getUpdateFollowerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.UpdateNewRideMessage,
                generated.Id>(
                  this, METHODID_UPDATE_FOLLOWER)))
          .addMethod(
            getUpdatePDRideMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.UpdateNewRideMessage,
                generated.Id>(
                  this, METHODID_UPDATE_PDRIDE)))
          .addMethod(
            getBookRideMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.BookingRequestMessage,
                generated.BookResult>(
                  this, METHODID_BOOK_RIDE)))
          .addMethod(
            getBookTripRideMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.BookingRequestMessage,
                generated.BookResult>(
                  this, METHODID_BOOK_TRIP_RIDE)))
          .addMethod(
            getUnBookTripRideMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.BookingRequestMessage,
                generated.BookResult>(
                  this, METHODID_UN_BOOK_TRIP_RIDE)))
          .addMethod(
            getBookTripRideApprovalMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.BookingApprovalMessage,
                generated.BookResult>(
                  this, METHODID_BOOK_TRIP_RIDE_APPROVAL)))
          .addMethod(
            getBookRideInTripMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.BookingRequestMessage,
                generated.BookResult>(
                  this, METHODID_BOOK_RIDE_IN_TRIP)))
          .addMethod(
            getLiveMapIsEmptyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.LiveMapIsEmptyMessage,
                generated.IsEmptyAgreement>(
                  this, METHODID_LIVE_MAP_IS_EMPTY)))
          .addMethod(
            getGetSyncParamMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.CityMessage,
                generated.SyncParam>(
                  this, METHODID_GET_SYNC_PARAM)))
          .build();
    }
  }

  /**
   */
  public static final class RouteGuideStub extends io.grpc.stub.AbstractAsyncStub<RouteGuideStub> {
    private RouteGuideStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RouteGuideStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RouteGuideStub(channel, callOptions);
    }

    /**
     */
    public void senderTest1(generated.Msg1 request,
        io.grpc.stub.StreamObserver<generated.Msg2> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSenderTest1Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateFollower(generated.UpdateNewRideMessage request,
        io.grpc.stub.StreamObserver<generated.Id> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateFollowerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updatePDRide(generated.UpdateNewRideMessage request,
        io.grpc.stub.StreamObserver<generated.Id> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdatePDRideMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void bookRide(generated.BookingRequestMessage request,
        io.grpc.stub.StreamObserver<generated.BookResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBookRideMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void bookTripRide(generated.BookingRequestMessage request,
        io.grpc.stub.StreamObserver<generated.BookResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBookTripRideMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unBookTripRide(generated.BookingRequestMessage request,
        io.grpc.stub.StreamObserver<generated.BookResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnBookTripRideMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void bookTripRideApproval(generated.BookingApprovalMessage request,
        io.grpc.stub.StreamObserver<generated.BookResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBookTripRideApprovalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void bookRideInTrip(generated.BookingRequestMessage request,
        io.grpc.stub.StreamObserver<generated.BookResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBookRideInTripMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void liveMapIsEmpty(generated.LiveMapIsEmptyMessage request,
        io.grpc.stub.StreamObserver<generated.IsEmptyAgreement> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLiveMapIsEmptyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSyncParam(generated.CityMessage request,
        io.grpc.stub.StreamObserver<generated.SyncParam> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSyncParamMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RouteGuideBlockingStub extends io.grpc.stub.AbstractBlockingStub<RouteGuideBlockingStub> {
    private RouteGuideBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RouteGuideBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RouteGuideBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.Msg2 senderTest1(generated.Msg1 request) {
      return blockingUnaryCall(
          getChannel(), getSenderTest1Method(), getCallOptions(), request);
    }

    /**
     */
    public generated.Id updateFollower(generated.UpdateNewRideMessage request) {
      return blockingUnaryCall(
          getChannel(), getUpdateFollowerMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.Id updatePDRide(generated.UpdateNewRideMessage request) {
      return blockingUnaryCall(
          getChannel(), getUpdatePDRideMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.BookResult bookRide(generated.BookingRequestMessage request) {
      return blockingUnaryCall(
          getChannel(), getBookRideMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.BookResult bookTripRide(generated.BookingRequestMessage request) {
      return blockingUnaryCall(
          getChannel(), getBookTripRideMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.BookResult unBookTripRide(generated.BookingRequestMessage request) {
      return blockingUnaryCall(
          getChannel(), getUnBookTripRideMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.BookResult bookTripRideApproval(generated.BookingApprovalMessage request) {
      return blockingUnaryCall(
          getChannel(), getBookTripRideApprovalMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.BookResult bookRideInTrip(generated.BookingRequestMessage request) {
      return blockingUnaryCall(
          getChannel(), getBookRideInTripMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.IsEmptyAgreement liveMapIsEmpty(generated.LiveMapIsEmptyMessage request) {
      return blockingUnaryCall(
          getChannel(), getLiveMapIsEmptyMethod(), getCallOptions(), request);
    }

    /**
     */
    public generated.SyncParam getSyncParam(generated.CityMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetSyncParamMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RouteGuideFutureStub extends io.grpc.stub.AbstractFutureStub<RouteGuideFutureStub> {
    private RouteGuideFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RouteGuideFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RouteGuideFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Msg2> senderTest1(
        generated.Msg1 request) {
      return futureUnaryCall(
          getChannel().newCall(getSenderTest1Method(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Id> updateFollower(
        generated.UpdateNewRideMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateFollowerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.Id> updatePDRide(
        generated.UpdateNewRideMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdatePDRideMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.BookResult> bookRide(
        generated.BookingRequestMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getBookRideMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.BookResult> bookTripRide(
        generated.BookingRequestMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getBookTripRideMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.BookResult> unBookTripRide(
        generated.BookingRequestMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getUnBookTripRideMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.BookResult> bookTripRideApproval(
        generated.BookingApprovalMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getBookTripRideApprovalMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.BookResult> bookRideInTrip(
        generated.BookingRequestMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getBookRideInTripMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.IsEmptyAgreement> liveMapIsEmpty(
        generated.LiveMapIsEmptyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getLiveMapIsEmptyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.SyncParam> getSyncParam(
        generated.CityMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSyncParamMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SENDER_TEST1 = 0;
  private static final int METHODID_UPDATE_FOLLOWER = 1;
  private static final int METHODID_UPDATE_PDRIDE = 2;
  private static final int METHODID_BOOK_RIDE = 3;
  private static final int METHODID_BOOK_TRIP_RIDE = 4;
  private static final int METHODID_UN_BOOK_TRIP_RIDE = 5;
  private static final int METHODID_BOOK_TRIP_RIDE_APPROVAL = 6;
  private static final int METHODID_BOOK_RIDE_IN_TRIP = 7;
  private static final int METHODID_LIVE_MAP_IS_EMPTY = 8;
  private static final int METHODID_GET_SYNC_PARAM = 9;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RouteGuideImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RouteGuideImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SENDER_TEST1:
          serviceImpl.senderTest1((generated.Msg1) request,
              (io.grpc.stub.StreamObserver<generated.Msg2>) responseObserver);
          break;
        case METHODID_UPDATE_FOLLOWER:
          serviceImpl.updateFollower((generated.UpdateNewRideMessage) request,
              (io.grpc.stub.StreamObserver<generated.Id>) responseObserver);
          break;
        case METHODID_UPDATE_PDRIDE:
          serviceImpl.updatePDRide((generated.UpdateNewRideMessage) request,
              (io.grpc.stub.StreamObserver<generated.Id>) responseObserver);
          break;
        case METHODID_BOOK_RIDE:
          serviceImpl.bookRide((generated.BookingRequestMessage) request,
              (io.grpc.stub.StreamObserver<generated.BookResult>) responseObserver);
          break;
        case METHODID_BOOK_TRIP_RIDE:
          serviceImpl.bookTripRide((generated.BookingRequestMessage) request,
              (io.grpc.stub.StreamObserver<generated.BookResult>) responseObserver);
          break;
        case METHODID_UN_BOOK_TRIP_RIDE:
          serviceImpl.unBookTripRide((generated.BookingRequestMessage) request,
              (io.grpc.stub.StreamObserver<generated.BookResult>) responseObserver);
          break;
        case METHODID_BOOK_TRIP_RIDE_APPROVAL:
          serviceImpl.bookTripRideApproval((generated.BookingApprovalMessage) request,
              (io.grpc.stub.StreamObserver<generated.BookResult>) responseObserver);
          break;
        case METHODID_BOOK_RIDE_IN_TRIP:
          serviceImpl.bookRideInTrip((generated.BookingRequestMessage) request,
              (io.grpc.stub.StreamObserver<generated.BookResult>) responseObserver);
          break;
        case METHODID_LIVE_MAP_IS_EMPTY:
          serviceImpl.liveMapIsEmpty((generated.LiveMapIsEmptyMessage) request,
              (io.grpc.stub.StreamObserver<generated.IsEmptyAgreement>) responseObserver);
          break;
        case METHODID_GET_SYNC_PARAM:
          serviceImpl.getSyncParam((generated.CityMessage) request,
              (io.grpc.stub.StreamObserver<generated.SyncParam>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RouteGuideBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RouteGuideBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.RouteGuideProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RouteGuide");
    }
  }

  private static final class RouteGuideFileDescriptorSupplier
      extends RouteGuideBaseDescriptorSupplier {
    RouteGuideFileDescriptorSupplier() {}
  }

  private static final class RouteGuideMethodDescriptorSupplier
      extends RouteGuideBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RouteGuideMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RouteGuideGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RouteGuideFileDescriptorSupplier())
              .addMethod(getSenderTest1Method())
              .addMethod(getUpdateFollowerMethod())
              .addMethod(getUpdatePDRideMethod())
              .addMethod(getBookRideMethod())
              .addMethod(getBookTripRideMethod())
              .addMethod(getUnBookTripRideMethod())
              .addMethod(getBookTripRideApprovalMethod())
              .addMethod(getBookRideInTripMethod())
              .addMethod(getLiveMapIsEmptyMethod())
              .addMethod(getGetSyncParamMethod())
              .build();
        }
      }
    }
    return result;
  }
}
