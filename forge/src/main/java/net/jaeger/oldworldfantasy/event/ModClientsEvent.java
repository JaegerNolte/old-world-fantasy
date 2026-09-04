package net.jaeger.oldworldfantasy.event;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.client.gui.screens.inventory.ModMerchantScreen;
import net.jaeger.oldworldfantasy.client.model.ModModels;
import net.jaeger.oldworldfantasy.item.ModItems;
import net.jaeger.oldworldfantasy.item.custom.items.shield.ModShieldItem;
import net.jaeger.oldworldfantasy.world.inventory.ModMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = OldWorldFantasyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientsEvent {

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModModels.INSTANCE.layers.forEach(event::registerLayerDefinition);
    }

    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ModItems.ITEMS.getEntries().forEach(entry -> {
                Item item = entry.get();

                if (item instanceof ModShieldItem shield) {
                    shield.registerModelProperty();
                }
            });
            MenuScreens.register(
                    ModMenus.MERCHANT.get(),
                    ModMerchantScreen::new
            );
        });
    }
}
