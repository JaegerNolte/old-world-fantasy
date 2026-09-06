package net.jaeger.oldworldfantasy.entity.client.greenskin.warboss;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.warboss.OrcWarboss;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OrcWarbossArmorModel extends GeoModel<OrcWarboss> {

    @Override
    public ResourceLocation getModelResource(OrcWarboss animatable) {
        return OldWorldFantasy.res("geo/entity/orcwarboss_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OrcWarboss animatable) {
        return OldWorldFantasy.res("textures/entity/orcwarboss/orcwarboss_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OrcWarboss animatable) {
        return null;
    }
}
