package net.jaeger.oldworldfantasy.entity.client.human.spearmen;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.spearmen.EmpireSpearmen;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireSpearmenModel extends GeoModel<EmpireSpearmen> {

    @Override
    public ResourceLocation getModelResource(EmpireSpearmen animatable) {
        return OldWorldFantasyMod.res("geo/entity/empire_soldier.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireSpearmen animatable) {
        return OldWorldFantasyMod.res("textures/entity/empire_soldier/empire_soldier.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireSpearmen animatable) {
        return OldWorldFantasyMod.res("animations/entity/empire_soldier.animation.json");
    }
}
