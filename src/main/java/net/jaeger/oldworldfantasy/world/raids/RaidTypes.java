package net.jaeger.oldworldfantasy.world.raids;

import net.jaeger.oldworldfantasy.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

import javax.annotation.Nullable;

public enum RaidTypes {

    BEASTMEN("beastmen", ModEffects.BEASTMEN_OMEN.getHolder().get()),
    GREENSKINS("greenskins", ModEffects.GREENSKIN_OMEN.getHolder().get());
//    EMPIRE("empire",ModEffects.BEASTMEN_OMEN.getHolder().get());

    private final String type;
    private final Holder<MobEffect> omen;

    RaidTypes(String type, Holder<MobEffect> omen) {
        this.type = type;
        this.omen = omen;
    }

    public String getType() {
        return this.type;
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
}
