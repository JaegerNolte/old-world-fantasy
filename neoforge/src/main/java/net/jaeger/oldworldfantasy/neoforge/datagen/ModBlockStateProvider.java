package net.jaeger.oldworldfantasy.neoforge.datagen;


import dev.architectury.registry.registries.RegistrySupplier;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, OldWorldFantasy.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.LEAD_BLOCK);
        blockWithItem(ModBlocks.STEEL_BLOCK);
        blockWithItem(ModBlocks.RAW_LEAD_BLOCK);
        blockWithItem(ModBlocks.LEAD_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_LEAD_ORE);
        blockWithItem(ModBlocks.ARCANE_COAL_BLOCK);
        blockWithItem(ModBlocks.ARCANE_COAL_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ARCANE_COAL_ORE);
    }

    private void blockWithItem(RegistrySupplier<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
