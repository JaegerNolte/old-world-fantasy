package net.jaeger.oldworldfantasy.entity.mobs.greenskin;

import net.jaeger.oldworldfantasy.entity.ModEntityTags;
import net.jaeger.oldworldfantasy.entity.ModRaider;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;

public abstract class AbstractGreenskin extends ModRaider {

    protected AbstractGreenskin(EntityType<? extends Raider> pEntityType, Level pLevel) {
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
            return !pEntity.getType().is(ModEntityTags.GREENSKIN) ? false : this.getTeam() == null && pEntity.getTeam() == null;
        }
    }

    protected class RaiderOpenDoorGoal extends OpenDoorGoal {
        public RaiderOpenDoorGoal(final Raider pRaider) {
            super(pRaider, false);
        }

        @Override
        public boolean canUse() {
            return super.canUse() && AbstractGreenskin.this.hasActiveRaid();
        }
    }

    public final boolean isGreenskin() {
        return this.getType().is(ModEntityTags.GREENSKIN);
    }
}
