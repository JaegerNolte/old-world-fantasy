package net.jaeger.oldworldfantasy.entity.mobs.human.empire.archlector;

import net.jaeger.oldworldfantasy.entity.ai.goals.HumanAttackGoal;
import net.jaeger.oldworldfantasy.entity.mobs.ModRaider;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.AbstractBeastmen;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.AbstractGreenskin;
import net.jaeger.oldworldfantasy.entity.mobs.human.AbstractHuman;
import net.jaeger.oldworldfantasy.item.ModItems;
import net.jaeger.oldworldfantasy.sound.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
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

import static net.jaeger.oldworldfantasy.OldWorldFantasyMod.LOG;

public class EmpireArchLector extends AbstractHuman {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation ATTACK_ANIMATION = RawAnimation.begin().thenPlay("ANIM_EMPIRE_SOLDIER_ATTACKING");
    private final String attack = "sword_attack";
    static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = p_34082_ -> p_34082_ == Difficulty.NORMAL || p_34082_ == Difficulty.HARD;
    private final int ambientSoundInterval = 1000;

    @Nullable
    private Player tradingPlayer;
    private MerchantOffers offers = new MerchantOffers();
    private int mobXp;

    public EmpireArchLector(EntityType<? extends ModRaider> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new EmpireArchLector.EmpireArchLectorBreakDoorGoal(this));
        this.goalSelector.addGoal(2, new AbstractHuman.RaiderOpenDoorGoal(this));
        this.goalSelector.addGoal(3, new HumanAttackGoal(this, 1.0, false, attack));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, AbstractHuman.class).setAlertOthers());
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractBeastmen.class, false));
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

        ((GroundPathNavigation) this.getNavigation()).setCanOpenDoors(true);
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.IMPERIAL_HELMET.get()));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModItems.IMPERIAL_CHESTPLATE.get()));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(ModItems.IMPERIAL_LEGGINGS.get()));
        this.setItemSlot(EquipmentSlot.FEET, new ItemStack(ModItems.IMPERIAL_BOOTS.get()));

        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.IMPERIAL_SWORD.get()));

        for (EquipmentSlot slot : new EquipmentSlot[] {
                EquipmentSlot.HEAD,
                EquipmentSlot.CHEST,
                EquipmentSlot.LEGS,
                EquipmentSlot.FEET,
                EquipmentSlot.OFFHAND
        }) {
            this.setDropChance(slot, 0.0F);
        }

        this.createOffers();
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

    static class EmpireArchLectorBreakDoorGoal extends BreakDoorGoal {
        public EmpireArchLectorBreakDoorGoal(Mob p_34112_) {
            super(p_34112_, 6, EmpireArchLector.DOOR_BREAKING_PREDICATE);
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canContinueToUse() {
            EmpireArchLector soldier = (EmpireArchLector) this.mob;
            return soldier.hasActiveRaid() && super.canContinueToUse();
        }

        @Override
        public boolean canUse() {
            EmpireArchLector soldier = (EmpireArchLector) this.mob;
            return soldier.hasActiveRaid() && soldier.random.nextInt(reducedTickDelay(10)) == 0 && super.canUse();
        }

        @Override
        public void start() {
            super.start();
            this.mob.setNoActionTime(0);
        }
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if (!this.isClientSide()) {
            boolean flag = this.getOffers().isEmpty();
            if (pHand == InteractionHand.MAIN_HAND) {
                if (flag) {
                    return InteractionResult.CONSUME;
                }
                startTrading(pPlayer);
            }
        }

        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    private void startTrading(Player player) {
        if (this.offers.isEmpty()) {
            this.createOffers();
        }

        this.setTradingPlayer(player);
        this.openTradingScreen(player, this.getDisplayName(), 1);
    }

    private void createOffers() {
        if (!this.offers.isEmpty()) {
            return;
        }

        this.offers.add(new MerchantOffer(
                new ItemCost(ModItems.TEEF.get(), 10),
                new ItemStack(ModItems.IMPERIAL_HELMET.get(), 3),
                10,
                1,
                0.05F
        ));
        this.offers.add(new MerchantOffer(
                new ItemCost(ModItems.TEEF.get(), 15),
                new ItemStack(ModItems.IMPERIAL_CHESTPLATE.get(), 3),
                10,
                1,
                0.05F
        ));
        this.offers.add(new MerchantOffer(
                new ItemCost(ModItems.TEEF.get(), 12),
                new ItemStack(ModItems.IMPERIAL_LEGGINGS.get(), 3),
                10,
                1,
                0.05F
        ));
        this.offers.add(new MerchantOffer(
                new ItemCost(ModItems.TEEF.get(), 8),
                new ItemStack(ModItems.IMPERIAL_BOOTS.get(), 3),
                10,
                1,
                0.05F
        ));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);

        MerchantOffers.CODEC.encodeStart(
                        NbtOps.INSTANCE,
                        this.offers
                ).resultOrPartial(LOG::error)
                .ifPresent(offersTag -> pCompound.put("Offers", offersTag));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);

        if (pCompound.contains("Offers")) {
            MerchantOffers.CODEC.parse(
                    NbtOps.INSTANCE,
                    pCompound.get("Offers")
            ).resultOrPartial(LOG::error).ifPresent(offers ->
                    this.offers = offers);
        }
    }

    @Override
    public void setTradingPlayer(@Nullable Player pTradingPlayer) {
        this.tradingPlayer = pTradingPlayer;
    }

    @Override
    public @Nullable Player getTradingPlayer() {
        return this.tradingPlayer;
    }

    @Override
    public MerchantOffers getOffers() {
        return this.offers;
    }

    @Override
    public void overrideOffers(MerchantOffers pOffers) {
        this.offers = pOffers;
    }

    @Override
    protected void rewardTradeXp(MerchantOffer pOffer) {
        int i = 3 + this.random.nextInt(4);
        this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5, this.getZ(), i));
    }

    @Override
    public int getVillagerXp() {
        return this.mobXp;
    }

    @Override
    public void overrideXp(int pXp) {
        this.mobXp = pXp;
    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return ModSounds.HUMAN_SATISFIED.get();
    }

    @Override
    public boolean isClientSide() {
        return this.level().isClientSide;
    }

    @Override
    public void tick() {
        super.tick();
    }
}
