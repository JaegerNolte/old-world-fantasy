package net.jaeger.oldworldfantasy.config.spawndata;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.PatrollingMonster;

public record SpawnDataHelper(
        EntityType<? extends PatrollingMonster> entityType,
        int weight
) {}
