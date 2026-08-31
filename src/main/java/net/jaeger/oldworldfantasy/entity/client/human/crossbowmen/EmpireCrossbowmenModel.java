package net.jaeger.oldworldfantasy.entity.client.human.crossbowmen;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.crossbowmen.EmpireCrossbowmen;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireCrossbowmenModel extends GeoModel<EmpireCrossbowmen> {

    @Override
    public ResourceLocation getModelResource(EmpireCrossbowmen animatable) {
        return OldWorldFantasyMod.res("geo/entity/empire_soldier.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireCrossbowmen animatable) {
        return OldWorldFantasyMod.res("textures/entity/empire_soldier/empire_soldier.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireCrossbowmen animatable) {
        return OldWorldFantasyMod.res("animations/entity/empire_soldier.animation.json");
    }
}
