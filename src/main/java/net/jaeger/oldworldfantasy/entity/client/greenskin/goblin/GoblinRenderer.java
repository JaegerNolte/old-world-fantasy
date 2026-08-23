package net.jaeger.oldworldfantasy.entity.client.greenskin.goblin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.greenskin.goblin.Goblin;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GoblinRenderer extends GeoEntityRenderer<Goblin> {

    public GoblinRenderer(EntityRendererProvider.Context context) {
        super(context, new GoblinModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(Goblin animatable) {
        return OldWorldFantasyMod.res("textures/entity/goblin/goblin_ardboyz.png");
    }

    @Override
    public void scaleModelForRender(float widthScale, float heightScale, PoseStack poseStack,
                                    Goblin animatable, BakedGeoModel model, boolean isReRender,
                                    float partialTick, int packedLight, int packedOverlay) {

        poseStack.scale(0.6f, 0.6f, 0.6f);

        super.scaleModelForRender(widthScale, heightScale, poseStack, animatable, model, isReRender, partialTick, packedLight, packedOverlay);
    }
}
