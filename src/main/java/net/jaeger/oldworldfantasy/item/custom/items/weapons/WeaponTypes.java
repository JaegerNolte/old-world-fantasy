package net.jaeger.oldworldfantasy.item.custom.items.weapons;

import net.jaeger.oldworldfantasy.config.WeaponsConfig;

public class WeaponTypes {
    public static final WeaponsConfig WEAPONS_CONFIG = new WeaponsConfig();

    public static final WeaponType IMPERIAL_SWORD = WEAPONS_CONFIG.getMelee("imperial_sword");

    public static final WeaponType CHOPPA_SWORD = WEAPONS_CONFIG.getMelee("choppa_sword");
    public static final WeaponType CHOPPA_AXE = WEAPONS_CONFIG.getMelee("choppa_axe");
}
