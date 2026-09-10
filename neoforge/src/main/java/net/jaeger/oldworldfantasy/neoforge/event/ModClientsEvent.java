package net.jaeger.oldworldfantasy.neoforge.event;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.client.gui.screens.inventory.ModMerchantScreen;
import net.jaeger.oldworldfantasy.client.gui.screens.saddle.GriffonScreen;
import net.jaeger.oldworldfantasy.client.model.ModModels;
import net.jaeger.oldworldfantasy.event.ModClient;
import net.jaeger.oldworldfantasy.item.ModItems;
import net.jaeger.oldworldfantasy.neoforge.client.ModClientItemExtensions;
import net.jaeger.oldworldfantasy.world.inventory.ModMenus;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = OldWorldFantasy.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientsEvent {

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new ModClientItemExtensions(), ModItems.IMPERIAL_SHIELD.get());
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModModels.INSTANCE.layers.forEach(event::registerLayerDefinition);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.MERCHANT.get(), ModMerchantScreen::new);
        event.register(ModMenus.GRIFFON.get(), GriffonScreen::new);
    }

    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(ModClient::init);
    }
}
