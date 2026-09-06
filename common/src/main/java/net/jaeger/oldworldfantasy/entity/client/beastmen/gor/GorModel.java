package net.jaeger.oldworldfantasy.entity.client.beastmen.gor;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.gor.Gor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GorModel extends GeoModel<Gor> {

    @Override
    public ResourceLocation getModelResource(Gor animatable) {
        return OldWorldFantasy.res("geo/entity/gor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Gor animatable) {
        return OldWorldFantasy.res("textures/entity/gor/gor_brown.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Gor animatable) {
        return OldWorldFantasy.res("animations/entity/gor.animation.json");
    }
}