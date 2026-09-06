package net.jaeger.oldworldfantasy.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.item.ModItems;
import net.jaeger.oldworldfantasy.item.items.misc.FuelBlockItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

public class ModBlocks {

    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(OldWorldFantasy.MOD_ID, Registries.BLOCK);

    public static final RegistrySupplier<Block> LEAD_BLOCK = registerBlock("lead_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)));

    public static final RegistrySupplier<Block> RAW_LEAD_BLOCK = registerBlock("raw_lead_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)));

    public static final RegistrySupplier<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 7.0F)
                    .sound(SoundType.METAL)));

    public static final RegistrySupplier<Block> LEAD_ORE = registerBlock("lead_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 5),
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 3.0F)
                            .sound(SoundType.STONE)));

    public static final RegistrySupplier<Block> DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 5),
                    BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE)
                            .requiresCorrectToolForDrops()
                            .strength(4.5F, 3.0F)
                            .sound(SoundType.DEEPSLATE)));

    public static final RegistrySupplier<Block> ARCANE_COAL_BLOCK = registerFuelBlock("arcane_coal_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)), 22000);

    public static final RegistrySupplier<Block> ARCANE_COAL_ORE = registerBlock("arcane_coal_ore",
            () -> new DropExperienceBlock(UniformInt.of(0, 2),
                    BlockBehaviour.Properties.of().mapColor(MapColor.STONE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 3.0F)));

    public static final RegistrySupplier<Block> DEEPSLATE_ARCANE_COAL_ORE = registerBlock("deepslate_arcane_coal_ore",
            () -> new DropExperienceBlock(UniformInt.of(0, 2),
                    BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresCorrectToolForDrops()
                            .strength(3.0F, 3.0F)
                            .sound(SoundType.DEEPSLATE)));

    private static <T extends Block> RegistrySupplier<T> registerBlock(String name, Supplier<T> block) {
        RegistrySupplier<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistrySupplier<Item> registerBlockItem(String name, RegistrySupplier<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistrySupplier<T> registerFuelBlock(String name, Supplier<T> block, int burnTime) {
        RegistrySupplier<T> toReturn = BLOCKS.register(name, block);
        registerFuelBlockItem(name, toReturn, burnTime);
        return toReturn;
    }

    private static <T extends Block> RegistrySupplier<Item> registerFuelBlockItem(String name, RegistrySupplier<T> block, int burnTime) {
        return ModItems.ITEMS.register(name, () -> new FuelBlockItem(block.get(), new Item.Properties(), burnTime));
    }

    public static void init() {
        BLOCKS.register();
    }
}
