package net.jaeger.oldworldfantasy.entity.client.human.captian;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.captain.EmpireCaptain;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireCaptainModel extends GeoModel<EmpireCaptain> {

    @Override
    public ResourceLocation getModelResource(EmpireCaptain animatable) {
        return OldWorldFantasyMod.res("geo/entity/empire_soldier.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireCaptain animatable) {
        return OldWorldFantasyMod.res("textures/entity/empire/empire_captain.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireCaptain animatable) {
        return OldWorldFantasyMod.res("animations/entity/empire_soldier.animation.json");
    }
}