package net.jaeger.oldworldfantasy.entity.mobs.human.empire.captain.client;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.captain.EmpireCaptain;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireCaptainModel extends GeoModel<EmpireCaptain> {

    @Override
    public ResourceLocation getModelResource(EmpireCaptain animatable) {
        return OldWorldFantasy.res("geo/entity/empire_soldier.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireCaptain animatable) {
        return OldWorldFantasy.res("textures/entity/empire/empire_captain.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireCaptain animatable) {
        return OldWorldFantasy.res("animations/entity/empire_soldier.animation.json");
    }
}