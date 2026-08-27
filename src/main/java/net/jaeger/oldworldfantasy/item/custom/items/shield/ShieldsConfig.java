package net.jaeger.oldworldfantasy.item.custom.items.shield;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShieldsConfig {

    public static final ShieldsConfig SHIELDS_CONFIG = new ShieldsConfig();

    public Map<String, ShieldType> shields = new LinkedHashMap<>() {{
        put("imperial_shield", ShieldType.of(350, 0.8f, 4, 10, true, true));
    }};

    public ShieldType get(String name) {
        return this.shields.get(name);
    }
}
