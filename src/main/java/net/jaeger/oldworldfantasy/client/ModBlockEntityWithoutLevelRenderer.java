package net.jaeger.oldworldfantasy.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.client.model.ImperialShieldModel;
import net.jaeger.oldworldfantasy.client.model.ModBaseShieldModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class ModBlockEntityWithoutLevelRenderer extends BlockEntityWithoutLevelRenderer {

    private Model model;

    public ModBlockEntityWithoutLevelRenderer(
            BlockEntityRenderDispatcher blockEntityRenderDispatcher,
            EntityModelSet entityModelSet
    ) {
        super(
                blockEntityRenderDispatcher,
                entityModelSet
        );

        this.model = new ImperialShieldModel(
                entityModelSet.bakeLayer(
                        ImperialShieldModel.LAYER_LOCATION
                )
        );
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (this.model instanceof ModBaseShieldModel shieldModel) {
            System.out.println("CUSTOM SHIELD RENDER: " + context);
            poseStack.pushPose();
            poseStack.scale(1.0F, -1.0F, -1.0F);
            VertexConsumer vertexConsumer =
                    bufferSource.getBuffer(RenderType.entitySolid(OldWorldFantasyMod.res("textures/entity/shield/imperial_shield.png")));
        shieldModel.renderToBuffer(
                    poseStack,
                    vertexConsumer,
                    packedLight,
                    packedOverlay,
                    0xFFFFFFFF
            );
            poseStack.popPose();
        }
    }
}
