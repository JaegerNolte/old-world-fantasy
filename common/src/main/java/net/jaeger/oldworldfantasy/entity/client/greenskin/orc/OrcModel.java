package net.jaeger.oldworldfantasy.entity.client.greenskin.orc;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.orc.Orc;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OrcModel extends GeoModel<Orc> {

    @Override
    public ResourceLocation getModelResource(Orc animatable) {
        return OldWorldFantasy.res("geo/entity/orc.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Orc animatable) {
        return OldWorldFantasy.res("textures/entity/orc/orc_ardboyz.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Orc animatable) {
        return OldWorldFantasy.res("animations/entity/orc.animation.json");
    }
}
