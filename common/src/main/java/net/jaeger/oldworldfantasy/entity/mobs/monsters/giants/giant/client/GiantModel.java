package net.jaeger.oldworldfantasy.entity.mobs.monsters.giants.giant.client;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.giants.giant.Giant;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GiantModel extends GeoModel<Giant> {

    @Override
    public ResourceLocation getModelResource(Giant animatable) {
        return OldWorldFantasy.res("geo/entity/giant.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Giant animatable) {
        return OldWorldFantasy.res("textures/entity/giant/giant.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Giant animatable) {
        return OldWorldFantasy.res("animations/entity/giant.animation.json");
    }
}
