package net.jaeger.oldworldfantasy.entity.client.greenskin.biguns;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.biguns.BigUns;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BigUnsArmorModel extends GeoModel<BigUns> {

    @Override
    public ResourceLocation getModelResource(BigUns animatable) {
        return OldWorldFantasy.res("geo/entity/biguns_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BigUns animatable) {
        return OldWorldFantasy.res("textures/entity/biguns/biguns_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BigUns animatable) {
        return null;
    }
}
