package net.jaeger.oldworldfantasy.neoforge.event;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.ModEntities;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.bestigor.Bestigor;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.gor.Gor;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.ungor.Ungor;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.wargor.Wargor;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.biguns.BigUns;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.goblin.Goblin;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.orc.Orc;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.warboss.OrcWarboss;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.archlector.EmpireArchLector;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.captain.EmpireCaptain;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.crossbowmen.EmpireCrossbowmen;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.spearmen.EmpireSpearmen;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.swordsmen.EmpireSwordsmen;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.giants.AbstractGiant;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.giants.giant.Giant;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.griffons.AbstractGriffon;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.griffons.griffon.Griffon;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.trolls.AbstractTroll;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.trolls.stone.StoneTroll;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = OldWorldFantasy.MOD_ID,  bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.UNGOR.get(), Ungor.createAttributes().build());
        event.put(ModEntities.GOR.get(), Gor.createAttributes().build());
        event.put(ModEntities.BESTIGOR.get(), Bestigor.createAttributes().build());
        event.put(ModEntities.WARGOR.get(), Wargor.createAttributes().build());

        event.put(ModEntities.GOBLIN.get(), Goblin.createAttributes().build());
        event.put(ModEntities.ORC.get(), Orc.createAttributes().build());
        event.put(ModEntities.BIGUNS.get(), BigUns.createAttributes().build());
        event.put(ModEntities.ORCWARBOSS.get(), OrcWarboss.createAttributes().build());

        event.put(ModEntities.EMPIRE_SWORDSMEN.get(), EmpireSwordsmen.createAttributes().build());
        event.put(ModEntities.EMPIRE_SPEARMEN.get(), EmpireSpearmen.createAttributes().build());
        event.put(ModEntities.EMPIRE_CROSSBOWMEN.get(), EmpireCrossbowmen.createAttributes().build());
        event.put(ModEntities.EMPIRE_CAPTAIN.get(), EmpireCaptain.createAttributes().build());
        event.put(ModEntities.EMPIRE_ARCH_LECTOR.get(), EmpireArchLector.createAttributes().build());

        event.put(ModEntities.STONE_TROLL.get(), StoneTroll.createAttributes().build());
        event.put(ModEntities.GIANT.get(), Giant.createAttributes().build());
        event.put(ModEntities.GRIFFON.get(), Griffon.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.STONE_TROLL.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                AbstractTroll::checkTrollSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.GIANT.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                AbstractGiant::checkGiantSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.GRIFFON.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                AbstractGriffon::checkGriffonSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
