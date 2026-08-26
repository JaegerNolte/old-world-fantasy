package net.jaeger.oldworldfantasy.entity.client.greenskin.warboss;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.warboss.OrcWarboss;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OrcWarbossRenderer extends GeoEntityRenderer<OrcWarboss> {

    public OrcWarbossRenderer(EntityRendererProvider.Context context) {
        super(context, new OrcWarbossModel());
        this.shadowRadius = 0.6f;
        this.addRenderLayer(new OrcWarbossArmorLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(OrcWarboss animatable) {
        return OldWorldFantasyMod.res("textures/entity/orcwarboss/orcwarboss_ardboyz.png");
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack,
                                    OrcWarboss animatable, BakedGeoModel model, boolean isReRender,
                                    float partialTick, int packedLight, int packedOverlay) {

        poseStack.scale(1.0f, 1.0f, 1.0f);

        super.scaleModelForRender(widthScale, heightScale, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }
}
