package net.jaeger.oldworldfantasy.event;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jaeger.oldworldfantasy.item.ModItems;
import net.jaeger.oldworldfantasy.item.items.shield.ModShieldItem;

@Environment(EnvType.CLIENT)
public class ModClient {

    public static void init() {

        ModItems.ITEMS.forEach(item -> {
            if (item instanceof ModShieldItem shield) {
                shield.registerModelProperty();
            }
        });
    }
}
