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

    public static final List<SpawnDataHelper> EMPIRE_WEIGHTS = List.of(
            new SpawnDataHelper(ModEntities.EMPIRE_SPEARMEN.get(), 35),
            new SpawnDataHelper(ModEntities.EMPIRE_SWORDSMEN.get(), 35),
            new SpawnDataHelper(ModEntities.EMPIRE_CROSSBOWMEN.get(), 40),
            new SpawnDataHelper(ModEntities.EMPIRE_ARCH_LECTOR.get(), 25),
            new SpawnDataHelper(ModEntities.EMPIRE_CAPTAIN.get(), 15));
}
