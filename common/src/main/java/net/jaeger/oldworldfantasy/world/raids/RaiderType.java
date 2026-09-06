package net.jaeger.oldworldfantasy.world.raids;

import net.jaeger.oldworldfantasy.entity.mobs.ModRaider;
import net.minecraft.world.entity.EntityType;

public class RaiderType {

    private final String name;
    private final EntityType<? extends ModRaider> entityType;
    private final int[] spawnsPerWaveBeforeBonus;

    private RaiderType(String name, EntityType<? extends ModRaider> entityType, int[] spawnsPerWaveBeforeBonus) {
        this.name = name;
        this.entityType = entityType;
        this.spawnsPerWaveBeforeBonus = spawnsPerWaveBeforeBonus;
    }

    public static RaiderType register(String name, EntityType<? extends ModRaider> entityType, int[] waveCounts) {
        RaiderType type = new RaiderType(name, entityType, waveCounts);
        return type;
    }

    public static RaiderType create(String name, EntityType<? extends ModRaider> type, int[] waveCounts) {
        return register(name, type, waveCounts);
    }

    public String name() {
        return name;
    }

    public EntityType<? extends ModRaider> getEntityType() {
        return entityType;
    }

    public int[] getSpawnsPerWaveBeforeBonus() {
        return spawnsPerWaveBeforeBonus;
    }
}
