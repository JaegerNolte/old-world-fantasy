package net.jaeger.oldworldfantasy.networking;

import dev.architectury.networking.NetworkManager;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.griffons.AbstractGriffon;
import net.jaeger.oldworldfantasy.networking.packet.MerchantOfferPacket;
import net.jaeger.oldworldfantasy.networking.packet.SelectMerchantTradePacket;
import net.jaeger.oldworldfantasy.networking.packet.ToggleFlightPacket;
import net.jaeger.oldworldfantasy.world.inventory.merchant.ModMerchantMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class ModPacketHandler {

    public static void handleMerchantOffers(MerchantOfferPacket packet, NetworkManager.PacketContext context) {
        context.queue(() -> {
            handleMerchantOffersClient(packet);
        });
    }

    public static void handleSelectMerchantTrade(SelectMerchantTradePacket packet, NetworkManager.PacketContext context) {
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

    public static void handleMerchantOffersClient(MerchantOfferPacket packet) {
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

    public static void handleToggleFlight(ToggleFlightPacket packet, NetworkManager.PacketContext context) {
        context.queue(() -> {
            Player player = context.getPlayer();

            if (player == null) {
                return;
            }

            if (player.getVehicle() instanceof AbstractGriffon griffon && griffon.isOwnedBy(player) && griffon.isSaddled()) {
                boolean flying = !griffon.isFlying();
                griffon.setFlying(flying);
            }
        });
    }
}
