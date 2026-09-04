package net.jaeger.oldworldfantasy.entity.client.human.swordsmen;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.swordsmen.EmpireSwordsmen;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireSwordsmenModel extends GeoModel<EmpireSwordsmen> {

    @Override
    public ResourceLocation getModelResource(EmpireSwordsmen animatable) {
        return OldWorldFantasyMod.res("geo/entity/empire_soldier.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireSwordsmen animatable) {
        return OldWorldFantasyMod.res("textures/entity/empire_soldier/empire_soldier.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireSwordsmen animatable) {
        return OldWorldFantasyMod.res("animations/entity/empire_soldier.animation.json");
    }
}