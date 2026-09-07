package net.jaeger.oldworldfantasy.entity.mobs.beastmen.bestigor.client;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.bestigor.Bestigor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BestigorModel extends GeoModel<Bestigor> {

    @Override
    public ResourceLocation getModelResource(Bestigor animatable) {
        return OldWorldFantasy.res("geo/entity/bestigor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Bestigor animatable) {
        return OldWorldFantasy.res("textures/entity/bestigor/gor_brown.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Bestigor animatable) {
        return OldWorldFantasy.res("animations/entity/bestigor.animation.json");
    }
}
