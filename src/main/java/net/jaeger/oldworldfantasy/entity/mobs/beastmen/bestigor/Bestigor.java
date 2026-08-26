package net.jaeger.oldworldfantasy.entity.mobs.beastmen.bestigor;

import net.jaeger.oldworldfantasy.entity.ModRaider;
import net.jaeger.oldworldfantasy.entity.ai.goals.BestigorAttackGoal;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.AbstractBeastmen;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.AbstractGreenskin;
import net.jaeger.oldworldfantasy.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class Bestigor extends AbstractBeastmen implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation ATTACK_ANIMATION = RawAnimation.begin().thenPlay("ANIM_BESTIGOR_ATTACKING");
    private final String axeAttack = "greataxe_swing";

    static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = p_34082_ -> p_34082_ == Difficulty.NORMAL || p_34082_ == Difficulty.HARD;
    private final int ambientSoundInterval = 1000;

    public Bestigor(EntityType<? extends Raider> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new Bestigor.BestigorBreakDoorGoal(this));
        this.goalSelector.addGoal(2, new AbstractBeastmen.RaiderOpenDoorGoal(this));
        this.goalSelector.addGoal(3, new BestigorAttackGoal(this, 1.0, false, axeAttack));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, ModRaider.class).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractGreenskin.class, false));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    @Override
    protected void customServerAiStep() {
        if (!this.isNoAi() && GoalUtils.hasGroundPathNavigation(this)) {
            boolean flag = ((ServerLevel) this.level()).isRaided(this.blockPosition());
            ((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(flag);
        }

        super.customServerAiStep();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.35F)
                .add(Attributes.MAX_HEALTH, 32)
                .add(Attributes.ATTACK_DAMAGE, 12.0)
                .add(Attributes.ARMOR, 8.0);
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.VINDICATOR_CELEBRATE;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {
        SpawnGroupData spawngroupdata = super.finalizeSpawn(pLevel, pDifficulty, pSpawnType, pSpawnGroupData);
        ((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
        RandomSource randomsource = pLevel.getRandom();
        this.populateDefaultEquipmentSlots(randomsource, pDifficulty);
        this.populateDefaultEquipmentEnchantments(pLevel, randomsource, pDifficulty);
        return spawngroupdata;
    }

    public int getAmbientSoundInterval() {
        return ambientSoundInterval;
    }

    public SoundEvent getAmbientSound(){
        return ModSounds.BEASTMEN_ROAR.get();
    }

    @Override
    public void playAmbientSound() {
        this.playSound(this.getAmbientSound(), 0.5F, 0.9F);
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.level().getProfiler().push("mobBaseTick");
        if (this.isAlive() && this.random.nextInt(1000) < this.ambientSoundTime++) {
            this.resetAmbientSoundTime();
            this.playAmbientSound();
        }

        this.level().getProfiler().pop();
    }

    private void resetAmbientSoundTime() {
        this.ambientSoundTime = -this.getAmbientSoundInterval();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSounds.BEASTMEN_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.BEASTMEN_DEATH.get();
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.SHEEP_STEP;
    }

    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(this.getStepSound(), 0.25F, 0.9F);
    }

    @Override
    public void applyRaidBuffs (ServerLevel pLevel,int pWave, boolean pUnused){

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "movement", 1, state -> {
            if (state.isMoving()) {
                state.setControllerSpeed(2);
                return state.setAndContinue(RawAnimation.begin().thenLoop("ANIM_BESTIGOR_WALKING"));
            } else {
                state.setControllerSpeed(1);
                return state.setAndContinue(RawAnimation.begin().thenLoop("ANIM_BESTIGOR_IDLE"));
            }
        }
        ));

        controllers.add(new AnimationController<>(this, "attack", 0, state ->
                PlayState.STOP).setAnimationSpeed(2.00).triggerableAnim(axeAttack, ATTACK_ANIMATION));
    }



    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    static class BestigorBreakDoorGoal extends BreakDoorGoal {
        public BestigorBreakDoorGoal(Mob p_34112_) {
            super(p_34112_, 6, Bestigor.DOOR_BREAKING_PREDICATE);
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canContinueToUse() {
            Bestigor bestigor = (Bestigor) this.mob;
            return bestigor.hasActiveRaid() && super.canContinueToUse();
        }

        @Override
        public boolean canUse() {
            Bestigor bestigor = (Bestigor) this.mob;
            return bestigor.hasActiveRaid() && bestigor.random.nextInt(reducedTickDelay(10)) == 0 && super.canUse();
        }

        @Override
        public void start() {
            super.start();
            this.mob.setNoActionTime(0);
        }
    }

    @Override
    public void tick() {
        super.tick();
    }
}
