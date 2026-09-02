package net.jaeger.oldworldfantasy.entity.mobs.human;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.ModEntityTags;
import net.jaeger.oldworldfantasy.entity.mobs.ModRaider;
import net.jaeger.oldworldfantasy.event.entity.player.ModTradeWithMerchantEvent;
import net.jaeger.oldworldfantasy.sound.ModSounds;
import net.jaeger.oldworldfantasy.world.item.trading.ModMerchant;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nullable;

public abstract class AbstractHuman extends ModRaider implements ModMerchant {

    @Nullable
    private Player tradingPlayer;

    protected AbstractHuman(EntityType<? extends ModRaider> pEntityType, Level pLevel) {
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
            return !pEntity.getType().is(ModEntityTags.EMPIRE) ? false : this.getTeam() == null && pEntity.getTeam() == null;
        }
    }

    protected class RaiderOpenDoorGoal extends OpenDoorGoal {
        public RaiderOpenDoorGoal(final ModRaider pRaider) {
            super(pRaider, false);
        }

        @Override
        public boolean canUse() {
            return super.canUse() && AbstractHuman.this.hasActiveRaid();
        }
    }

    public final boolean isEmpire() {
        return this.getType().is(ModEntityTags.EMPIRE);
    }

    @Override
    public void notifyTrade(MerchantOffer pOffer) {
        pOffer.increaseUses();
        this.ambientSoundTime = -this.getAmbientSoundInterval();
        this.rewardTradeXp(pOffer);

        if (this.tradingPlayer != null) {
            MinecraftForge.EVENT_BUS.post(new ModTradeWithMerchantEvent(this.tradingPlayer, pOffer, this));
        }
    }

    @Override
    public void notifyTradeUpdated(ItemStack pStack) {
        if (!this.level().isClientSide && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
            this.ambientSoundTime = -this.getAmbientSoundInterval();
            this.makeSound(this.getTradeUpdatedSound(!pStack.isEmpty()));
        }
    }

    protected SoundEvent getTradeUpdatedSound(boolean pIsYesSound) {
        return pIsYesSound ? ModSounds.HUMAN_SATISFIED.get() : ModSounds.HUMAN_ANGRY.get();
    }

    protected abstract void rewardTradeXp(MerchantOffer pOffer);

    @Override
    protected ResourceKey<LootTable> getDefaultLootTable() {
        return ResourceKey.create(
                Registries.LOOT_TABLE,
                OldWorldFantasyMod.res("entities/empire")
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
