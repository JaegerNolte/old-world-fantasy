package net.jaeger.oldworldfantasy.entity.client.beastmen.gor;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.gor.Gor;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class GorRenderer extends GeoEntityRenderer<Gor> {


    public GorRenderer(EntityRendererProvider.Context context) {
        super(context, new GorModel());
        this.shadowRadius = .5f;
    }

    @Override
    public ResourceLocation getTextureLocation(Gor animatable) {
        return OldWorldFantasyMod.res("textures/entity/gor/gor_brown.png");
    }
}
