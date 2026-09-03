package net.jaeger.oldworldfantasy.effect.omens;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class GreenskinOmen extends MobEffect {

    public GreenskinOmen(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return false;
    }
}
