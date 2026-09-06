package net.jaeger.oldworldfantasy.entity.client.human.spearmen;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.EmpireVariant;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.spearmen.EmpireSpearmen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;


public class EmpireSpearmenRenderer extends GeoEntityRenderer<EmpireSpearmen> {

    public EmpireSpearmenRenderer(EntityRendererProvider.Context context) {
        super(context, new EmpireSpearmenModel());
        this.shadowRadius = 0.5f;
        this.addRenderLayer(new EmpireSpearmenArmorLayer(this));

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
            protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, EmpireSpearmen animatable) {
                return switch (bone.getName()) {
                    case "LeftHandItem" -> ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
                    case "RightHandItem" -> ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
                    default -> ItemDisplayContext.NONE;
                };
            }

            @Override
            protected void renderStackForBone(PoseStack poseStack, GeoBone bone,
                                              ItemStack stack, EmpireSpearmen animatable, MultiBufferSource bufferSource,
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
    public ResourceLocation getTextureLocation(EmpireSpearmen animatable) {
        return EmpireVariant.LOCATION_BY_VARIANT.get(animatable.getVariant());
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack,
                                    EmpireSpearmen animatable, BakedGeoModel model, boolean isReRender,
                                    float partialTick, int packedLight, int packedOverlay) {

        poseStack.scale(1.0f, 1.0f, 1.0f);
        super.scaleModelForRender(widthScale, heightScale, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }
}
