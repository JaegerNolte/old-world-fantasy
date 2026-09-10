package net.jaeger.oldworldfantasy.networking.packet;


import io.netty.buffer.ByteBuf;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record ToggleFlightPacket() implements CustomPacketPayload {

    public static final Type<ToggleFlightPacket> TYPE = new Type<>(OldWorldFantasy.res("toggle_flight"));
    public static final ToggleFlightPacket INSTANCE = new ToggleFlightPacket();
    public static final StreamCodec<ByteBuf, ToggleFlightPacket> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
