package net.jaeger.oldworldfantasy.entity.client.human.crossbowmen;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.crossbowmen.EmpireCrossbowmen;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireCrossbowmenArmorModel extends GeoModel<EmpireCrossbowmen> {

    @Override
    public ResourceLocation getModelResource(EmpireCrossbowmen animatable) {
        return OldWorldFantasyMod.res("geo/item/armor/empire_soldier_armor_1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireCrossbowmen animatable) {
        return OldWorldFantasyMod.res("textures/item/armor/imperial_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireCrossbowmen animatable) {
        return null;
    }
}
