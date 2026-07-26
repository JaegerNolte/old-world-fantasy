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
                    .title(Component.translatable("creativetab.old_world_fantasy_tab")).displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.LEAD.get());
                        pOutput.accept(ModItems.RAW_LEAD.get());

                        pOutput.accept(ModBlocks.JARED_BLOCK.get());
                        pOutput.accept(ModBlocks.LEAD_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_LEAD_BLOCK.get());
                        pOutput.accept(ModBlocks.LEAD_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_LEAD_ORE.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
