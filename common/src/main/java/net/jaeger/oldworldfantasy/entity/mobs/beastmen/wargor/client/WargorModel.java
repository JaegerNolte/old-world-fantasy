package net.jaeger.oldworldfantasy.entity.mobs.beastmen.wargor.client;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.wargor.Wargor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WargorModel extends GeoModel<Wargor> {

    @Override
    public ResourceLocation getModelResource(Wargor animatable) {
        return OldWorldFantasy.res("geo/entity/wargor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Wargor animatable) {
        return OldWorldFantasy.res("textures/entity/wargor/wargor_brown.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Wargor animatable) {
        return OldWorldFantasy.res("animations/entity/wargor.animation.json");
    }
}