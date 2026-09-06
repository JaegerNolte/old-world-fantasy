package net.jaeger.oldworldfantasy;

import com.mojang.logging.LogUtils;
import net.jaeger.oldworldfantasy.block.ModBlocks;
import net.jaeger.oldworldfantasy.component.ModDataComponents;
import net.jaeger.oldworldfantasy.effect.ModEffects;
import net.jaeger.oldworldfantasy.entity.ModEntities;
import net.jaeger.oldworldfantasy.item.ModCreativeModeTabs;
import net.jaeger.oldworldfantasy.item.ModItems;
import net.jaeger.oldworldfantasy.sound.ModSounds;
import net.jaeger.oldworldfantasy.world.inventory.ModMenus;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public final class OldWorldFantasy {

    public static final String MOD_ID = "oldworldfantasy";

    public static ResourceLocation res(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

    public static final Logger LOG = LogUtils.getLogger();

    public static void init() {
        ModCreativeModeTabs.init();

        ModEffects.init();
        ModDataComponents.init();

        ModBlocks.init();
        ModItems.init();

        ModSounds.init();
        ModEntities.init();

        ModMenus.init();
        LOG.info("Initializing {}", MOD_ID);
    }
}
