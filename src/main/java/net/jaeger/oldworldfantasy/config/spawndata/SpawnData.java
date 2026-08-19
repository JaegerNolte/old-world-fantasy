package net.jaeger.oldworldfantasy.config.spawndata;

import net.jaeger.oldworldfantasy.entity.ModEntities;

import java.util.List;

public class SpawnData {

    public static final List<SpawnDataHelper> BEASTMEN_WEIGHTS = List.of(
            new SpawnDataHelper(ModEntities.UNGOR.get(), 60),
            new SpawnDataHelper(ModEntities.GOR.get(), 25));
}
