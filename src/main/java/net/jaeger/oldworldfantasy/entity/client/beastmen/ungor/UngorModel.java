package net.jaeger.oldworldfantasy.entity.client.beastmen.ungor;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.beastmen.ungor.Ungor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


public class UngorModel extends GeoModel<Ungor> {

    @Override
    public ResourceLocation getModelResource(Ungor animatable) {
        return OldWorldFantasyMod.res("geo/entity/ungor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Ungor animatable) {
        return OldWorldFantasyMod.res("textures/entity/ungor/ungor_brown.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Ungor animatable) {
        return OldWorldFantasyMod.res("animations/entity/ungor.animation.json");
    }
}