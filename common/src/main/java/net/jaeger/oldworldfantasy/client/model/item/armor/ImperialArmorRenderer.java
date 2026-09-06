package net.jaeger.oldworldfantasy.client.model.item.armor;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.item.items.armor.ImperialArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ImperialArmorRenderer extends GeoArmorRenderer<ImperialArmorItem> {
    public ImperialArmorRenderer() {
        super(new DefaultedItemGeoModel<>(OldWorldFantasy.res("armor/imperial_armor")));
    }
}
