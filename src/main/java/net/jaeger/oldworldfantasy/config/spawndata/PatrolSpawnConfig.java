package net.jaeger.oldworldfantasy.config.spawndata;

import net.jaeger.oldworldfantasy.entity.ModEntities;

import java.util.List;

public class PatrolSpawnConfig {

    public static final List<SpawnDataHelper> BEASTMEN_WEIGHTS = List.of(
            new SpawnDataHelper(ModEntities.UNGOR.get(), 40),
            new SpawnDataHelper(ModEntities.GOR.get(), 35),
            new SpawnDataHelper(ModEntities.BESTIGOR.get(), 25),
            new SpawnDataHelper(ModEntities.WARGOR.get(), 15));

    public static final List<SpawnDataHelper> GREENSKIN_WEIGHTS = List.of(
            new SpawnDataHelper(ModEntities.GOBLIN.get(), 40),
            new SpawnDataHelper(ModEntities.ORC.get(), 40),
            new SpawnDataHelper(ModEntities.BIGUNS.get(), 35),
            new SpawnDataHelper(ModEntities.ORCWARBOSS.get(), 15));
}
