package net.jaeger.oldworldfantasy.entity.mobs.monsters.trolls.stone.client;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.trolls.stone.StoneTroll;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StoneTrollModel extends GeoModel<StoneTroll> {

    @Override
    public ResourceLocation getModelResource(StoneTroll animatable) {
        return OldWorldFantasy.res("geo/entity/stone_troll.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StoneTroll animatable) {
        return OldWorldFantasy.res("textures/entity/troll/stone_troll.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StoneTroll animatable) {
        return OldWorldFantasy.res("animations/entity/troll.animation.json");
    }
}
