package net.jaeger.oldworldfantasy.entity.mobs.human.empire.crossbowmen;

import net.jaeger.oldworldfantasy.entity.ai.goals.HumanAttackGoal;
import net.jaeger.oldworldfantasy.entity.mobs.ModRaider;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.AbstractBeastmen;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.AbstractGreenskin;
import net.jaeger.oldworldfantasy.entity.mobs.human.AbstractHuman;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.EmpireVariant;
import net.jaeger.oldworldfantasy.item.ModItems;
import net.jaeger.oldworldfantasy.sound.ModSounds;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class EmpireCrossbowmen extends AbstractHuman implements CrossbowAttackMob {

    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(EmpireCrossbowmen.class, EntityDataSerializers.INT);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation ATTACK_ANIMATION = RawAnimation.begin().thenPlay("ANIM_EMPIRE_SOLDIER_ATTACKING");
    private final String attack = "sword_attack";

    static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = p_34082_ -> p_34082_ == Difficulty.NORMAL || p_34082_ == Difficulty.HARD;
    private final int ambientSoundInterval = 1000;

    public EmpireCrossbowmen(EntityType<? extends ModRaider> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new ModRaider.HoldGroundAttackGoal<>(this, 10.0F));
        this.goalSelector.addGoal(3, new HumanAttackGoal(this, 1.0, false, attack));
        this.goalSelector.addGoal(3, new RangedCrossbowAttackGoal<>(this, 1.0, 8.0F));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, AbstractHuman.class).setAlertOthers());
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractBeastmen.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractGreenskin.class, false));
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
                .add(Attributes.FOLLOW_RANGE, 12.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.35F)
                .add(Attributes.MAX_HEALTH, 24)
                .add(Attributes.ATTACK_DAMAGE, 2.0F);
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.VINDICATOR_CELEBRATE;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pSpawnType, @Nullable SpawnGroupData pSpawnGroupData) {

        EmpireVariant variant = Util.getRandom(EmpireVariant.values(), this.random);
        this.setVariant(variant);

        ((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.IMPERIAL_HELMET.get()));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModItems.IMPERIAL_CHESTPLATE.get()));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(ModItems.IMPERIAL_LEGGINGS.get()));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(ModItems.IMPERIAL_BOOTS.get()));

        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));

        for (EquipmentSlot slot : new EquipmentSlot[] {
                EquipmentSlot.HEAD,
                EquipmentSlot.CHEST,
                EquipmentSlot.LEGS,
                EquipmentSlot.FEET,
                EquipmentSlot.OFFHAND
        }) {this.setDropChance(slot, 0.0F);}

        return pSpawnGroupData;
    }

    public int getAmbientSoundInterval() {
        return ambientSoundInterval;
    }

    public SoundEvent getAmbientSound(){
        return ModSounds.HUMAN_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSounds.HUMAN_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.HUMAN_DEATH.get();
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
            if (state.isMoving()) {
                state.setControllerSpeed(2);
                return state.setAndContinue(RawAnimation.begin().thenLoop("ANIM_EMPIRE_SOLDIER_WALKING"));
            } else {
                state.setControllerSpeed(1);
                return state.setAndContinue(RawAnimation.begin().thenLoop("ANIM_EMPIRE_SOLDIER_IDLE"));
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
    public void setChargingCrossbow(boolean pIsCharging) {

    }

    @Override
    public boolean canFireProjectileWeapon(ProjectileWeaponItem pProjectileWeapon) {
        return pProjectileWeapon == Items.CROSSBOW;
    }

    @Override
    public void onCrossbowAttackPerformed() {
        this.noActionTime = 0;
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
        this.performCrossbowAttack(this, 1.6F);
    }

    static class SoldierBreakDoorGoal extends BreakDoorGoal {
        public SoldierBreakDoorGoal(Mob p_34112_) {
            super(p_34112_, 6, EmpireCrossbowmen.DOOR_BREAKING_PREDICATE);
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canContinueToUse() {
            EmpireCrossbowmen soldier = (EmpireCrossbowmen) this.mob;
            return soldier.hasActiveRaid() && super.canContinueToUse();
        }

        @Override
        public boolean canUse() {
            EmpireCrossbowmen soldier = (EmpireCrossbowmen) this.mob;
            return soldier.hasActiveRaid() && soldier.random.nextInt(reducedTickDelay(10)) == 0 && super.canUse();
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


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(VARIANT, 0);
    }

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public EmpireVariant getVariant() {
        return EmpireVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(EmpireVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void setTradingPlayer(@org.jetbrains.annotations.Nullable Player pTradingPlayer) {

    }

    @Override
    public @org.jetbrains.annotations.Nullable Player getTradingPlayer() {
        return null;
    }

    @Override
    public MerchantOffers getOffers() {
        return null;
    }

    @Override
    public void overrideOffers(MerchantOffers pOffers) {

    }

    @Override
    public void notifyTrade(MerchantOffer pOffer) {

    }

    @Override
    public void notifyTradeUpdated(ItemStack pStack) {

    }

    @Override
    protected void rewardTradeXp(MerchantOffer pOffer) {

    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int pXp) {

    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return null;
    }

    @Override
    public boolean isClientSide() {
        return false;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(VARIANT, pCompound.getInt("Variant"));
    }
}
