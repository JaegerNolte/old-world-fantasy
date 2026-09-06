package net.jaeger.oldworldfantasy.entity.client.beastmen.wargor;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.wargor.Wargor;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class WargorRenderer extends GeoEntityRenderer<Wargor> {


    public WargorRenderer(EntityRendererProvider.Context context) {
        super(context, new WargorModel());
        this.shadowRadius = .5f;
    }

    @Override
    public ResourceLocation getTextureLocation(Wargor animatable) {
        return OldWorldFantasy.res("textures/entity/wargor/wargor_brown.png");
    }
}
