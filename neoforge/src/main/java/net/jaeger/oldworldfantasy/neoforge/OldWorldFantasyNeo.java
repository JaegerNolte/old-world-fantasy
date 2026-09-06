package net.jaeger.oldworldfantasy.neoforge;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.ModEntities;
import net.jaeger.oldworldfantasy.entity.client.beastmen.bestigor.BestigorRenderer;
import net.jaeger.oldworldfantasy.entity.client.beastmen.gor.GorRenderer;
import net.jaeger.oldworldfantasy.entity.client.beastmen.ungor.UngorRenderer;
import net.jaeger.oldworldfantasy.entity.client.beastmen.wargor.WargorRenderer;
import net.jaeger.oldworldfantasy.entity.client.greenskin.biguns.BigUnsRenderer;
import net.jaeger.oldworldfantasy.entity.client.greenskin.goblin.GoblinRenderer;
import net.jaeger.oldworldfantasy.entity.client.greenskin.orc.OrcRenderer;
import net.jaeger.oldworldfantasy.entity.client.greenskin.warboss.OrcWarbossRenderer;
import net.jaeger.oldworldfantasy.entity.client.human.archlector.EmpireArchLectorRenderer;
import net.jaeger.oldworldfantasy.entity.client.human.captain.EmpireCaptainRenderer;
import net.jaeger.oldworldfantasy.entity.client.human.crossbowmen.EmpireCrossbowmenRenderer;
import net.jaeger.oldworldfantasy.entity.client.human.spearmen.EmpireSpearmenRenderer;
import net.jaeger.oldworldfantasy.entity.client.human.swordsmen.EmpireSwordsmenRenderer;
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
        }
    }
}
