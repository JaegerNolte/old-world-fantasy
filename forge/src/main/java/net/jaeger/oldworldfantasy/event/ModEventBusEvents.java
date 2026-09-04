package net.jaeger.oldworldfantasy.event;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
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
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OldWorldFantasyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
    }
}
