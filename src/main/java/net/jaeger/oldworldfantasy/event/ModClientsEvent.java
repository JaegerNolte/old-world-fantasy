package net.jaeger.oldworldfantasy.event;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.client.model.ImperialShieldModel;
import net.jaeger.oldworldfantasy.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = OldWorldFantasyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientsEvent {

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(
                ImperialShieldModel.LAYER_LOCATION,
                ImperialShieldModel::createBodyLayer
        );
    }

    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        ItemProperties.register(ModItems.IMPERIAL_SHIELD.get(), ResourceLocation.withDefaultNamespace("blocking"), (itemStack, level, entity, useDur) ->
                entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F
        );
    }
}
