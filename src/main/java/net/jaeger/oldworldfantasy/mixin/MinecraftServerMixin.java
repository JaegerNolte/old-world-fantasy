package net.jaeger.oldworldfantasy.mixin;

import net.jaeger.oldworldfantasy.worldgen.spawners.BeastmanPatrolSpawner;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.CustomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @ModifyVariable(
            method = "createLevels",
            at = @At(value = "STORE", ordinal = 0)
    )
    private List<CustomSpawner> addModSpawners(List<CustomSpawner> spawners) {
        List<CustomSpawner> modified = new ArrayList<>(spawners);
        modified.add(new BeastmanPatrolSpawner());
        return modified;
    }
}
