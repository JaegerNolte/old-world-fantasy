package net.jaeger.oldworldfantasy.item;

import net.jaeger.oldworldfantasy.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier RUNE_HARDENED_IRON = new ForgeTier(1400, 3,3.5f, 20,
            ModTags.Blocks.NEEDS_RUNE_HARDENED_IRON_TOOL, () -> Ingredient.of(ModItems.RUNE_HARDEN_IRON_INGOT.get()),
            ModTags.Blocks.INCORRECT_FOR_RUNE_HARDENED_IRON_TOOL);

    public static final Tier STEEL = new ForgeTier(1400, 4,3f, 20,
            ModTags.Blocks.NEEDS_RUNE_HARDENED_IRON_TOOL, () -> Ingredient.of(ModItems.STEEL_INGOT.get()),
            ModTags.Blocks.INCORRECT_FOR_RUNE_HARDENED_IRON_TOOL);
}
