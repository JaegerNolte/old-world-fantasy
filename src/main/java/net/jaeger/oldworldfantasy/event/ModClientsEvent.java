package net.jaeger.oldworldfantasy.event;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.client.model.ImperialShieldModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OldWorldFantasyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientsEvent {

//    @SubscribeEvent
//    public static void onComputerFovModifierEvent(ComputeFovModifierEvent event) {
//        if(event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() == ModItems.BLUNDERBUSS.get()) {
//            float fovModifier = 1f;
//            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
//            float deltaTicks = (float)ticksUsingItem / 20f;
//            if(deltaTicks > 1f) {
//                deltaTicks = 1f;
//            } else {
//                deltaTicks *= deltaTicks;
//            }
//            fovModifier *= 1f - deltaTicks * 0.15f;
//            event.setNewFovModifier(fovModifier);
//        }
//    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(
                ImperialShieldModel.LAYER_LOCATION,
                ImperialShieldModel::createBodyLayer
        );
    }
}
