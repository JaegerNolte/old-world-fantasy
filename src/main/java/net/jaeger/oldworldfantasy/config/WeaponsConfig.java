package net.jaeger.oldworldfantasy.config;

import net.jaeger.oldworldfantasy.item.custom.items.weapons.WeaponType;

import java.util.LinkedHashMap;
import java.util.Map;

public class WeaponsConfig {

    private Map<String, WeaponType> meleeWeapons = new LinkedHashMap<>() {{
        put("imperial_sword", WeaponType.of(2.7f, 1.7f, 0.0f, 0.2f, 0.0f, 10));
        put("imperial_greatsword", WeaponType.of(6.2f, 1.12f, 0.5f, 0.3f, 4.0f, 18, 1, 0.8f, true, false));
        put("imperial_pike", WeaponType.of(2.2f, 1.0f, 2.5f, 0.2f, 0.2f, 12));
        put("imperial_halberd", WeaponType.of(7.5f, 0.9f, 1.0f, 0.3f, 0.4f, 18,1,  1.2f, true, true));

        put("rune_hardened_axe", WeaponType.of(7.1f, 1.00f, 0.0f, 0.3f, 0.3f, 24));
        put("rune_hardened_sword", WeaponType.of(6.4f, 1.12f, 0.5f, 0.2f, 0.2f, 18));

        put("choppa_sword", WeaponType.of(6.4f, 1.0f, 0.0f, 0.2f, 0.3f, 18));
        put("choppa_axe", WeaponType.of(5.4f, 0.9f, 0.0f, 0.3f, 0.4f, 24));
    }};

    public WeaponType getMelee(String name) {
        return meleeWeapons.get(name);
    }
}
