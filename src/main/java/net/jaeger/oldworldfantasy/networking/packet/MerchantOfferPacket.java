package net.jaeger.oldworldfantasy.networking.packet;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.trading.MerchantOffers;

public record MerchantOfferPacket(
        int containerId,
        MerchantOffers offers,
        int level,
        int xp,
        boolean showProgress,
        boolean canRestock
) {
    public static final StreamCodec<RegistryFriendlyByteBuf, MerchantOfferPacket> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT,
                    MerchantOfferPacket::containerId,

                    MerchantOffers.STREAM_CODEC,
                    MerchantOfferPacket::offers,

                    ByteBufCodecs.VAR_INT,
                    MerchantOfferPacket::level,

                    ByteBufCodecs.VAR_INT,
                    MerchantOfferPacket::xp,

                    ByteBufCodecs.BOOL,
                    MerchantOfferPacket::showProgress,

                    ByteBufCodecs.BOOL,
                    MerchantOfferPacket::canRestock,

                    MerchantOfferPacket::new
            );
}

