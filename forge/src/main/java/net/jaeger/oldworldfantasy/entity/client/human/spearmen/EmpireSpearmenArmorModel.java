package net.jaeger.oldworldfantasy.entity.client.human.spearmen;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.spearmen.EmpireSpearmen;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireSpearmenArmorModel extends GeoModel<EmpireSpearmen> {

    @Override
    public ResourceLocation getModelResource(EmpireSpearmen animatable) {
        return OldWorldFantasyMod.res("geo/item/armor/empire_soldier_armor_1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireSpearmen animatable) {
        return OldWorldFantasyMod.res("textures/item/armor/imperial_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireSpearmen animatable) {
        return null;
    }
}
