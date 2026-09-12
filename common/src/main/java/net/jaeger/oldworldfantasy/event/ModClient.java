package net.jaeger.oldworldfantasy.event;

import net.jaeger.oldworldfantasy.item.ModItems;
import net.jaeger.oldworldfantasy.item.items.shield.ModShieldItem;

public class ModClient {

    public static void init() {
        ModItems.ITEMS.getRegistrar().forEach(item -> {
            if (item instanceof ModShieldItem shield) {
                shield.registerModelProperty();
            }
        });
    }
}
