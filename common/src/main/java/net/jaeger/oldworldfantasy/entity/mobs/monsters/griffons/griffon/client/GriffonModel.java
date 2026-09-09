package net.jaeger.oldworldfantasy.entity.mobs.monsters.griffons.griffon.client;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.griffons.griffon.Griffon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GriffonModel extends GeoModel<Griffon> {

    @Override
    public ResourceLocation getModelResource(Griffon animatable) {
        return OldWorldFantasy.res("geo/entity/griffon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Griffon animatable) {
        return OldWorldFantasy.res("textures/entity/griffon/griffon.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Griffon animatable) {
        return OldWorldFantasy.res("animations/entity/griffon.animation.json");
    }
}
