package net.jaeger.oldworldfantasy.entity.client.human.archlector;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.archlector.EmpireArchLector;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EmpireArchLectorArmorModel extends GeoModel<EmpireArchLector> {

    @Override
    public ResourceLocation getModelResource(EmpireArchLector animatable) {
        return OldWorldFantasyMod.res("geo/item/armor/empire_archlector_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmpireArchLector animatable) {
        return OldWorldFantasyMod.res("textures/item/armor/archlector_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmpireArchLector animatable) {
        return null;
    }
}
