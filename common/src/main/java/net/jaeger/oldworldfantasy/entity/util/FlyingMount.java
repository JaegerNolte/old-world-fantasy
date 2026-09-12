package net.jaeger.oldworldfantasy.entity.util;

import net.minecraft.world.entity.player.Player;

public interface FlyingMount {

    Player getRidingPlayer();

    boolean isRidingPlayer(Player player);

    boolean isFlying();

    default boolean isGoingUp() {
        return false;
    }

    default boolean isGoingDown() {
        return false;
    }

    default boolean isHovering() {
        return false;
    }
}
