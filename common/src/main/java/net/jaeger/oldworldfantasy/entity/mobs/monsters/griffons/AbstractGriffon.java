package net.jaeger.oldworldfantasy.entity.mobs.monsters.griffons;

import dev.architectury.registry.menu.MenuRegistry;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.ModEntityTags;
import net.jaeger.oldworldfantasy.entity.mobs.ModRaider;
import net.jaeger.oldworldfantasy.world.inventory.saddle.AbstractGriffonMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

import java.util.Optional;
import java.util.UUID;

public class AbstractGriffon extends ModRaider implements ContainerListener, HasCustomInventoryScreen, OwnableEntity, Saddleable {

    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(AbstractGriffon.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Optional<UUID>> DATA_OWNER = SynchedEntityData.defineId(AbstractGriffon.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Boolean> DATA_FLYING = SynchedEntityData.defineId(AbstractGriffon.class, EntityDataSerializers.BOOLEAN);
    public SimpleContainer inventory;
    @Nullable
    private UUID owner;

    protected AbstractGriffon(EntityType<? extends ModRaider> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.createInventory();
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

    public final boolean isGriffon() {
        return this.getType().is(ModEntityTags.GRIFFON);
    }

    @Override
    protected ResourceKey<LootTable> getDefaultLootTable() {
        return ResourceKey.create(
                Registries.LOOT_TABLE,
                OldWorldFantasy.res("entities/griffon")
        );
    }

    public static boolean checkGriffonSpawnRules(EntityType<? extends AbstractGriffon> entityType, ServerLevelAccessor level,
                                                 MobSpawnType spawnType, BlockPos pos, RandomSource randomSource) {
        if (level.getDifficulty() != Difficulty.PEACEFUL) {
            return pos.getY() >= 130 && level.canSeeSky(pos);
        }
        return false;
    }

    @Nullable
    @Override
    public UUID getOwnerUUID() {
        return this.entityData.get(DATA_OWNER).orElse(null);
    }

    public void setOwnerUUID(@Nullable UUID uuid) {
        this.entityData.set(DATA_OWNER, Optional.ofNullable(uuid));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_FLAGS, (byte)0);
        builder.define(DATA_OWNER, Optional.empty());
        builder.define(DATA_FLYING, false);
    }

    protected boolean getFlag(int i) {
        return (this.entityData.get(DATA_ID_FLAGS) & i) != 0;
    }

    protected void setFlag(int flag, boolean value) {
        byte flags = this.entityData.get(DATA_ID_FLAGS);

        if (value) {
            this.entityData.set(DATA_ID_FLAGS, (byte)(flags | flag));
        } else {
            this.entityData.set(DATA_ID_FLAGS, (byte)(flags & ~flag));
        }
    }

    public void setTamed(boolean value) {
        this.setFlag(2, value);
    }

    public boolean isTamed() {
        return this.getFlag(2);
    }

    public void setEating(boolean value) {
        this.setFlag(16, value);
    }

    public boolean isEating() {
        return this.getFlag(16);
    }

    public boolean isFlying() {
        return this.entityData.get(DATA_FLYING);
    }

    public void setFlying(boolean flying) {
        this.entityData.set(DATA_FLYING, flying);
    }


    @Nullable
    protected SoundEvent getEatingSound() {
        return null;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (this.isTamed()) {
            if (player.isSecondaryUseActive()) {
                this.openCustomInventoryScreen(player);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
            if (this.isOwnedBy(player)) {
                this.doPlayerRide(player);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
            return InteractionResult.PASS;
        }

        if (stack.is(Items.BEEF)) {
            if (!this.level().isClientSide) {
                stack.consume(1, player);
                this.tryToTame(player);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }

        return super.mobInteract(player, hand);
    }

    private void tryToTame(Player player) {
        if (this.random.nextInt(3) == 0) {
            this.tame(player);
            this.navigation.stop();
            this.setTarget(null);
            this.level().broadcastEntityEvent(this, (byte) 7);
        } else {
            this.level().broadcastEntityEvent(this, (byte) 6);
        }
    }

    public void tame(Player player) {
        this.setTamed(true);
        this.setOwnerUUID(player.getUUID());
    }

    public boolean isOwnedBy(LivingEntity livingEntity) {
        UUID ownerUUID = this.getOwnerUUID();
        return ownerUUID != null && ownerUUID.equals(livingEntity.getUUID());
    }

    public final int getInventorySize() {
        return getInventorySize(this.getInventoryColumns());
    }

    public int getInventoryColumns() {
        return 0;
    }

    public static int getInventorySize(int i) {
        return i * 3 + 1;
    }

    protected void createInventory() {
        SimpleContainer simpleContainer = this.inventory;
        this.inventory = new SimpleContainer(this.getInventorySize());
        if (simpleContainer != null) {
            simpleContainer.removeListener(this);
            int i = Math.min(simpleContainer.getContainerSize(), this.inventory.getContainerSize());

            for (int j = 0; j < i; j++) {
                ItemStack itemStack = simpleContainer.getItem(j);
                if (!itemStack.isEmpty()) {
                    this.inventory.setItem(j, itemStack.copy());
                }
            }
        }

        this.inventory.addListener(this);
        this.syncSaddleToClients();
    }

    @Override
    public void containerChanged(Container container) {
        boolean bl = this.isSaddled();
        this.syncSaddleToClients();
        if (this.tickCount > 20 && !bl && this.isSaddled()) {
            this.playSound(this.getSaddleSoundEvent(), 0.5F, 1.0F);
        }
    }

    protected void syncSaddleToClients() {
        if (!this.level().isClientSide) {
            this.setFlag(4, !this.inventory.getItem(0).isEmpty());
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putBoolean("EatingMeat", this.isEating());
        compoundTag.putBoolean("Tame", this.isTamed());
        compoundTag.putBoolean("Flying", this.isFlying());
        if (this.getOwnerUUID() != null) {
            compoundTag.putUUID("Owner", this.getOwnerUUID());
        }

        if (!this.inventory.getItem(0).isEmpty()) {
            compoundTag.put("SaddleItem", this.inventory.getItem(0).save(this.registryAccess()));
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setEating(compoundTag.getBoolean("EatingMeat"));
        this.setTamed(compoundTag.getBoolean("Tame"));
        this.setFlying(compoundTag.getBoolean("Flying"));
        UUID uUID;
        if (compoundTag.hasUUID("Owner")) {
            uUID = compoundTag.getUUID("Owner");
        } else {
            String string = compoundTag.getString("Owner");
            uUID = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), string);
        }

        if (uUID != null) {
            this.setOwnerUUID(uUID);
        }

        if (compoundTag.contains("SaddleItem", 10)) {
            ItemStack itemStack = ItemStack.parse(this.registryAccess(), compoundTag.getCompound("SaddleItem")).orElse(ItemStack.EMPTY);
            if (itemStack.is(Items.SADDLE)) {
                this.inventory.setItem(0, itemStack);
            }
        }

        this.syncSaddleToClients();
    }

    protected void spawnTamingParticles(boolean bl) {
        ParticleOptions particleOptions = bl ? ParticleTypes.HEART : ParticleTypes.SMOKE;

        for (int i = 0; i < 7; i++) {
            double d = this.random.nextGaussian() * 0.02;
            double e = this.random.nextGaussian() * 0.02;
            double f = this.random.nextGaussian() * 0.02;
            this.level().addParticle(particleOptions, this.getRandomX(1.0), this.getRandomY() + 0.5, this.getRandomZ(1.0), d, e, f);
        }
    }

    @Override
    public boolean causeFallDamage(float f, float g, DamageSource damageSource) {
        if (f > 1.0F) {
            this.playSound(SoundEvents.HORSE_LAND, 0.4F, 1.0F);
        }

        int i = this.calculateFallDamage(f, g);
        if (i <= 0) {
            return false;
        }

        this.hurt(damageSource, i);
        if (this.isVehicle()) {
            for (Entity entity : this.getIndirectPassengers()) {
                entity.hurt(damageSource, i);
            }
        }

        this.playBlockFallSound();
        return true;
    }

    @Override
    public void handleEntityEvent(byte b) {
        if (b == 7) {
            this.spawnTamingParticles(true);
        } else if (b == 6) {
            this.spawnTamingParticles(false);
        } else {
            super.handleEntityEvent(b);
        }
    }

    @Override
    protected void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
        super.positionRider(entity, moveFunction);
        if (entity instanceof LivingEntity) {
            ((LivingEntity)entity).yBodyRot = this.yBodyRot;
        }
    }

    protected void doPlayerRide(Player player) {
        this.setEating(false);
        if (!this.level().isClientSide) {
            player.setYRot(this.getYRot());
            player.setXRot(this.getXRot());
            player.startRiding(this);
        }
    }

    @Override
    public void openCustomInventoryScreen(Player player) {
        if (!this.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
            if ((!this.isVehicle() || this.hasPassenger(player)) && this.isTamed()) {
                MenuRegistry.openExtendedMenu(serverPlayer, new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return AbstractGriffon.this.getDisplayName();
                    }

                    @Override
                    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player playerEntity) {
                        return new AbstractGriffonMenu(containerId, inventory, AbstractGriffon.this, AbstractGriffon.this.inventory);
                    }
                }, buf -> {
                    buf.writeInt(this.getId());
                });
            }
        }
    }

    public boolean hasInventoryChanged(Container container) {
        return this.inventory != container;
    }

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && this.isTamed();
    }

    @Override
    public void equipSaddle(ItemStack itemStack, @Nullable SoundSource soundSource) {
        this.inventory.setItem(0, itemStack);
    }

    @Override
    public boolean isSaddled() {
        return this.getFlag(4);
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        if (this.isSaddled() && this.getFirstPassenger() instanceof Player player) {
            return player;
        }
        return super.getControllingPassenger();
    }

    @Override
    public void travel(Vec3 travelVector) {
        LivingEntity controller = this.getControllingPassenger();

        if (controller instanceof Player player) {
            OldWorldFantasy.LOG.info("Controlling player: {}", player.getName());
            this.setYRot(player.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(player.getXRot() * 0.5F);
            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.getYRot();

            float strafe = player.xxa * 0.5F;
            float forward = player.zza;

            if (forward <= 0.0F) {
                forward *= 0.25F;
            }

            if (this.isFlying()) {
                OldWorldFantasy.LOG.info("Flying: {}", isFlying());
                this.setNoGravity(true);
                travelFlying(player, forward, strafe);
            } else {
                OldWorldFantasy.LOG.info("Flying: {}", isFlying());
                this.setNoGravity(false);
                super.travel(new Vec3(strafe, travelVector.y, forward));
            }
            return;
        }

        OldWorldFantasy.LOG.info("Setting default behaviour");
        this.setNoGravity(this.isFlying());
        super.travel(travelVector);
    }

    private void travelFlying(Player player, float forward, float strafe) {
        float speed = 0.5F;

        Vec3 lookVec = player.getLookAngle();
        Vec3 motionIntent = Vec3.ZERO;
        if (forward > 0) motionIntent = motionIntent.add(lookVec);
        if (forward < 0) motionIntent = motionIntent.subtract(lookVec);

        if (strafe != 0) {
            Vec3 sideVec = lookVec.cross(new Vec3(0, 1, 0)).normalize();
            if (strafe > 0) motionIntent = motionIntent.subtract(sideVec);
            if (strafe < 0) motionIntent = motionIntent.add(sideVec);
        }

        if (motionIntent.lengthSqr() > 1.0D) {
            motionIntent = motionIntent.normalize();
        }

        Vec3 finalMovement = motionIntent.scale(speed);
        this.setDeltaMovement(finalMovement);
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.91D));
    }

    @Nullable
    private Vec3 getDismountLocationInDirection(Vec3 vec3, LivingEntity livingEntity) {
        double d = this.getX() + vec3.x;
        double e = this.getBoundingBox().minY;
        double f = this.getZ() + vec3.z;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for (Pose pose : livingEntity.getDismountPoses()) {
            mutableBlockPos.set(d, e, f);
            double g = this.getBoundingBox().maxY + 0.75;

            do {
                double h = this.level().getBlockFloorHeight(mutableBlockPos);
                if (mutableBlockPos.getY() + h > g) {
                    break;
                }

                if (DismountHelper.isBlockFloorValid(h)) {
                    AABB aABB = livingEntity.getLocalBoundsForPose(pose);
                    Vec3 vec32 = new Vec3(d, mutableBlockPos.getY() + h, f);
                    if (DismountHelper.canDismountTo(this.level(), livingEntity, aABB.move(vec32))) {
                        livingEntity.setPose(pose);
                        return vec32;
                    }
                }

                mutableBlockPos.move(Direction.UP);
            } while (mutableBlockPos.getY() < g);
        }

        return null;
    }

    @Override
    public SlotAccess getSlot(int i) {
        int j = i - 400;
        if (j == 0) {
            return new SlotAccess() {
                @Override
                public ItemStack get() {
                    return AbstractGriffon.this.inventory.getItem(0);
                }

                @Override
                public boolean set(ItemStack itemStack) {
                    if (!itemStack.isEmpty() && !itemStack.is(Items.SADDLE)) {
                        return false;
                    }

                    AbstractGriffon.this.inventory.setItem(0, itemStack);
                    AbstractGriffon.this.syncSaddleToClients();
                    return true;
                }
            };
        }

        int k = i - 500 + 1;
        return k >= 1 && k < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, k) : super.getSlot(i);
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity livingEntity) {
        Vec3 vec3 = getCollisionHorizontalEscapeVector(
                this.getBbWidth(), livingEntity.getBbWidth(), this.getYRot() + (livingEntity.getMainArm() == HumanoidArm.RIGHT ? 90.0F : -90.0F)
        );
        Vec3 vec32 = this.getDismountLocationInDirection(vec3, livingEntity);
        if (vec32 != null) {
            return vec32;
        }

        Vec3 vec33 = getCollisionHorizontalEscapeVector(
                this.getBbWidth(), livingEntity.getBbWidth(), this.getYRot() + (livingEntity.getMainArm() == HumanoidArm.LEFT ? 90.0F : -90.0F)
        );
        Vec3 vec34 = this.getDismountLocationInDirection(vec33, livingEntity);
        return vec34 != null ? vec34 : this.position();
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity entity, EntityDimensions entityDimensions, float f) {
        return super.getPassengerAttachmentPoint(entity, entityDimensions, f);
    }

    @Override
    public void aiStep() {
        if (this.getControllingPassenger() instanceof Player) {
            super.aiStep();
            return;
        }
        super.aiStep();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getControllingPassenger() instanceof Player) {
            this.getXRot();
            this.getNavigation().stop();
        }
    }
}
