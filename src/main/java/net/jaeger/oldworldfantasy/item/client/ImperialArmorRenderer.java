package net.jaeger.oldworldfantasy.item.client;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.item.custom.items.armor.ImperialArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ImperialArmorRenderer extends GeoArmorRenderer<ImperialArmorItem> {
    public ImperialArmorRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(OldWorldFantasyMod.MOD_ID, "armor/imperial_armor")));
    }
}
