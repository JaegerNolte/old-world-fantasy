package net.jaeger.oldworldfantasy.networking;

import dev.architectury.networking.NetworkManager;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public final class ModNetworking {

    private ModNetworking() {
    }

    public static void init() {

    }

    public static <T extends CustomPacketPayload> void sendToServer(T packet) {
        NetworkManager.sendToServer(packet);
    }

    public static <T extends CustomPacketPayload> void sendToPlayer(ServerPlayer player, T packet) {
        NetworkManager.sendToPlayer(player, packet);
    }
}


