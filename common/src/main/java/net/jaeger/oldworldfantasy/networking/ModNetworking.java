package net.jaeger.oldworldfantasy.networking;

import dev.architectury.networking.NetworkManager;
import net.jaeger.oldworldfantasy.networking.packet.MerchantOfferPacket;
import net.jaeger.oldworldfantasy.networking.packet.SelectMerchantTradePacket;
import net.jaeger.oldworldfantasy.world.inventory.ModMerchantMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public final class ModNetworking {

    public static void init(){
        NetworkManager.registerReceiver(NetworkManager.c2s(), SelectMerchantTradePacket.TYPE, SelectMerchantTradePacket.STREAM_CODEC, ModNetworking::handleSelectMerchantTrade);
        NetworkManager.registerReceiver(NetworkManager.s2c(), MerchantOfferPacket.TYPE, MerchantOfferPacket.STREAM_CODEC, ModNetworking::handleMerchantOffers);
    }

    private static void handleMerchantOffers(MerchantOfferPacket packet, NetworkManager.PacketContext context) {
        context.queue(() -> {
            handleMerchantOffersClient(packet);
        });
    }

    private static void handleSelectMerchantTrade(SelectMerchantTradePacket packet, NetworkManager.PacketContext context) {
        context.queue(() -> {
            Player player = context.getPlayer();

            if (player == null) {
                return;
            }

            if (player.containerMenu instanceof ModMerchantMenu merchantMenu) {
                merchantMenu.setSelectionHint(packet.tradeIndex());
            }
        });
    }

    private static void handleMerchantOffersClient(MerchantOfferPacket packet) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        AbstractContainerMenu menu = minecraft.player.containerMenu;
        if (packet.containerId() == menu.containerId && menu instanceof ModMerchantMenu merchantMenu) {

            merchantMenu.setOffers(packet.offers());
            merchantMenu.setXp(packet.xp());
            merchantMenu.setMerchantLevel(packet.level());
            merchantMenu.setShowProgressBar(packet.showProgress());
            merchantMenu.setCanRestock(packet.canRestock());
        }
    }

    public static <T extends CustomPacketPayload> void sendToServer(T packet) {
        NetworkManager.sendToServer(packet);
    }

    public static <T extends CustomPacketPayload> void sendToPlayer(ServerPlayer player, T packet) {
        NetworkManager.sendToPlayer(player, packet);
    }
}


