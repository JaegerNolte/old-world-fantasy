package net.jaeger.oldworldfantasy.entity.client.beastmen.ungor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jaeger.oldworldfantasy.entity.custom.beastmen.ungor.Ungor;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class UngorItemLayer extends GeoRenderLayer<Ungor> {

    public UngorItemLayer(GeoRenderer<Ungor> entityRendererIn) {
        super(entityRendererIn);
    }


    @Override
    public void renderForBone(PoseStack poseStack, Ungor animatable, GeoBone bone, RenderType renderType,
                              MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick,
                              int packedLight, int packedOverlay) {

        super.renderForBone(poseStack, animatable, bone, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay);
    }
}
