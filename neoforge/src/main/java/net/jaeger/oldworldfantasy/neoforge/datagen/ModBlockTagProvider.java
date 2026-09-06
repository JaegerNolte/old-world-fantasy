package net.jaeger.oldworldfantasy.neoforge.datagen;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, OldWorldFantasy.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.LEAD_BLOCK.get())
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.RAW_LEAD_BLOCK.get())
                .add(ModBlocks.LEAD_BLOCK.get())
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(ModBlocks.ARCANE_COAL_BLOCK.get())
                .add(ModBlocks.ARCANE_COAL_ORE.get())
                .add(ModBlocks.DEEPSLATE_ARCANE_COAL_ORE.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.LEAD_BLOCK.get())
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.RAW_LEAD_BLOCK.get())
                .add(ModBlocks.LEAD_BLOCK.get())
                .add(ModBlocks.LEAD_ORE.get())
                .add(ModBlocks.DEEPSLATE_LEAD_ORE.get())
                .add(ModBlocks.ARCANE_COAL_BLOCK.get())
                .add(ModBlocks.ARCANE_COAL_ORE.get())
                .add(ModBlocks.DEEPSLATE_ARCANE_COAL_ORE.get());
    }
}
