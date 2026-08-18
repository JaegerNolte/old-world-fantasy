package net.jaeger.oldworldfantasy.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class ModItemTier implements Tier {

    private final String name;
    private final float attackDamageBonus;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;
    private final float speed;
    private final int uses;
    private final float density;
    private final TagKey<Block> incorrectBlocks;

    public static ModItemTier STEEL = new ModItemTier("steel", BlockTags.INCORRECT_FOR_IRON_TOOL, 400, 6.0F, 2.5F, 14, "c:ingots/steel", 2);

    public ModItemTier(String name, Tier tier, float density) {
        this.name = name;
        this.incorrectBlocks = tier.getIncorrectBlocksForDrops();
        this.uses = tier.getUses();
        this.speed = tier.getSpeed();
        this.attackDamageBonus = tier.getAttackDamageBonus();
        this.enchantmentValue = tier.getEnchantmentValue();
        this.repairIngredient = tier::getRepairIngredient;
        this.density = density;
    }

    public ModItemTier(String name, TagKey<Block> incorrectBlocks, int uses, float speed, float attack, int enchantment, String repairitemtag, float density) {
        this.name = name;
        this.incorrectBlocks = incorrectBlocks;
        this.uses = uses;
        this.speed = speed;
        this.attackDamageBonus = attack;
        this.enchantmentValue = enchantment;
        this.repairIngredient = () -> Ingredient.of(TagKey.create(Registries.ITEM, ResourceLocation.parse(repairitemtag)));
        this.density = density;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamageBonus;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocks;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
