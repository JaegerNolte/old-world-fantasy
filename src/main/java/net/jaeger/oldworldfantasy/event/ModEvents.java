package net.jaeger.oldworldfantasy.event;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.world.raids.ModRaid;
import net.jaeger.oldworldfantasy.world.raids.ModRaids;
import net.jaeger.oldworldfantasy.world.raids.RaidTypes;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OldWorldFantasyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        if (event.level instanceof ServerLevel serverLevel) {
            ModRaids.get(serverLevel).tick();
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        if (!(event.player instanceof ServerPlayer player)) {
            return;
        }
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
}