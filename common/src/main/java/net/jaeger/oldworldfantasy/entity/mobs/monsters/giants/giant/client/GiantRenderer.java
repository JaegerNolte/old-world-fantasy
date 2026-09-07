package net.jaeger.oldworldfantasy.entity.mobs.monsters.giants.giant.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.giants.giant.Giant;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class GiantRenderer extends GeoEntityRenderer<Giant> {

    public GiantRenderer(EntityRendererProvider.Context context) {
        super(context, new GiantModel());
        this.shadowRadius = 0.5f;

        addRenderLayer(new BlockAndItemGeoLayer<>(this,
                (bone, animatable) -> {
                    if (bone.getName().equals("RightHandItem")) {
                        return animatable.getMainHandItem();
                    }

                    return null;
                }, (bone, animatable) -> null) {

            @Override
            protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, Giant animatable) {
                return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
            }

            @Override
            protected void renderStackForBone(PoseStack poseStack, GeoBone bone,
                                              ItemStack stack, Giant animatable, MultiBufferSource bufferSource,
                                              float partialTick, int packedLight, int packedOverlay) {
                if (bone.getName().equals("RightHandItem")) {
                    poseStack.translate(0.05, -1.00, -0.40);
                    poseStack.scale(1.5f, 1.5f, 1.5f);
                    poseStack.mulPose(Axis.XP.rotationDegrees(-75));
                }

                super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(Giant animatable) {
        return OldWorldFantasy.res("textures/entity/giant/giant.png");
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack,
                                    Giant animatable, BakedGeoModel model, boolean isReRender,
                                    float partialTick, int packedLight, int packedOverlay) {

        poseStack.scale(2.8f, 2.8f, 2.8f);

        super.scaleModelForRender(widthScale, heightScale, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }
}
