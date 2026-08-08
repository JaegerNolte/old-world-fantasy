package net.jaeger.oldworldfantasy.entity.client.gor;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.gor.GorEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class GorRenderer extends MobRenderer<GorEntity, GorModel<GorEntity>> {


    public GorRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GorModel<>(pContext.bakeLayer(GorModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(GorEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(OldWorldFantasyMod.MOD_ID, "textures/entity/gor/gor_brown.png");
    }

    @Override
    public void render(GorEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
