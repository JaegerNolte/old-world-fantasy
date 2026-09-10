package net.jaeger.oldworldfantasy.entity.mobs.monsters.griffons;

import dev.architectury.registry.menu.MenuRegistry;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.ModEntityTags;
import net.jaeger.oldworldfantasy.entity.util.FlyingMount;
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
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;

import java.util.Optional;
import java.util.UUID;

public class AbstractGriffon extends TamableAnimal implements ContainerListener, HasCustomInventoryScreen, OwnableEntity, Saddleable, FlyingMount, GeoEntity {

    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS = SynchedEntityData.defineId(AbstractGriffon.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Optional<UUID>> OWNER = SynchedEntityData.defineId(AbstractGriffon.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.defineId(AbstractGriffon.class, EntityDataSerializers.BOOLEAN);
    public SimpleContainer inventory;
    @Nullable
    private UUID owner;

    private boolean isFlying;

    protected AbstractGriffon(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.createInventory();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.FOLLOW_RANGE, 30.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.30F)
                .add(Attributes.MAX_HEALTH, 55)
                .add(Attributes.ATTACK_DAMAGE, 5.0F);
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

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    @Nullable
    @Override
    public UUID getOwnerUUID() {
        return this.entityData.get(OWNER).orElse(null);
    }

    public void setOwnerUUID(@Nullable UUID uuid) {
        this.entityData.set(OWNER, Optional.ofNullable(uuid));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_FLAGS, (byte)0);
        builder.define(OWNER, Optional.empty());
        builder.define(FLYING, false);
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
        if (level().isClientSide) {
            return this.isFlying = this.entityData.get(FLYING).booleanValue();
        }
        return isFlying;
    }

    public void setFlying(boolean flying) {
        this.entityData.set(FLYING, flying);
        if (!level().isClientSide) {
            this.isFlying = flying;
        }
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

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
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
    protected void checkFallDamage(double d, boolean bl, BlockState blockState, BlockPos blockPos) {
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
        for (Entity passenger : this.getPassengers()) {
            if (passenger instanceof Player player
                    && this.isOwnedBy(player)
                    && this.getTarget() != passenger) {
                return player;
            }
        }

        return null;
    }

    @Nullable
    public Player getRidingPlayer() {
        LivingEntity passenger = getControllingPassenger();
        return passenger instanceof Player player ? player : null;
    }

    @Override
    protected @NotNull Vec3 getRiddenInput(Player player, @NotNull Vec3 travelVector) {
        float strafe = player.xxa * 0.5F;
        float forward = player.zza;

        if (forward <= 0.0F) {
            forward *= 0.25F;
        }

        return new Vec3(strafe, 0.0D, forward);
    }

    @Override
    public void travel(@NotNull Vec3 travelVector) {
        Player player = getRidingPlayer();

        if (player == null) {
            setNoGravity(isFlying());
            super.travel(travelVector);
            return;
        }

        setYRot(player.getYRot());
        yRotO = getYRot();
        setXRot(player.getXRot() * 0.5F);
        yBodyRot = getYRot();
        yHeadRot = getYRot();

        if (isFlying()) {
            setNoGravity(true);

            if (isControlledByLocalInstance()) {
                float speed = 0.5F;

                Vec3 look = player.getLookAngle();
                Vec3 forward = look.scale(player.zza);
                Vec3 right = new Vec3(-look.z, 0.0D, look.x).normalize();
                Vec3 strafe = right.scale(player.xxa);
                Vec3 movement = forward.add(strafe);

                if (movement.lengthSqr() > 1.0D) {
                    movement = movement.normalize();
                }

                movement = movement.scale(speed);
                setDeltaMovement(movement);
                move(MoverType.SELF, getDeltaMovement());
                setDeltaMovement(getDeltaMovement().scale(0.9D));
            }

            return;
        }

        setNoGravity(false);
        if (isControlledByLocalInstance()) {
            setSpeed(getRiddenSpeed(player));
            super.travel(travelVector);
        }
    }

    @Override
    protected float getRiddenSpeed(@NotNull Player player) {
        return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED);
    }

    @Override
    public void positionRider(@NotNull Entity passenger, @NotNull MoveFunction callback) {
        super.positionRider(passenger, callback);
        if (this.hasPassenger(passenger)) {
            yBodyRot = getYRot();
            setYHeadRot(passenger.getYHeadRot());
            setYBodyRot(passenger.getYRot());
        }
        passenger.setPos(this.getX(), this.getY() + 2.5F, this.getZ());
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
}
