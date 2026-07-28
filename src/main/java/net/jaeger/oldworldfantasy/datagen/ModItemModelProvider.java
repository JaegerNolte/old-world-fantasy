package net.jaeger.oldworldfantasy.datagen;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, OldWorldFantasyMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.LEAD_INGOT.get());
        basicItem(ModItems.RAW_LEAD.get());
        basicItem(ModItems.LEAD_NUGGET.get());
        basicItem(ModItems.RUNE_HARDEN_IRON_INGOT.get());
        basicItem(ModItems.ARCANE_COAL.get());
        basicItem(ModItems.RED_WINE.get());
    }

    private ItemModelBuilder handHeldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(OldWorldFantasyMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
