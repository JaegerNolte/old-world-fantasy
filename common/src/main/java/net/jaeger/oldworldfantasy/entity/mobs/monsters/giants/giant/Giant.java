package net.jaeger.oldworldfantasy.entity.mobs.monsters.giants.giant;

import net.jaeger.oldworldfantasy.entity.ModEntityTags;
import net.jaeger.oldworldfantasy.entity.ai.goals.GiantAttackGoal;
import net.jaeger.oldworldfantasy.entity.mobs.ModRaider;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.AbstractGreenskin;
import net.jaeger.oldworldfantasy.entity.mobs.human.AbstractHuman;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.giants.AbstractGiant;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Giant extends AbstractGiant {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation ATTACK_ANIMATION = RawAnimation.begin().thenPlay("ANIM_GIANT_ATTACKING");
    private final String attack = "attack";
    private final int ambientSoundInterval = 1000;

    public Giant(EntityType<? extends ModRaider> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public boolean isAlliedTo(Entity pEntity) {
        if (super.isAlliedTo(pEntity)) {
            return true;
        } else {
            return !pEntity.getType().is(ModEntityTags.GREENSKIN) ? false : this.getTeam() == null && pEntity.getTeam() == null;
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new GiantAttackGoal(this, 1.2, false, attack));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, AbstractGreenskin.class).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractHuman.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 1.0));
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
                .add(Attributes.FOLLOW_RANGE, 30.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.40F)
                .add(Attributes.MAX_HEALTH, 60)
                .add(Attributes.ATTACK_DAMAGE, 18.0F)
                .add(Attributes.ARMOR, 14.0F);
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.RAVAGER_CELEBRATE;
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
        return SoundEvents.RAVAGER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.RAVAGER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.RAVAGER_DEATH;
    }

    @Override
    public void playAmbientSound() {
        this.playSound(this.getAmbientSound(), 0.8F, 0.5F);
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
    protected void triggerOnDeathMobEffects(RemovalReason pRemovalReason) {
        super.triggerOnDeathMobEffects(pRemovalReason);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "movement", 1, state -> {
            if (state.isMoving()) {
                state.setControllerSpeed(2);
                return state.setAndContinue(RawAnimation.begin().thenLoop("ANIM_GIANT_WALKING"));
            } else {
                state.setControllerSpeed(1);
                return state.setAndContinue(RawAnimation.begin().thenLoop("ANIM_GIANT_IDLE"));
            }
        }
        ));

        controllers.add(new AnimationController<>(this, "attack", 0, state ->
                PlayState.STOP).setAnimationSpeed(2.00).triggerableAnim(attack, ATTACK_ANIMATION));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void tick() {
        super.tick();
    }
}
