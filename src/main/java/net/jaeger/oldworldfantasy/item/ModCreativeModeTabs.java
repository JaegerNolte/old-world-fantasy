package net.jaeger.oldworldfantasy.item;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OldWorldFantasyMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> OLD_WORD_FANTASY_TAB = CREATIVE_MOD_TABS.register("old_world_fantasy_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.LEAD_BLOCK.get()))
                    .title(Component.translatable("creativetab.old_world_fantasy_tab")).displayItems((pParameters, output) -> {
                        output.accept(ModItems.RAW_LEAD.get());
                        output.accept(ModItems.LEAD_INGOT.get());
                        output.accept(ModItems.LEAD_NUGGET.get());
                        output.accept(ModItems.ARCANE_COAL.get());
                        output.accept(ModItems.STEEL_INGOT.get());
                        output.accept(ModItems.STEEL_NUGGET.get());
                        output.accept(ModItems.RUNE_HARDEN_IRON_INGOT.get());
                        output.accept(ModItems.LEATHER_STRAP.get());
                        output.accept(ModItems.HILT.get());
                        output.accept(ModItems.POLE.get());

                        output.accept(ModItems.RUNE_HARDENED_IRON_SWORD.get());
                        output.accept(ModItems.RUNE_HARDENED_IRON_PICKAXE.get());
                        output.accept(ModItems.RUNE_HARDENED_IRON_SHOVEL.get());
                        output.accept(ModItems.RUNE_HARDENED_IRON_AXE.get());
                        output.accept(ModItems.RUNE_HARDENED_IRON_HOE.get());
                        output.accept(ModItems.IMPERIAL_SWORD.get());
                        output.accept(ModItems.IMPERIAL_GREATSWORD.get());
                        output.accept(ModItems.IMPERIAL_PIKE.get());
                        output.accept(ModItems.IMPERIAL_HALBERD.get());

//                        output.accept(ModItems.BLUNDERBUSS.get());

                        output.accept(ModItems.RED_WINE.get());

                        output.accept(ModItems.NURGLING_SPAWN_EGG.get());
                        output.accept(ModItems.UNGOR_SPAWN_EGG.get());

                        output.accept(ModBlocks.LEAD_BLOCK.get());
                        output.accept(ModBlocks.RAW_LEAD_BLOCK.get());
                        output.accept(ModBlocks.STEEL_BLOCK.get());
                        output.accept(ModBlocks.LEAD_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_LEAD_ORE.get());
                        output.accept(ModBlocks.ARCANE_COAL_BLOCK.get());
                        output.accept(ModBlocks.ARCANE_COAL_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_ARCANE_COAL_ORE.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
