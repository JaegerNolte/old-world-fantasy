package net.jaeger.oldworldfantasy.entity.mobs.beastmen.ungor.client;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.ungor.Ungor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


public class UngorModel extends GeoModel<Ungor> {

    @Override
    public ResourceLocation getModelResource(Ungor animatable) {
        return OldWorldFantasy.res("geo/entity/ungor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Ungor animatable) {
        return OldWorldFantasy.res("textures/entity/ungor/ungor_brown.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Ungor animatable) {
        return OldWorldFantasy.res("animations/entity/ungor.animation.json");
    }
}