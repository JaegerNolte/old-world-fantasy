package net.jaeger.oldworldfantasy.entity.client.greenskin.goblin;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.goblin.Goblin;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GoblinModel extends GeoModel<Goblin> {

    @Override
    public ResourceLocation getModelResource(Goblin animatable) {
        return OldWorldFantasyMod.res("geo/entity/goblin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Goblin animatable) {
        return OldWorldFantasyMod.res("textures/entity/goblin/goblin_ardboyz.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Goblin animatable) {
        return OldWorldFantasyMod.res("animations/entity/goblin.animation.json");
    }
}
