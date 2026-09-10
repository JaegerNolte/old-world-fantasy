package net.jaeger.oldworldfantasy.event;

import dev.architectury.event.events.client.ClientTickEvent;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.client.keyMapping.ModKeyBinds;
import net.jaeger.oldworldfantasy.item.ModItems;
import net.jaeger.oldworldfantasy.item.items.shield.ModShieldItem;
import net.jaeger.oldworldfantasy.networking.ModNetworking;
import net.jaeger.oldworldfantasy.networking.packet.ToggleFlightPacket;

public class ModClient {

    public static void init() {
        ModItems.ITEMS.getRegistrar().forEach(item -> {
            if (item instanceof ModShieldItem shield) {
                shield.registerModelProperty();
            }
        });

        ClientTickEvent.CLIENT_POST.register(minecraft -> {

            while (ModKeyBinds.TOGGLE_FLIGHT.consumeClick()) {
                if (minecraft.player != null) {
                    OldWorldFantasy.LOG.info("TOGGLE_FLIGHT Pressed");
                    ModNetworking.sendToServer(ToggleFlightPacket.INSTANCE);
                }
            }
        });
    }
}
