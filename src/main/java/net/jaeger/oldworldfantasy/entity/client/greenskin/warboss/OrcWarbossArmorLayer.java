package net.jaeger.oldworldfantasy.entity.client.greenskin.warboss;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.warboss.OrcWarboss;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.Optional;

public class OrcWarbossArmorLayer extends GeoRenderLayer<OrcWarboss> {

    private final OrcWarbossArmorModel armorModel = new OrcWarbossArmorModel();

    public OrcWarbossArmorLayer(GeoRenderer<OrcWarboss> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, OrcWarboss animatable, BakedGeoModel bakedModel, RenderType renderType,
                       MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        BakedGeoModel armorBakedModel = armorModel.getBakedModel(armorModel.getModelResource(animatable));

        matchBonePose(bakedModel, armorBakedModel, "body");
        matchBonePose(bakedModel, armorBakedModel, "head");
        matchBonePose(bakedModel, armorBakedModel, "upper_body");
        matchBonePose(bakedModel, armorBakedModel, "left_arm");
        matchBonePose(bakedModel, armorBakedModel, "right_arm");
        matchBonePose(bakedModel, armorBakedModel, "left_leg");
        matchBonePose(bakedModel, armorBakedModel, "right_leg");

        RenderType armorRenderType = RenderType.entityCutoutNoCull(armorModel.getTextureResource(animatable));

        getRenderer().reRender(armorBakedModel, poseStack, bufferSource, animatable, armorRenderType,
                bufferSource.getBuffer(armorRenderType), partialTick, packedLight, packedOverlay, 0xFFFFFFFF);
    }

    private void matchBonePose(BakedGeoModel sourceModel, BakedGeoModel targetModel, String boneName) {

        Optional<GeoBone> sourceOptional = sourceModel.getBone(boneName);
        Optional<GeoBone> targetOptional = targetModel.getBone(boneName);

        if (sourceOptional.isEmpty() || targetOptional.isEmpty()) {
            return;
        }

        GeoBone source = sourceOptional.get();
        GeoBone target = targetOptional.get();

        target.setRotX(source.getRotX());
        target.setRotY(source.getRotY());
        target.setRotZ(source.getRotZ());

        target.setPosX(source.getPosX());
        target.setPosY(source.getPosY());
        target.setPosZ(source.getPosZ());

        target.setScaleX(source.getScaleX());
        target.setScaleY(source.getScaleY());
        target.setScaleZ(source.getScaleZ());
    }
}
