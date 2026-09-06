package net.jaeger.oldworldfantasy.effect;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.effect.omens.BeastmenOmen;
import net.jaeger.oldworldfantasy.effect.omens.GreenskinOmen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;


public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(OldWorldFantasy.MOD_ID, Registries.MOB_EFFECT);

    public static final RegistrySupplier<MobEffect> BEASTMEN_OMEN = MOB_EFFECTS.register("beastmen_omen",
            () -> new BeastmenOmen(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static final RegistrySupplier<MobEffect> GREENSKIN_OMEN = MOB_EFFECTS.register("greenskin_omen",
            () -> new GreenskinOmen(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static void init() {
        MOB_EFFECTS.register();
    }
}
