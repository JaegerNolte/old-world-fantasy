package net.jaeger.oldworldfantasy.item.custom.items.weapons;

import net.jaeger.oldworldfantasy.config.WeaponsConfig;

public class WeaponTypes {
    public static final WeaponsConfig WEAPONS_CONFIG = new WeaponsConfig();

    public static final WeaponType IMPERIAL_SWORD = WEAPONS_CONFIG.getMelee("imperial_sword");
    public static final WeaponType IMPERIAL_GREATSWORD = WEAPONS_CONFIG.getMelee("imperial_greatsword");
    public static final WeaponType IMPERIAL_PIKE = WEAPONS_CONFIG.getMelee("imperial_pike");
    public static final WeaponType IMPERIAL_HALBERD = WEAPONS_CONFIG.getMelee("imperial_halberd");

    public static final WeaponType RUNE_HARDENED_AXE = WEAPONS_CONFIG.getMelee("rune_hardened_axe");
    public static final WeaponType RUNE_HARDENED_SWORD = WEAPONS_CONFIG.getMelee("rune_hardened_sword");

    public static final WeaponType CHOPPA_SWORD = WEAPONS_CONFIG.getMelee("choppa_sword");
    public static final WeaponType CHOPPA_AXE = WEAPONS_CONFIG.getMelee("choppa_axe");
}
