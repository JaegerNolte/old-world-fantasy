package net.jaeger.oldworldfantasy.neoforge.client;

import net.jaeger.oldworldfantasy.client.render.tileentity.ModBlockEntityWithoutLevelRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class ModClientItemExtensions implements IClientItemExtensions {

    private final BlockEntityWithoutLevelRenderer renderer;

    public ModClientItemExtensions() {
        this.renderer = new ModBlockEntityWithoutLevelRenderer(
                Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                Minecraft.getInstance().getEntityModels()
        );
    }

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return this.renderer;
    }
}
