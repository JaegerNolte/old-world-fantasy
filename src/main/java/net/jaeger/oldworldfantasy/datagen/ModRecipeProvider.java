package net.jaeger.oldworldfantasy.datagen;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.block.ModBlocks;
import net.jaeger.oldworldfantasy.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private List<ItemLike> LEAD_SMELTABLES = List.of(ModItems.RAW_LEAD.get(), ModBlocks.LEAD_ORE.get(), ModBlocks.DEEPSLATE_LEAD_ORE.get());

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LEAD_BLOCK.get())
                .pattern("lll")
                .pattern("lll")
                .pattern("lll")
                .define('l', ModItems.LEAD_INGOT.get())
                .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_LEAD_BLOCK.get())
                .pattern("lll")
                .pattern("lll")
                .pattern("lll")
                .define('l', ModItems.RAW_LEAD.get())
                .unlockedBy("has_raw_lead", has(ModItems.RAW_LEAD.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.LEAD_INGOT.get())
                .pattern("lll")
                .pattern("lll")
                .pattern("lll")
                .define('l', ModItems.LEAD_NUGGET.get())
                .unlockedBy("has_lead_nugget", has(ModItems.LEAD_NUGGET.get()))
                .save(pRecipeOutput, OldWorldFantasyMod.MOD_ID + ":lead_ingot_from_nuggets");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ARCANE_COAL_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ARCANE_COAL.get())
                .unlockedBy("has_arcane_coal", has(ModItems.ARCANE_COAL.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RUNE_HARDEN_IRON_INGOT.get(), 2)
                .pattern("iA ")
                .pattern("Ai ")
                .define('A', ModItems.ARCANE_COAL.get())
                .define('i', Items.IRON_INGOT)
                .unlockedBy("has_arcane_coal", has(ModItems.ARCANE_COAL.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.RUNE_HARDENED_IRON_SWORD.get())
                .pattern(" r ")
                .pattern(" r ")
                .pattern(" s ")
                .define('r', ModItems.RUNE_HARDEN_IRON_INGOT.get())
                .define('s', Items.STICK)
                .unlockedBy("has_rune_hardened_iron", has(ModItems.RUNE_HARDEN_IRON_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.RUNE_HARDENED_IRON_PICKAXE.get())
                .pattern("rrr")
                .pattern(" s ")
                .pattern(" s ")
                .define('r', ModItems.RUNE_HARDEN_IRON_INGOT.get())
                .define('s', Items.STICK)
                .unlockedBy("has_rune_hardened_iron", has(ModItems.RUNE_HARDEN_IRON_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.RUNE_HARDENED_IRON_SHOVEL.get())
                .pattern(" r ")
                .pattern(" s ")
                .pattern(" s ")
                .define('r', ModItems.RUNE_HARDEN_IRON_INGOT.get())
                .define('s', Items.STICK)
                .unlockedBy("has_rune_hardened_iron", has(ModItems.RUNE_HARDEN_IRON_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.RUNE_HARDENED_IRON_AXE.get())
                .pattern("rr ")
                .pattern("rs ")
                .pattern(" s ")
                .define('r', ModItems.RUNE_HARDEN_IRON_INGOT.get())
                .define('s', Items.STICK)
                .unlockedBy("has_rune_hardened_iron", has(ModItems.RUNE_HARDEN_IRON_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.RUNE_HARDENED_IRON_HOE.get())
                .pattern("rr ")
                .pattern(" s ")
                .pattern(" s ")
                .define('r', ModItems.RUNE_HARDEN_IRON_INGOT.get())
                .define('s', Items.STICK)
                .unlockedBy("has_rune_hardened_iron", has(ModItems.RUNE_HARDEN_IRON_INGOT.get()))
                .save(pRecipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 9)
                .requires(ModBlocks.LEAD_BLOCK.get())
                .unlockedBy("has_lead_block", has(ModBlocks.LEAD_BLOCK.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LEAD_NUGGET.get(), 9)
                .requires(ModItems.LEAD_INGOT.get())
                .unlockedBy("has_lead_ingot", has(ModItems.LEAD_NUGGET.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_LEAD.get(), 9)
                .requires(ModBlocks.RAW_LEAD_BLOCK.get())
                .unlockedBy("has_raw_lead_block", has(ModBlocks.RAW_LEAD_BLOCK.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ARCANE_COAL.get(), 9)
                .requires(ModBlocks.ARCANE_COAL_BLOCK.get())
                .unlockedBy("has_arcane_coal_block", has(ModBlocks.ARCANE_COAL_BLOCK.get()))
                .save(pRecipeOutput);


        oreSmelting(pRecipeOutput, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 0.25f, 200, "lead");
        oreBlasting(pRecipeOutput, LEAD_SMELTABLES, RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 0.25f, 100, "lead");

    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, OldWorldFantasyMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
