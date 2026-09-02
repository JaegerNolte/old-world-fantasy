package net.jaeger.oldworldfantasy.networking.packet;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.world.inventory.ModMerchantMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class SelectMerchantTradePacket {

    private final int tradeIndex;

    public SelectMerchantTradePacket(int tradeIndex) {
        this.tradeIndex = tradeIndex;
    }

    public SelectMerchantTradePacket(FriendlyByteBuf buf) {
        this.tradeIndex = buf.readVarInt();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeVarInt(this.tradeIndex);
    }

    public static SelectMerchantTradePacket decode(FriendlyByteBuf buf) {
        return new SelectMerchantTradePacket(buf);
    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {

            ServerPlayer player = context.getSender();
            if (player == null) {
                OldWorldFantasyMod.LOG.warn("SelectMerchantTradePacket received without server player");
                return;
            }

            AbstractContainerMenu container = player.containerMenu;
            OldWorldFantasyMod.LOG.info("Server received merchant selection: {}", this.tradeIndex);
            if (container instanceof ModMerchantMenu merchantMenu) {

                if (this.tradeIndex >= 0 && this.tradeIndex < merchantMenu.getOffers().size()) {

                    OldWorldFantasyMod.LOG.info("Setting merchant selection to {}", this.tradeIndex);
                    merchantMenu.setSelectionHint(this.tradeIndex);

                } else {
                    OldWorldFantasyMod.LOG.warn("Invalid merchant selection {}. Offer count: {}", this.tradeIndex, merchantMenu.getOffers().size());
                }

            } else {
                OldWorldFantasyMod.LOG.warn("Player container is not ModMerchantMenu: {}", container.getClass().getName());
            }
        });
        context.setPacketHandled(true);
    }
}

