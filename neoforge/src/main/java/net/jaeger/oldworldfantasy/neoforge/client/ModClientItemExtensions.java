package net.jaeger.oldworldfantasy.neoforge.client;

import net.jaeger.oldworldfantasy.client.render.tileentity.ModClientRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

public class ModClientItemExtensions extends ModClientRenderer implements IClientItemExtensions {

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return getModelRender();
    }
}
