package net.jaeger.oldworldfantasy.entity.client.ungor;

import com.mojang.blaze3d.vertex.PoseStack;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.beastmen.ungor.Ungor;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class UngorRenderer extends MobRenderer<Ungor, UngorModel<Ungor>> {


    public UngorRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new UngorModel<>(pContext.bakeLayer(UngorModel.LAYER_LOCATION)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(Ungor pEntity) {
        return ResourceLocation.fromNamespaceAndPath(OldWorldFantasyMod.MOD_ID, "textures/entity/ungor/ungor_brown.png");
    }

    @Override
    public void render(Ungor pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.scale(0.8f, 0.8f,0.8f);
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
