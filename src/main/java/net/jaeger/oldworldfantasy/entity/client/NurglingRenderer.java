package net.jaeger.oldworldfantasy.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.nurgling.NurglingEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class NurglingRenderer extends MobRenderer<NurglingEntity, NurglingModel<NurglingEntity>> {


    public NurglingRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new NurglingModel<>(pContext.bakeLayer(NurglingModel.LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(NurglingEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(OldWorldFantasyMod.MOD_ID, "textures/entity/nurgling/nurgling_green.png");
    }

    @Override
    public void render(NurglingEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
