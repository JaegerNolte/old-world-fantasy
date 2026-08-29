package net.jaeger.oldworldfantasy.effect;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.effect.omens.BeastmenOmen;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, OldWorldFantasyMod.MOD_ID);

    public static final RegistryObject<MobEffect> BEASTMEN_OMEN = MOB_EFFECTS.register("beastmen_omen",
            () -> new BeastmenOmen(MobEffectCategory.NEUTRAL, 0x36ebab));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
