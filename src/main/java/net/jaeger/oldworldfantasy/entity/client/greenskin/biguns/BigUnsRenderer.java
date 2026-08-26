package net.jaeger.oldworldfantasy.entity.client.greenskin.biguns;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.greenskin.biguns.BigUns;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BigUnsRenderer extends GeoEntityRenderer<BigUns> {

    public BigUnsRenderer(EntityRendererProvider.Context context) {
        super(context, new BigUnsModel());
        this.shadowRadius = 0.6f;
        this.addRenderLayer(new BigUnsArmorLayer(this));
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
