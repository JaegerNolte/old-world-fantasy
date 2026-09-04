package net.jaeger.oldworldfantasy.entity.client.human.archlector;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.archlector.EmpireArchLector;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireArchLectorModel extends GeoModel<EmpireArchLector> {

    @Override
    public ResourceLocation getModelResource(EmpireArchLector animatable) {
        return OldWorldFantasyMod.res("geo/entity/empire_soldier.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireArchLector animatable) {
        return OldWorldFantasyMod.res("textures/entity/empire/empire_archlector.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireArchLector animatable) {
        return OldWorldFantasyMod.res("animations/entity/empire_soldier.animation.json");
    }
}