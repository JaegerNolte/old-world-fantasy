package net.jaeger.oldworldfantasy.entity.mobs.human.empire.spearmen.client;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.spearmen.EmpireSpearmen;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireSpearmenArmorModel extends GeoModel<EmpireSpearmen> {

    @Override
    public ResourceLocation getModelResource(EmpireSpearmen animatable) {
        return OldWorldFantasy.res("geo/item/armor/empire_soldier_armor_1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireSpearmen animatable) {
        return OldWorldFantasy.res("textures/item/armor/imperial_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireSpearmen animatable) {
        return null;
    }
}
