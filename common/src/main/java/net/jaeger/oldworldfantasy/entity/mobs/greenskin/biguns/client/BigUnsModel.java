package net.jaeger.oldworldfantasy.entity.mobs.greenskin.biguns.client;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.biguns.BigUns;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BigUnsModel extends GeoModel<BigUns> {

    @Override
    public ResourceLocation getModelResource(BigUns animatable) {
        return OldWorldFantasy.res("geo/entity/biguns.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BigUns animatable) {
        return OldWorldFantasy.res("textures/entity/biguns/biguns_ardboyz.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BigUns animatable) {
        return OldWorldFantasy.res("animations/entity/biguns.animation.json");
    }
}
