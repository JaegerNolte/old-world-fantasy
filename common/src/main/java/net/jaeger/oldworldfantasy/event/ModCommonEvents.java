package net.jaeger.oldworldfantasy.event;

import net.jaeger.oldworldfantasy.effect.ModEffects;
import net.jaeger.oldworldfantasy.entity.ModEntities;
import net.jaeger.oldworldfantasy.world.raids.ModRaid;
import net.jaeger.oldworldfantasy.world.raids.ModRaids;
import net.jaeger.oldworldfantasy.world.raids.RaidTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ModCommonEvents {

    public static void onLevelTick(ServerLevel serverLevel) {
        ModRaids.get(serverLevel).tick();
    }

    public static void onMobDeath(LivingEntity entity, DamageSource source) {
        if (entity.level().isClientSide()) {
            return;
        }

        if (!(entity.level() instanceof ServerLevel level)) {
            return;
        }

        if (source.getEntity() instanceof Player player && isRaidOngoing(level)) {

            if (entity.getType() == ModEntities.ORCWARBOSS.get()) {
                player.addEffect(new MobEffectInstance(effectHolder(ModEffects.GREENSKIN_OMEN.get()),
                        120000, 1, false, false, true));
            }
        }
    }

    public static void onPlayerTick(ServerPlayer player) {
        if (player.tickCount % 20 != 0) {
            return;
        }
        ServerLevel level = player.serverLevel();
        if (!player.getActiveEffects().isEmpty()) {

            ModRaids modRaids = ModRaids.get(level);
            ModRaid existingRaid = modRaids.getRaidAt(player.blockPosition());

            if (existingRaid == null) {

                for (MobEffectInstance effectInstance : player.getActiveEffects()) {

                    Holder<MobEffect> effect = effectInstance.getEffect();
                    String raidType = RaidTypes.getTypeByOmen(effect);

                    if (raidType != null) {
                        ModRaid raid = modRaids.createOrExtendRaid(player, player.blockPosition(), raidType);
                        if (raid != null) {
                            player.removeEffect(effect);
                        }
                        break;
                    }
                }
            }
        }
    }

    public static boolean isRaidOngoing(ServerLevel level) {
        ModRaids raids = ModRaids.get(level);
        return raids.raidMap.isEmpty();
    }

    public static Holder<MobEffect> effectHolder(MobEffect effect){
        return BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect);
    }
}