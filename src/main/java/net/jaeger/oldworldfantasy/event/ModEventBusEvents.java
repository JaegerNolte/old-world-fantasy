package net.jaeger.oldworldfantasy.event;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.ModEntities;
import net.jaeger.oldworldfantasy.entity.client.nurgling.NurglingModel;
import net.jaeger.oldworldfantasy.entity.custom.beastmen.bestigor.Bestigor;
import net.jaeger.oldworldfantasy.entity.custom.beastmen.gor.Gor;
import net.jaeger.oldworldfantasy.entity.custom.beastmen.ungor.Ungor;
import net.jaeger.oldworldfantasy.entity.custom.beastmen.wargor.Wargor;
import net.jaeger.oldworldfantasy.entity.custom.greenskin.goblin.Goblin;
import net.jaeger.oldworldfantasy.entity.custom.greenskin.orc.Orc;
import net.jaeger.oldworldfantasy.entity.custom.nurgling.NurglingEntity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OldWorldFantasyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(NurglingModel.LAYER_LOCATION, NurglingModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.NURGLING.get(), NurglingEntity.createAttributes().build());
        event.put(ModEntities.UNGOR.get(), Ungor.createAttributes().build());
        event.put(ModEntities.GOR.get(), Gor.createAttributes().build());
        event.put(ModEntities.BESTIGOR.get(), Bestigor.createAttributes().build());
        event.put(ModEntities.WARGOR.get(), Wargor.createAttributes().build());
        event.put(ModEntities.GOBLIN.get(), Goblin.createAttributes().build());
        event.put(ModEntities.ORC.get(), Orc.createAttributes().build());
        event.put(ModEntities.BIGUNS.get(), Orc.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.NURGLING.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
