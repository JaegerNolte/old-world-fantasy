package net.jaeger.oldworldfantasy.item.custom.items;

import net.jaeger.oldworldfantasy.client.ModBlockEntityWithoutLevelRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ModBaseShield extends ShieldItem {

    public ModBaseShield(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void initializeClient(
            Consumer<IClientItemExtensions> consumer) {
                consumer.accept(new IClientItemExtensions() {

                private BlockEntityWithoutLevelRenderer renderer;

                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {

                    if (renderer == null) {
                        renderer = new ModBlockEntityWithoutLevelRenderer(
                                Minecraft.getInstance()
                                        .getBlockEntityRenderDispatcher(),

                                Minecraft.getInstance()
                                        .getEntityModels()
                        );
                    }

                    return renderer;
                }
            });
    }
}
