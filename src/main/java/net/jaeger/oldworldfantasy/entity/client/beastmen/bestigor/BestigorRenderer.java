package net.jaeger.oldworldfantasy.entity.client.beastmen.bestigor;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.bestigor.Bestigor;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BestigorRenderer extends GeoEntityRenderer<Bestigor> {

    public BestigorRenderer(EntityRendererProvider.Context context) {
        super(context, new BestigorModel());
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(Bestigor animatable) {
        return OldWorldFantasyMod.res("textures/entity/bestigor/bestigor_brown.png");
    }
}
