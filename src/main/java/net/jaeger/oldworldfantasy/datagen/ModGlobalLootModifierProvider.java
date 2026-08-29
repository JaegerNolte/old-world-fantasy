package net.jaeger.oldworldfantasy.datagen;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {

    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, OldWorldFantasyMod.MOD_ID, registries);
    }

    @Override
    protected void start(HolderLookup.Provider registries) {
        // inject custom drops here
    }
}
