// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: scheme.proto

package generated;

/**
 * Protobuf type {@code routeguide.UpdateNewRideMessage}
 */
public final class UpdateNewRideMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:routeguide.UpdateNewRideMessage)
    UpdateNewRideMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UpdateNewRideMessage.newBuilder() to construct.
  private UpdateNewRideMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UpdateNewRideMessage() {
    addressedTo_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new UpdateNewRideMessage();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UpdateNewRideMessage(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            generated.RideProto.Builder subBuilder = null;
            if (ride_ != null) {
              subBuilder = ride_.toBuilder();
            }
            ride_ = input.readMessage(generated.RideProto.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(ride_);
              ride_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            addressedTo_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return generated.RouteGuideProto.internal_static_routeguide_UpdateNewRideMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return generated.RouteGuideProto.internal_static_routeguide_UpdateNewRideMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            generated.UpdateNewRideMessage.class, generated.UpdateNewRideMessage.Builder.class);
  }

  public static final int RIDE_FIELD_NUMBER = 1;
  private generated.RideProto ride_;
  /**
   * <code>.routeguide.RideProto ride = 1;</code>
   * @return Whether the ride field is set.
   */
  @java.lang.Override
  public boolean hasRide() {
    return ride_ != null;
  }
  /**
   * <code>.routeguide.RideProto ride = 1;</code>
   * @return The ride.
   */
  @java.lang.Override
  public generated.RideProto getRide() {
    return ride_ == null ? generated.RideProto.getDefaultInstance() : ride_;
  }
  /**
   * <code>.routeguide.RideProto ride = 1;</code>
   */
  @java.lang.Override
  public generated.RideProtoOrBuilder getRideOrBuilder() {
    return getRide();
  }

  public static final int ADDRESSEDTO_FIELD_NUMBER = 2;
  private volatile java.lang.Object addressedTo_;
  /**
   * <code>string AddressedTo = 2;</code>
   * @return The addressedTo.
   */
  @java.lang.Override
  public java.lang.String getAddressedTo() {
    java.lang.Object ref = addressedTo_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      addressedTo_ = s;
      return s;
    }
  }
  /**
   * <code>string AddressedTo = 2;</code>
   * @return The bytes for addressedTo.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getAddressedToBytes() {
    java.lang.Object ref = addressedTo_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      addressedTo_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (ride_ != null) {
      output.writeMessage(1, getRide());
    }
    if (!getAddressedToBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, addressedTo_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (ride_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getRide());
    }
    if (!getAddressedToBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, addressedTo_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof generated.UpdateNewRideMessage)) {
      return super.equals(obj);
    }
    generated.UpdateNewRideMessage other = (generated.UpdateNewRideMessage) obj;

    if (hasRide() != other.hasRide()) return false;
    if (hasRide()) {
      if (!getRide()
          .equals(other.getRide())) return false;
    }
    if (!getAddressedTo()
        .equals(other.getAddressedTo())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasRide()) {
      hash = (37 * hash) + RIDE_FIELD_NUMBER;
      hash = (53 * hash) + getRide().hashCode();
    }
    hash = (37 * hash) + ADDRESSEDTO_FIELD_NUMBER;
    hash = (53 * hash) + getAddressedTo().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static generated.UpdateNewRideMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.UpdateNewRideMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.UpdateNewRideMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.UpdateNewRideMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.UpdateNewRideMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static generated.UpdateNewRideMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static generated.UpdateNewRideMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.UpdateNewRideMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.UpdateNewRideMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static generated.UpdateNewRideMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static generated.UpdateNewRideMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static generated.UpdateNewRideMessage parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(generated.UpdateNewRideMessage prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code routeguide.UpdateNewRideMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:routeguide.UpdateNewRideMessage)
      generated.UpdateNewRideMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return generated.RouteGuideProto.internal_static_routeguide_UpdateNewRideMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return generated.RouteGuideProto.internal_static_routeguide_UpdateNewRideMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              generated.UpdateNewRideMessage.class, generated.UpdateNewRideMessage.Builder.class);
    }

    // Construct using generated.UpdateNewRideMessage.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (rideBuilder_ == null) {
        ride_ = null;
      } else {
        ride_ = null;
        rideBuilder_ = null;
      }
      addressedTo_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return generated.RouteGuideProto.internal_static_routeguide_UpdateNewRideMessage_descriptor;
    }

    @java.lang.Override
    public generated.UpdateNewRideMessage getDefaultInstanceForType() {
      return generated.UpdateNewRideMessage.getDefaultInstance();
    }

    @java.lang.Override
    public generated.UpdateNewRideMessage build() {
      generated.UpdateNewRideMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public generated.UpdateNewRideMessage buildPartial() {
      generated.UpdateNewRideMessage result = new generated.UpdateNewRideMessage(this);
      if (rideBuilder_ == null) {
        result.ride_ = ride_;
      } else {
        result.ride_ = rideBuilder_.build();
      }
      result.addressedTo_ = addressedTo_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof generated.UpdateNewRideMessage) {
        return mergeFrom((generated.UpdateNewRideMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(generated.UpdateNewRideMessage other) {
      if (other == generated.UpdateNewRideMessage.getDefaultInstance()) return this;
      if (other.hasRide()) {
        mergeRide(other.getRide());
      }
      if (!other.getAddressedTo().isEmpty()) {
        addressedTo_ = other.addressedTo_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      generated.UpdateNewRideMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (generated.UpdateNewRideMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private generated.RideProto ride_;
    private com.google.protobuf.SingleFieldBuilderV3<
        generated.RideProto, generated.RideProto.Builder, generated.RideProtoOrBuilder> rideBuilder_;
    /**
     * <code>.routeguide.RideProto ride = 1;</code>
     * @return Whether the ride field is set.
     */
    public boolean hasRide() {
      return rideBuilder_ != null || ride_ != null;
    }
    /**
     * <code>.routeguide.RideProto ride = 1;</code>
     * @return The ride.
     */
    public generated.RideProto getRide() {
      if (rideBuilder_ == null) {
        return ride_ == null ? generated.RideProto.getDefaultInstance() : ride_;
      } else {
        return rideBuilder_.getMessage();
      }
    }
    /**
     * <code>.routeguide.RideProto ride = 1;</code>
     */
    public Builder setRide(generated.RideProto value) {
      if (rideBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ride_ = value;
        onChanged();
      } else {
        rideBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.routeguide.RideProto ride = 1;</code>
     */
    public Builder setRide(
        generated.RideProto.Builder builderForValue) {
      if (rideBuilder_ == null) {
        ride_ = builderForValue.build();
        onChanged();
      } else {
        rideBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.routeguide.RideProto ride = 1;</code>
     */
    public Builder mergeRide(generated.RideProto value) {
      if (rideBuilder_ == null) {
        if (ride_ != null) {
          ride_ =
            generated.RideProto.newBuilder(ride_).mergeFrom(value).buildPartial();
        } else {
          ride_ = value;
        }
        onChanged();
      } else {
        rideBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.routeguide.RideProto ride = 1;</code>
     */
    public Builder clearRide() {
      if (rideBuilder_ == null) {
        ride_ = null;
        onChanged();
      } else {
        ride_ = null;
        rideBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.routeguide.RideProto ride = 1;</code>
     */
    public generated.RideProto.Builder getRideBuilder() {
      
      onChanged();
      return getRideFieldBuilder().getBuilder();
    }
    /**
     * <code>.routeguide.RideProto ride = 1;</code>
     */
    public generated.RideProtoOrBuilder getRideOrBuilder() {
      if (rideBuilder_ != null) {
        return rideBuilder_.getMessageOrBuilder();
      } else {
        return ride_ == null ?
            generated.RideProto.getDefaultInstance() : ride_;
      }
    }
    /**
     * <code>.routeguide.RideProto ride = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        generated.RideProto, generated.RideProto.Builder, generated.RideProtoOrBuilder> 
        getRideFieldBuilder() {
      if (rideBuilder_ == null) {
        rideBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            generated.RideProto, generated.RideProto.Builder, generated.RideProtoOrBuilder>(
                getRide(),
                getParentForChildren(),
                isClean());
        ride_ = null;
      }
      return rideBuilder_;
    }

    private java.lang.Object addressedTo_ = "";
    /**
     * <code>string AddressedTo = 2;</code>
     * @return The addressedTo.
     */
    public java.lang.String getAddressedTo() {
      java.lang.Object ref = addressedTo_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        addressedTo_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string AddressedTo = 2;</code>
     * @return The bytes for addressedTo.
     */
    public com.google.protobuf.ByteString
        getAddressedToBytes() {
      java.lang.Object ref = addressedTo_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        addressedTo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string AddressedTo = 2;</code>
     * @param value The addressedTo to set.
     * @return This builder for chaining.
     */
    public Builder setAddressedTo(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      addressedTo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string AddressedTo = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearAddressedTo() {
      
      addressedTo_ = getDefaultInstance().getAddressedTo();
      onChanged();
      return this;
    }
    /**
     * <code>string AddressedTo = 2;</code>
     * @param value The bytes for addressedTo to set.
     * @return This builder for chaining.
     */
    public Builder setAddressedToBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      addressedTo_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:routeguide.UpdateNewRideMessage)
  }

  // @@protoc_insertion_point(class_scope:routeguide.UpdateNewRideMessage)
  private static final generated.UpdateNewRideMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new generated.UpdateNewRideMessage();
  }

  public static generated.UpdateNewRideMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UpdateNewRideMessage>
      PARSER = new com.google.protobuf.AbstractParser<UpdateNewRideMessage>() {
    @java.lang.Override
    public UpdateNewRideMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UpdateNewRideMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UpdateNewRideMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UpdateNewRideMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public generated.UpdateNewRideMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

