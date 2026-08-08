package net.jaeger.oldworldfantasy.event;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.ModEntities;
import net.jaeger.oldworldfantasy.entity.client.gor.GorModel;
import net.jaeger.oldworldfantasy.entity.client.nurgling.NurglingModel;
import net.jaeger.oldworldfantasy.entity.client.ungor.UngorModel;
import net.jaeger.oldworldfantasy.entity.custom.gor.GorEntity;
import net.jaeger.oldworldfantasy.entity.custom.nurgling.NurglingEntity;
import net.jaeger.oldworldfantasy.entity.custom.ungor.UngorEntity;
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
        event.registerLayerDefinition(UngorModel.LAYER_LOCATION, UngorModel::createBodyLayer);
        event.registerLayerDefinition(GorModel.LAYER_LOCATION, GorModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.NURGLING.get(), NurglingEntity.createAttributes().build());
        event.put(ModEntities.UNGOR.get(), UngorEntity.createAttributes().build());
        event.put(ModEntities.GOR.get(), GorEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.NURGLING.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.UNGOR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.GOR.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
