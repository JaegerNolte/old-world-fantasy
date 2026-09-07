package net.jaeger.oldworldfantasy.entity.mobs.greenskin.warboss.client;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.warboss.OrcWarboss;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OrcWarbossModel extends GeoModel<OrcWarboss> {

    @Override
    public ResourceLocation getModelResource(OrcWarboss animatable) {
        return OldWorldFantasy.res("geo/entity/orcwarboss.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OrcWarboss animatable) {
        return OldWorldFantasy.res("textures/entity/orcwarboss/orcwarboss_ardboyz.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OrcWarboss animatable) {
        return OldWorldFantasy.res("animations/entity/orcwarboss.animation.json");
    }
}
