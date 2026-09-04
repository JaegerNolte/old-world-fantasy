package net.jaeger.oldworldfantasy.world.raids;

import net.jaeger.oldworldfantasy.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

import javax.annotation.Nullable;
import java.util.Arrays;

public enum RaidTypes {

    BEASTMEN("beastmen", ModEffects.BEASTMEN_OMEN.getHolder().get()),
    GREENSKINS("greenskins", ModEffects.GREENSKIN_OMEN.getHolder().get());
//    EMPIRE("empire",ModEffects.BEASTMEN_OMEN.getHolder().get());

    private final String name;
    private final Holder<MobEffect> omen;

    RaidTypes(String name, Holder<MobEffect> omen) {
        this.name = name;
        this.omen = omen;
    }

    public String getType() {
        return this.name;
    }

    public Holder<MobEffect> getOmen() {
        return this.omen;
    }

    @Nullable
    public static String getTypeByOmen(Holder<MobEffect> omen) {
        for (RaidTypes raidType : values()) {
            if (raidType.getOmen().equals(omen)) {
                return raidType.getType();
            }
        }

        return null;
    }

    public static RaidTypes getTag(String value) {
        return Arrays.stream(RaidTypes.values())
                .filter(types -> types.getType().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
    }
}
