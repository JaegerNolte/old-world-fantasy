package net.jaeger.oldworldfantasy.networking;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.networking.packet.MerchantOfferPacket;
import net.jaeger.oldworldfantasy.networking.packet.SelectMerchantTradePacket;
import net.jaeger.oldworldfantasy.world.inventory.ModMerchantMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.*;

public class ModNetworking {

    public static SimpleChannel INSTANCE;
    private static final int PROTOCOL_VERSION = 1;
    private static int packetId;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        INSTANCE = ChannelBuilder
                .named(OldWorldFantasyMod.res("main"))
                .networkProtocolVersion(PROTOCOL_VERSION)
                .clientAcceptedVersions(Channel.VersionTest.exact(PROTOCOL_VERSION))
                .serverAcceptedVersions(Channel.VersionTest.exact(PROTOCOL_VERSION))
                .simpleChannel();

        INSTANCE.messageBuilder(SelectMerchantTradePacket.class, id())
                .encoder(SelectMerchantTradePacket::encode)
                .decoder(SelectMerchantTradePacket::decode)
                .consumerMainThread(SelectMerchantTradePacket::handle)
                .add();

        INSTANCE.messageBuilder(MerchantOfferPacket.class, id(),
                        NetworkDirection.PLAY_TO_CLIENT
                )
                .codec(MerchantOfferPacket.STREAM_CODEC)
                .consumerMainThread((packet, context) -> {
                    handleMerchantOffers(packet, context);
                })
                .add();
    }

    private static void handleMerchantOffers(MerchantOfferPacket packet, CustomPayloadEvent.Context context) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        AbstractContainerMenu menu = minecraft.player.containerMenu;

        if (packet.containerId() == menu.containerId
                && menu instanceof ModMerchantMenu merchantMenu) {

            merchantMenu.setOffers(packet.offers());
            merchantMenu.setXp(packet.xp());
            merchantMenu.setMerchantLevel(packet.level());
            merchantMenu.setShowProgressBar(packet.showProgress());
            merchantMenu.setCanRestock(packet.canRestock());
        }
    }

    public static void sendToServer(Object message) {
        INSTANCE.send(message, PacketDistributor.SERVER.noArg());
    }
}


