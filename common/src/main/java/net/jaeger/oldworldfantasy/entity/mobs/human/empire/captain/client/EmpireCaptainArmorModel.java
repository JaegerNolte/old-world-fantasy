package net.jaeger.oldworldfantasy.entity.mobs.human.empire.captain.client;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.captain.EmpireCaptain;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireCaptainArmorModel extends GeoModel<EmpireCaptain> {

    @Override
    public ResourceLocation getModelResource(EmpireCaptain animatable) {
        return OldWorldFantasy.res("geo/item/armor/empire_captain_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireCaptain animatable) {
        return OldWorldFantasy.res("textures/item/armor/empire_captain.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireCaptain animatable) {
        return null;
    }
}
