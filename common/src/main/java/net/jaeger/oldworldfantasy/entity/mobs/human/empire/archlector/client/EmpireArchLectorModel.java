package net.jaeger.oldworldfantasy.entity.mobs.human.empire.archlector.client;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.archlector.EmpireArchLector;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireArchLectorModel extends GeoModel<EmpireArchLector> {

    @Override
    public ResourceLocation getModelResource(EmpireArchLector animatable) {
        return OldWorldFantasy.res("geo/entity/empire_soldier.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireArchLector animatable) {
        return OldWorldFantasy.res("textures/entity/empire/empire_archlector.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireArchLector animatable) {
        return OldWorldFantasy.res("animations/entity/empire_soldier.animation.json");
    }
}