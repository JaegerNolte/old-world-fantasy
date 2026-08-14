package net.jaeger.oldworldfantasy.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;

public final class ModClientRenderer {

    private static BlockEntityWithoutLevelRenderer renderer;

    public static BlockEntityWithoutLevelRenderer get() {
        if (renderer == null) {
            renderer = new ModBlockEntityWithoutLevelRenderer(
                    Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                    Minecraft.getInstance().getEntityModels()
            );
        }

        return renderer;
    }
}
