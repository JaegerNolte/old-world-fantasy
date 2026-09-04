package net.jaeger.oldworldfantasy.client.model.item.armor;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.item.custom.items.armor.ImperialArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ImperialArmorRenderer extends GeoArmorRenderer<ImperialArmorItem> {
    public ImperialArmorRenderer() {
        super(new DefaultedItemGeoModel<>(OldWorldFantasyMod.res("armor/imperial_armor")));
    }
}
