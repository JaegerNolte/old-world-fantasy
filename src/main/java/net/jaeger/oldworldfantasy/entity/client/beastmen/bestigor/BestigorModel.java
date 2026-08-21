package net.jaeger.oldworldfantasy.entity.client.beastmen.bestigor;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.beastmen.bestigor.Bestigor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BestigorModel extends GeoModel<Bestigor> {

    @Override
    public ResourceLocation getModelResource(Bestigor animatable) {
        return OldWorldFantasyMod.res("geo/entity/bestigor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Bestigor animatable) {
        return OldWorldFantasyMod.res("textures/entity/bestigor/gor_brown.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Bestigor animatable) {
        return OldWorldFantasyMod.res("animations/entity/bestigor.animation.json");
    }
}
