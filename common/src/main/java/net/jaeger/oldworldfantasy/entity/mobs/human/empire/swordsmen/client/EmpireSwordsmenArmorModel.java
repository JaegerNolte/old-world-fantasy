package net.jaeger.oldworldfantasy.entity.mobs.human.empire.swordsmen.client;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.swordsmen.EmpireSwordsmen;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireSwordsmenArmorModel extends GeoModel<EmpireSwordsmen> {

    @Override
    public ResourceLocation getModelResource(EmpireSwordsmen animatable) {
        return OldWorldFantasy.res("geo/item/armor/empire_soldier_armor_1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireSwordsmen animatable) {
        return OldWorldFantasy.res("textures/item/armor/imperial_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireSwordsmen animatable) {
        return null;
    }
}
