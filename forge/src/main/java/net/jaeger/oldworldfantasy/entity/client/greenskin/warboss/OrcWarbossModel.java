package net.jaeger.oldworldfantasy.entity.client.greenskin.warboss;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.warboss.OrcWarboss;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OrcWarbossModel extends GeoModel<OrcWarboss> {

    @Override
    public ResourceLocation getModelResource(OrcWarboss animatable) {
        return OldWorldFantasyMod.res("geo/entity/orcwarboss.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OrcWarboss animatable) {
        return OldWorldFantasyMod.res("textures/entity/orcwarboss/orcwarboss_ardboyz.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OrcWarboss animatable) {
        return OldWorldFantasyMod.res("animations/entity/orcwarboss.animation.json");
    }
}
