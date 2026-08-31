package net.jaeger.oldworldfantasy.entity.client.human.captian;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.captain.EmpireCaptain;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireCaptainArmorModel extends GeoModel<EmpireCaptain> {

    @Override
    public ResourceLocation getModelResource(EmpireCaptain animatable) {
        return OldWorldFantasyMod.res("geo/item/armor/empire_soldier_armor_1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireCaptain animatable) {
        return OldWorldFantasyMod.res("textures/item/armor/imperial_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireCaptain animatable) {
        return null;
    }
}
