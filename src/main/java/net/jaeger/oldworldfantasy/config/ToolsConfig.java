package net.jaeger.oldworldfantasy.config;

import net.jaeger.oldworldfantasy.item.custom.items.weapons.WeaponType;

public class ToolsConfig {

    public static final WeaponType BASE_AXE = WeaponType.of(2.7f, 1.1f, 0.0f, 0.2f, 0.0f, 12);
    public static final WeaponType BASE_PICKAXE = WeaponType.of(2.7f, 0.8f, 0.0f, 0.2f, 0.0f, 35);
    public static final WeaponType BASE_HOE = WeaponType.of(2.7f, 1.1f, 0.0f, 0.2f, 0.0f, 0);
    public static final WeaponType BASE_SHOVEL = WeaponType.of(2.7f, 1.1f, 0.0f, 0.2f, 0.0f, 0);

    private ToolsConfig() {

    }
}
