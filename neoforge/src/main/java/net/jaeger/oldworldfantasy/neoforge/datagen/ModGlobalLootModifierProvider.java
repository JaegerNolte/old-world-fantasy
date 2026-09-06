package net.jaeger.oldworldfantasy.neoforge.datagen;


import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {

    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, OldWorldFantasy.MOD_ID);
    }

    @Override
    protected void start() {

    }
}
