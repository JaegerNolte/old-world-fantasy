package net.jaeger.oldworldfantasy.networking.packet;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;


public record SelectMerchantTradePacket(int tradeIndex) implements CustomPacketPayload {

    public static final Type<SelectMerchantTradePacket> TYPE = new Type<>(OldWorldFantasy.res("select_merchant_trade"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SelectMerchantTradePacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            SelectMerchantTradePacket::tradeIndex,
            SelectMerchantTradePacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

