package net.jaeger.oldworldfantasy.entity.client.greenskin.biguns;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.biguns.BigUns;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class BigUnsRenderer extends GeoEntityRenderer<BigUns> {

    public BigUnsRenderer(EntityRendererProvider.Context context) {
        super(context, new BigUnsModel());
        this.shadowRadius = 0.6f;
        this.addRenderLayer(new BigUnsArmorLayer(this));

        addRenderLayer(new BlockAndItemGeoLayer<>(this,
                (bone, animatable) -> {
                    if (bone.getName().equals("RightHandItem")) {
                        return animatable.getMainHandItem();
                    }

                    return null;
                }, (bone, animatable) -> null) {

            @Override
            protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, BigUns animatable) {
                return ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
            }

            @Override
            protected void renderStackForBone(PoseStack poseStack, GeoBone bone,
                                              ItemStack stack, BigUns animatable, MultiBufferSource bufferSource,
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
    public ResourceLocation getTextureLocation(BigUns animatable) {
        return OldWorldFantasyMod.res("textures/entity/biguns/biguns_ardboyz.png");
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack,
                                    BigUns animatable, BakedGeoModel model, boolean isReRender,
                                    float partialTick, int packedLight, int packedOverlay) {

        poseStack.scale(1.0f, 1.0f, 1.0f);

        super.scaleModelForRender(widthScale, heightScale, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }
}
