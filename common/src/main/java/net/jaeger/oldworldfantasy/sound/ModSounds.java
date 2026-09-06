package net.jaeger.oldworldfantasy.sound;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;


public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS_EVENTS =
            DeferredRegister.create(OldWorldFantasy.MOD_ID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> BEASTMEN_ROAR = registerSoundEvent("beastmen_roar");
    public static final RegistrySupplier<SoundEvent> BEASTMEN_WALK = registerSoundEvent("beastmen_walk");
    public static final RegistrySupplier<SoundEvent> BEASTMEN_HURT = registerSoundEvent("beastmen_hurt");
    public static final RegistrySupplier<SoundEvent> BEASTMEN_DEATH = registerSoundEvent("beastmen_death");

    public static final RegistrySupplier<SoundEvent> HUMAN_AMBIENT = registerSoundEvent("human_ambient");
    public static final RegistrySupplier<SoundEvent> HUMAN_HURT = registerSoundEvent("human_hurt");
    public static final RegistrySupplier<SoundEvent> HUMAN_DEATH = registerSoundEvent("human_death");
    public static final RegistrySupplier<SoundEvent> HUMAN_ANGRY = registerSoundEvent("human_angry");
    public static final RegistrySupplier<SoundEvent> HUMAN_SATISFIED = registerSoundEvent("human_satisfied");

    public static final RegistrySupplier<SoundEvent> CHAOS_HORN = registerSoundEvent("chaos_horn");


    private static RegistrySupplier<SoundEvent> registerSoundEvent(String name) {
        return SOUNDS_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(OldWorldFantasy.MOD_ID, name)));
    }

    public static void init() {
        SOUNDS_EVENTS.register();
    }
}
