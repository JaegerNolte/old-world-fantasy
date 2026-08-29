package net.jaeger.oldworldfantasy.entity.mobs.beastmen;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.ModEntityTags;
import net.jaeger.oldworldfantasy.entity.mobs.ModRaider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.storage.loot.LootTable;

public abstract class AbstractBeastmen extends ModRaider {

    protected AbstractBeastmen(EntityType<? extends ModRaider> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    public void applyRaidBuffs(ServerLevel pLevel, int pWave, boolean pUnused) {

    }

    @Override
    public SoundEvent getCelebrateSound() {
        return null;
    }


    @Override
    public boolean canAttack(LivingEntity pTarget) {
        return super.canAttack(pTarget);
    }

    @Override
    public boolean isAlliedTo(Entity pEntity) {
        if (super.isAlliedTo(pEntity)) {
            return true;
        } else {
            return !pEntity.getType().is(ModEntityTags.BEASTMEN) ? false : this.getTeam() == null && pEntity.getTeam() == null;
        }
    }

    protected class RaiderOpenDoorGoal extends OpenDoorGoal {
        public RaiderOpenDoorGoal(final ModRaider pRaider) {
            super(pRaider, false);
        }

        @Override
        public boolean canUse() {
            return super.canUse() && AbstractBeastmen.this.hasActiveRaid();
        }
    }

    public final boolean isBeastman() {
        return this.getType().is(ModEntityTags.BEASTMEN);
    }

    @Override
    protected ResourceKey<LootTable> getDefaultLootTable() {
        return ResourceKey.create(
                Registries.LOOT_TABLE,
                OldWorldFantasyMod.res("entities/beastmen")
        );
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor pLevel, MobSpawnType pSpawnReason) {
        return true;
    }

    @Override
    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return 0.0F;
    }
}
