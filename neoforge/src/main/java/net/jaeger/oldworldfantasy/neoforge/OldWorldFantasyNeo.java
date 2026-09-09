package net.jaeger.oldworldfantasy.neoforge;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.ModEntities;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.bestigor.client.BestigorRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.gor.client.GorRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.ungor.client.UngorRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.wargor.client.WargorRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.biguns.client.BigUnsRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.goblin.client.GoblinRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.orc.client.OrcRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.warboss.client.OrcWarbossRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.archlector.client.EmpireArchLectorRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.captain.client.EmpireCaptainRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.crossbowmen.client.EmpireCrossbowmenRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.spearmen.client.EmpireSpearmenRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.swordsmen.client.EmpireSwordsmenRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.giants.giant.client.GiantRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.griffons.griffon.client.GriffonRenderer;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.trolls.stone.client.StoneTrollRenderer;
import net.jaeger.oldworldfantasy.neoforge.datagen.DataGenerators;
import net.jaeger.oldworldfantasy.networking.ModNetworking;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import static net.jaeger.oldworldfantasy.OldWorldFantasy.MOD_ID;

@Mod(MOD_ID)
public final class OldWorldFantasyNeo {

    public OldWorldFantasyNeo(IEventBus modEventBus) {
        modEventBus.addListener(this::commonSetup);

        modEventBus.register(DataGenerators.class);

        OldWorldFantasy.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModNetworking.init();
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @SuppressWarnings("removal")
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.UNGOR.get(), UngorRenderer::new);
            EntityRenderers.register(ModEntities.GOR.get(), GorRenderer::new);
            EntityRenderers.register(ModEntities.BESTIGOR.get(), BestigorRenderer::new);
            EntityRenderers.register(ModEntities.WARGOR.get(), WargorRenderer::new);
            EntityRenderers.register(ModEntities.GOBLIN.get(), GoblinRenderer::new);
            EntityRenderers.register(ModEntities.ORC.get(), OrcRenderer::new);
            EntityRenderers.register(ModEntities.BIGUNS.get(), BigUnsRenderer::new);
            EntityRenderers.register(ModEntities.ORCWARBOSS.get(), OrcWarbossRenderer::new);
            EntityRenderers.register(ModEntities.EMPIRE_SWORDSMEN.get(), EmpireSwordsmenRenderer::new);
            EntityRenderers.register(ModEntities.EMPIRE_SPEARMEN.get(), EmpireSpearmenRenderer::new);
            EntityRenderers.register(ModEntities.EMPIRE_CROSSBOWMEN.get(), EmpireCrossbowmenRenderer::new);
            EntityRenderers.register(ModEntities.EMPIRE_CAPTAIN.get(), EmpireCaptainRenderer::new);
            EntityRenderers.register(ModEntities.EMPIRE_ARCH_LECTOR.get(), EmpireArchLectorRenderer::new);
            EntityRenderers.register(ModEntities.STONE_TROLL.get(), StoneTrollRenderer::new);
            EntityRenderers.register(ModEntities.GIANT.get(), GiantRenderer::new);
            EntityRenderers.register(ModEntities.GRIFFON.get(), GriffonRenderer::new);
        }
    }
}
