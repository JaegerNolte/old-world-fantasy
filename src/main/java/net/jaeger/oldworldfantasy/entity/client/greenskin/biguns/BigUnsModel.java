package net.jaeger.oldworldfantasy.entity.client.greenskin.biguns;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.greenskin.biguns.BigUns;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BigUnsModel extends GeoModel<BigUns> {

    @Override
    public ResourceLocation getModelResource(BigUns animatable) {
        return OldWorldFantasyMod.res("geo/entity/biguns.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BigUns animatable) {
        return OldWorldFantasyMod.res("textures/entity/biguns/biguns_ardboyz.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BigUns animatable) {
        return OldWorldFantasyMod.res("animations/entity/biguns.animation.json");
    }
}
