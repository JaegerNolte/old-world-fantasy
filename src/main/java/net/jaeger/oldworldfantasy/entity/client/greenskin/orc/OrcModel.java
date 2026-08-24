package net.jaeger.oldworldfantasy.entity.client.greenskin.orc;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.greenskin.orc.Orc;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OrcModel extends GeoModel<Orc> {

    @Override
    public ResourceLocation getModelResource(Orc animatable) {
        return OldWorldFantasyMod.res("geo/entity/orc.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Orc animatable) {
        return OldWorldFantasyMod.res("textures/entity/orc/orc_ardboyz.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Orc animatable) {
        return OldWorldFantasyMod.res("animations/entity/orc.animation.json");
    }
}
