package net.jaeger.oldworldfantasy.entity.mobs.monsters.griffons.griffon;

import net.jaeger.oldworldfantasy.entity.ai.goals.GriffonAttackGoal;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.griffons.AbstractGriffon;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Griffon extends AbstractGriffon {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation ATTACK_ANIMATION = RawAnimation.begin().thenPlay("ANIM_GRIFFON_ATTACKING");
    private final String attack = "attack";
    private final int ambientSoundInterval = 1000;

    public Griffon(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.3F));
        this.goalSelector.addGoal(2, new GriffonAttackGoal(this, 1.5, false, attack));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LivingEntity.class, false));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    public int getAmbientSoundInterval() {
        return ambientSoundInterval;
    }

    public SoundEvent getAmbientSound(){
        return SoundEvents.CHICKEN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.CHICKEN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    @Override
    public void playAmbientSound() {
        this.playSound(this.getAmbientSound(), 1.0F, 1.0F);
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
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "movement", 1, state -> {
            if (!isFlying()) {
                if (state.isMoving()) {
                    state.setControllerSpeed(2);
                    return state.setAndContinue(RawAnimation.begin().thenLoop("ANIM_GRIFFON_WALKING"));
                } else if (!isInSittingPose()) {
                    state.setControllerSpeed(1);
                    return state.setAndContinue(RawAnimation.begin().thenLoop("ANIM_GRIFFON_IDLE"));
                } else {
                    state.setControllerSpeed(1);
                    return state.setAndContinue(RawAnimation.begin().thenLoop("ANIM_GRIFFON_SITTING"));
                }
            } else {
                state.setControllerSpeed(2);
                return state.setAndContinue(RawAnimation.begin().thenLoop("ANIM_GRIFFON_FLYING"));
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
    public boolean canUseSlot(EquipmentSlot arg) {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
    }
}
