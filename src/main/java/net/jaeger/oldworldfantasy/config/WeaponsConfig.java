package net.jaeger.oldworldfantasy.config;

import net.jaeger.oldworldfantasy.item.custom.items.weapons.WeaponType;

import java.util.LinkedHashMap;
import java.util.Map;

public class WeaponsConfig {

    private Map<String, WeaponType> meleeWeapons = new LinkedHashMap<>() {{
        put("imperial_sword", WeaponType.of(2.7f, 1.8f, 0.0f, 0.2f, 0.0f));
        put("imperial_greatsword", WeaponType.of(6.2f, 1.4f, 0.5f, 0.3f, 4.0f));
        put("imperial_pike", WeaponType.of(2.2f, 1.2f, 2.5f, 0.2f, 0.2f));
        put("imperial_halberd", WeaponType.of(7.5f, 1.1f, 1.0f, 0.3f, 0.4f));

        put("rune_hardened_axe", WeaponType.of(7.1f, 1.3f, 0.0f, 0.3f, 0.3f));
        put("rune_hardened_sword", WeaponType.of(6.4f, 1.8f, 0.5f, 0.2f, 0.2f));

        put("choppa_sword", WeaponType.of(6.4f, 1.6f, 0.0f, 0.2f, 0.3f));
        put("choppa_axe", WeaponType.of(5.4f, 1.2f, 0.0f, 0.3f, 0.4f));
    }};

    public WeaponType getMelee(String name) {
        return meleeWeapons.get(name);
    }
}
