package net.jaeger.oldworldfantasy.entity.mobs.monsters.giants;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.ModEntityTags;
import net.jaeger.oldworldfantasy.entity.mobs.ModRaider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.*;
import net.minecraft.world.level.storage.loot.LootTable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

public class AbstractGiant extends ModRaider {

    protected AbstractGiant(EntityType<? extends ModRaider> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void applyRaidBuffs(ServerLevel pLevel, int pWave, boolean pUnused) {

    }

    @Override
    public SoundEvent getCelebrateSound() {
        return null;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return null;
    }

    @Override
    public boolean canAttack(LivingEntity pTarget) {
        return super.canAttack(pTarget);
    }

    public final boolean isGiant() {
        return this.getType().is(ModEntityTags.GIANT);
    }

    @Override
    protected ResourceKey<LootTable> getDefaultLootTable() {
        return ResourceKey.create(
                Registries.LOOT_TABLE,
                OldWorldFantasy.res("entities/giant")
        );
    }

    // TODO: Change Later?
    public static boolean checkGiantSpawnRules(EntityType<? extends AbstractGiant> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource randomSource) {
        if (level.getDifficulty() != Difficulty.PEACEFUL) {
            return MobSpawnType.ignoresLightRequirements(spawnType) || isBrightEnoughToSpawn(level, pos);
        }
        return false;
    }

    protected static boolean isBrightEnoughToSpawn(BlockAndTintGetter blockAndTintGetter, BlockPos blockPos) {
        return blockAndTintGetter.getRawBrightness(blockPos, 0) > 8;
    }

    @Override
    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return 0.0F;
    }
}
