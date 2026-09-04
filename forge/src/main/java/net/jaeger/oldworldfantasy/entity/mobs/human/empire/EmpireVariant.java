package net.jaeger.oldworldfantasy.entity.mobs.human.empire;

import com.google.common.collect.Maps;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public enum EmpireVariant {

    BLONDE(0),
    BROWN(1);

    private final int id;
    private final static EmpireVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
        comparingInt(EmpireVariant::getId)).toArray(EmpireVariant[]::new);

    EmpireVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static EmpireVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

    public static final Map<EmpireVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(EmpireVariant.class), map -> {
                map.put(EmpireVariant.BLONDE, OldWorldFantasyMod.res("textures/entity/empire/empire_soldier.png"));
                map.put(EmpireVariant.BROWN, OldWorldFantasyMod.res("textures/entity/empire/empire_soldier_1.png"));
            });
}
