package net.jaeger.oldworldfantasy.item.custom.items.shield;

import net.jaeger.oldworldfantasy.client.render.tileentity.ModClientRenderer;
import net.jaeger.oldworldfantasy.item.IHasModelProperty;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class ModShieldItem extends ShieldItem implements IHasModelProperty {

    public ModShieldItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void registerModelProperty() {
        ItemProperties.register(this, ResourceLocation.withDefaultNamespace("blocking"), (itemStack, level, entity, useDur) ->
                entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F
        );
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
