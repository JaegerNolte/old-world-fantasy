package net.jaeger.oldworldfantasy.neoforge.event;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.client.gui.screens.inventory.ModMerchantScreen;
import net.jaeger.oldworldfantasy.client.gui.screens.saddle.GriffonScreen;
import net.jaeger.oldworldfantasy.client.keyMapping.ModKeyMappings;
import net.jaeger.oldworldfantasy.client.model.ModModels;
import net.jaeger.oldworldfantasy.event.ModClient;
import net.jaeger.oldworldfantasy.item.ModItems;
import net.jaeger.oldworldfantasy.neoforge.client.ModClientItemExtensions;
import net.jaeger.oldworldfantasy.networking.ModNetworking;
import net.jaeger.oldworldfantasy.networking.packet.ToggleFlightPacket;
import net.jaeger.oldworldfantasy.world.inventory.ModMenus;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = OldWorldFantasy.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientsEvent {

    ModClientsEvent(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        ModKeyMappings.init();
    }

    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(ModClient::init);
    }

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
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ModKeyMappings.PRESS_TOGGLE_FLIGHT.get());
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        while (ModKeyMappings.PRESS_TOGGLE_FLIGHT.get().consumeClick()){
            if (Minecraft.getInstance().player != null) {
                OldWorldFantasy.LOG.info("TOGGLE_FLIGHT Pressed");
                ModNetworking.sendToServer(ToggleFlightPacket.INSTANCE);
            }
        }
    }
}
