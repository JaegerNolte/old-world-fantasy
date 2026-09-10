package net.jaeger.oldworldfantasy.networking;

import dev.architectury.networking.NetworkManager;
import net.jaeger.oldworldfantasy.networking.packet.MerchantOfferPacket;
import net.jaeger.oldworldfantasy.networking.packet.SelectMerchantTradePacket;
import net.jaeger.oldworldfantasy.networking.packet.ToggleFlightPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public final class ModNetworking {

    public static void init(){
        NetworkManager.registerReceiver(NetworkManager.c2s(), SelectMerchantTradePacket.TYPE, SelectMerchantTradePacket.STREAM_CODEC, ModPacketHandler::handleSelectMerchantTrade);
        NetworkManager.registerReceiver(NetworkManager.s2c(), MerchantOfferPacket.TYPE, MerchantOfferPacket.STREAM_CODEC, ModPacketHandler::handleMerchantOffers);
        NetworkManager.registerReceiver(NetworkManager.c2s(), ToggleFlightPacket.TYPE, ToggleFlightPacket.STREAM_CODEC, ModPacketHandler::handleToggleFlight);
    }

    public static <T extends CustomPacketPayload> void sendToServer(T packet) {
        NetworkManager.sendToServer(packet);
    }

    public static <T extends CustomPacketPayload> void sendToPlayer(ServerPlayer player, T packet) {
        NetworkManager.sendToPlayer(player, packet);
    }
}


