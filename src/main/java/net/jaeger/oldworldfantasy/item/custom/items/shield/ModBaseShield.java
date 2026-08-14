package net.jaeger.oldworldfantasy.item.custom.items.shield;

import net.jaeger.oldworldfantasy.client.ModClientRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ModBaseShield extends ShieldItem {

    public ModBaseShield(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ModClientRenderer.get();
            }
        });
    }
}
