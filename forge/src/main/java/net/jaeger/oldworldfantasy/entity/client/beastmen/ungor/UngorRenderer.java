package net.jaeger.oldworldfantasy.entity.client.beastmen.ungor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.ungor.Ungor;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;


public class UngorRenderer extends GeoEntityRenderer<Ungor> {

    public UngorRenderer(EntityRendererProvider.Context context) {
        super(context, new UngorModel());
        this.shadowRadius = 0.5f;

        addRenderLayer(new BlockAndItemGeoLayer<>(
                this,
                (bone, animatable) -> {
                    if (bone.getName().equals("RightHandItem")) {
                        return animatable.getMainHandItem();
                    }

                    return null;
                },
                (bone, animatable) -> null

        ) {
            @Override
            protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, Ungor animatable) {
                return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
            }

            @Override
            protected void renderStackForBone(PoseStack poseStack, GeoBone bone,
                                              ItemStack stack, Ungor animatable, MultiBufferSource bufferSource,
                                              float partialTick, int packedLight, int packedOverlay) {
                if (bone.getName().equals("RightHandItem")) {
                    poseStack.translate(-0.03, -0.1, -0.11);
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90));
                }

                super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(Ungor animatable) {
        return OldWorldFantasyMod.res("textures/entity/ungor/ungor_brown.png");
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack,
                                    Ungor animatable, BakedGeoModel model, boolean isReRender,
                                    float partialTick, int packedLight, int packedOverlay) {

        poseStack.scale(0.8f, 0.8f, 0.8f);

        super.scaleModelForRender(widthScale, heightScale, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }
}
