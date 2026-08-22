package net.jaeger.oldworldfantasy.entity.ai.goals;

import net.jaeger.oldworldfantasy.entity.ModEntityTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;
import java.util.List;

public class FrenzyBeastmenAlliesGoal extends Goal {

    private final Mob mob;
    private final double range;
    private final int duration;
    private final int amplifier;

    private int cooldown;

    public FrenzyBeastmenAlliesGoal(Mob mob, double range, int duration, int amplifier) {
        this.mob = mob;
        this.range = range;
        this.duration = duration;
        this.amplifier = amplifier;

        this.setFlags(EnumSet.noneOf(Goal.Flag.class));
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return true;
    }

    private void playAmbientSound() {
        int randomNumber = (int)(Math.random() * (7 - 0 + 1)) + 0;
        mob.level().playSound(null, mob.getX(), mob.getY(), mob.getZ(), SoundEvents.GOAT_HORN_SOUND_VARIANTS.get(randomNumber),
                                    SoundSource.HOSTILE, 1.0F, .7F);
    }

    @Override
    public void tick() {

        if (cooldown > 0) {
            cooldown--;
            return;
        }

        cooldown = 600;
        AABB area = mob.getBoundingBox().inflate(range);

        List<LivingEntity> allies = mob.level().getEntitiesOfClass(LivingEntity.class, area, entity ->
                entity.getType().is(ModEntityTags.BEASTMEN));

        for (LivingEntity ally : allies) {
            ally.addEffect(new MobEffectInstance(
                    MobEffects.DAMAGE_BOOST,
                    duration,
                    amplifier,
                    false,
                    true
            ));
            ally.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SPEED,
                    duration,
                    amplifier,
                    false,
                    true
            ));
            playAmbientSound();
        }
    }
}
