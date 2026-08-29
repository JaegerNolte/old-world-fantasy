package net.jaeger.oldworldfantasy.effect.omens;

import net.jaeger.oldworldfantasy.effect.ModEffects;
import net.jaeger.oldworldfantasy.worldgen.raids.ModRaid;
import net.jaeger.oldworldfantasy.worldgen.raids.ModRaids;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class BeastmenOmen extends MobEffect {

    public BeastmenOmen(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {

        if (entity instanceof ServerPlayer serverPlayer && !serverPlayer.isSpectator()) {
            ServerLevel serverLevel = serverPlayer.serverLevel();
            BlockPos blockPos = serverPlayer.getRaidOmenPosition();
            if (blockPos != null) {
                ModRaid raid = ModRaids.get(serverLevel).createOrExtendRaid(serverPlayer, blockPos);
                if (raid != null) {
                    serverPlayer.clearRaidOmenPosition();
                    serverPlayer.removeEffect(ModEffects.BEASTMEN_OMEN.getHolder().get());
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier) {
        return true;
    }
}
