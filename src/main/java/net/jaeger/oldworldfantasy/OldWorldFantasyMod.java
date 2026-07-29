package net.jaeger.oldworldfantasy;

import com.mojang.logging.LogUtils;
import net.jaeger.oldworldfantasy.block.ModBlocks;
import net.jaeger.oldworldfantasy.item.ModCreativeModeTabs;
import net.jaeger.oldworldfantasy.item.ModItems;
import net.jaeger.oldworldfantasy.sound.ModSounds;
import net.jaeger.oldworldfantasy.util.ModItemProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(OldWorldFantasyMod.MOD_ID)
public class OldWorldFantasyMod {

    public static final String MOD_ID = "oldworldfantasy";

    public static final Logger LOG = LogUtils.getLogger();


    public OldWorldFantasyMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        ModSounds.register(modEventBus);

        modEventBus.addListener(this::addCreative);
    }


    private void commonSetup(final FMLCommonSetupEvent event) {

    }


    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.RAW_LEAD);
            event.accept(ModItems.LEAD_INGOT);
            event.accept(ModItems.LEAD_NUGGET);
            event.accept(ModItems.RUNE_HARDEN_IRON_INGOT);
            event.accept(ModItems.ARCANE_COAL);

            event.accept(ModItems.RUNE_HARDENED_IRON_SWORD);
            event.accept(ModItems.RUNE_HARDENED_IRON_PICKAXE);
            event.accept(ModItems.RUNE_HARDENED_IRON_SHOVEL);
            event.accept(ModItems.RUNE_HARDENED_IRON_AXE);
            event.accept(ModItems.RUNE_HARDENED_IRON_HOE);
            event.accept(ModItems.BLUNDERBUSS);

            event.accept(ModItems.RED_WINE);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.LEAD_BLOCK);
            event.accept(ModBlocks.RAW_LEAD_BLOCK);
            event.accept(ModBlocks.DEEPSLATE_LEAD_ORE);
            event.accept(ModBlocks.LEAD_ORE);
            event.accept(ModBlocks.ARCANE_COAL_BLOCK);
            event.accept(ModBlocks.ARCANE_COAL_ORE);
            event.accept(ModBlocks.DEEPSLATE_ARCANE_COAL_ORE);
        }
    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {


    }


    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            ModItemProperties.addCustomItemProperties();
        }
    }
}
