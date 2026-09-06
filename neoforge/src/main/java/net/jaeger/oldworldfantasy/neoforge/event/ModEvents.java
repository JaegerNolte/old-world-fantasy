package net.jaeger.oldworldfantasy.neoforge.event;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.event.ModCommonEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = OldWorldFantasy.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            ModCommonEvents.onLevelTick(serverLevel);
        }
    }

    @SubscribeEvent
    public static void onMobDeath(LivingDeathEvent event) {
        ModCommonEvents.onMobDeath(event.getEntity(), event.getSource());
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {

        if (event.getEntity() instanceof ServerPlayer player) {
            ModCommonEvents.onPlayerTick(player);
        }
    }
}
