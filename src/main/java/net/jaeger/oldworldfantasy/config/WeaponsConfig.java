package net.jaeger.oldworldfantasy.config;

import net.jaeger.oldworldfantasy.item.custom.items.weapons.WeaponType;

import java.util.LinkedHashMap;
import java.util.Map;

public class WeaponsConfig {

    private Map<String, WeaponType> meleeWeapons = new LinkedHashMap<>() {{
        put("imperial_sword", WeaponType.of(2.00f, 1.60f, 0.00f, 0.0f, 0.6f, 10));
        put("choppa_sword", WeaponType.of(8.00f, 0.80f, 0.00f, 0.2f, 1.2f, 18));
        put("choppa_axe", WeaponType.of(10.00f, 0.60f, 0.00f, 0.3f, 1.4f, 24));
    }};

    public WeaponType getMelee(String name) {
        return meleeWeapons.get(name);
    }
}
