package net.jaeger.oldworldfantasy.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;

public class ModClientRenderer {

    protected static BlockEntityWithoutLevelRenderer renderer;

    public static BlockEntityWithoutLevelRenderer getModelRender() {
        if (renderer == null) {
            renderer = new ModBlockEntityWithoutLevelRenderer(
                    Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                    Minecraft.getInstance().getEntityModels()
            );
        }

        return renderer;
    }
}
