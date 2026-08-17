package net.jaeger.oldworldfantasy.client.render.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jaeger.oldworldfantasy.client.model.ModModels;
import net.jaeger.oldworldfantasy.client.model.item.ModBaseShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ModBlockEntityWithoutLevelRenderer extends BlockEntityWithoutLevelRenderer {

    private final EntityModelSet entityModelSet;
    private final Map<ModelLayerLocation, ModBaseShieldModel> models = new HashMap<>();

    public ModBlockEntityWithoutLevelRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);
        this.entityModelSet = entityModelSet;
    }

    private ModBaseShieldModel getModel(Item item) {
        ModelLayerLocation location = ModModels.INSTANCE.getModel(item);
        if (location == null) {
            return null;
        }
        return models.computeIfAbsent(location, layer -> {
            ModelPart root = entityModelSet.bakeLayer(layer);
            Function<ModelPart, ? extends ModBaseShieldModel> factory = ModModels.INSTANCE.getModelFactory(layer);
            return factory.apply(root);
        });
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ModBaseShieldModel model = getModel(stack.getItem());
        if (model == null) {
            return;
        }
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        ResourceLocation texture = getShieldTexture(stack.getItem());
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entitySolid(texture));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, 0xFFFFFFFF);
        poseStack.popPose();
    }

    private ResourceLocation getShieldTexture(Item item) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
        return ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), "textures/entity/shield/" + itemId.getPath() + ".png");
    }
}
