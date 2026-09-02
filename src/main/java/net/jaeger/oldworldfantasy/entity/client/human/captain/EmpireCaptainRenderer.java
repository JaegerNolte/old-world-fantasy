package net.jaeger.oldworldfantasy.entity.client.human.captain;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.captain.EmpireCaptain;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

public class EmpireCaptainRenderer extends GeoEntityRenderer<EmpireCaptain> {

    public EmpireCaptainRenderer(EntityRendererProvider.Context context) {
        super(context, new EmpireCaptainModel());
        this.shadowRadius = 0.5f;
        this.addRenderLayer(new EmpireCaptainArmorLayer(this));

        addRenderLayer(new BlockAndItemGeoLayer<>(this,
                (bone, animatable) -> {
                    if (bone.getName().equals("LeftHandItem")) {
                        return animatable.getMainHandItem();
                    }
                    if (bone.getName().equals("RightHandItem")) {
                        return animatable.getOffhandItem();
                    }
                    return null;
                }, (bone, animatable) -> null) {

            @Override
            protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, EmpireCaptain animatable) {
                return switch (bone.getName()) {
                    case "LeftHandItem" -> ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
                    case "RightHandItem" -> ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
                    default -> ItemDisplayContext.NONE;
                };
            }

            @Override
            protected void renderStackForBone(PoseStack poseStack, GeoBone bone,
                                              ItemStack stack, EmpireCaptain animatable, MultiBufferSource bufferSource,
                                              float partialTick, int packedLight, int packedOverlay) {
                if (bone.getName().equals("LeftHandItem")) {
                    poseStack.translate(0.15, -0.2, -0.3);
                    poseStack.mulPose(Axis.XP.rotationDegrees(-180));
                }
                if (bone.getName().equals("RightHandItem")) {
                    poseStack.translate(-0.1, -0.15, -0.12);
                    poseStack.mulPose(Axis.YP.rotationDegrees(180));
                    poseStack.mulPose(Axis.XP.rotationDegrees(90));
                }
                super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(EmpireCaptain animatable) {
        return OldWorldFantasyMod.res("textures/entity/empire/empire_captain.png");
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack,
                                    EmpireCaptain animatable, BakedGeoModel model, boolean isReRender,
                                    float partialTick, int packedLight, int packedOverlay) {

        poseStack.scale(1.0f, 1.0f, 1.0f);
        super.scaleModelForRender(widthScale, heightScale, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }
}
