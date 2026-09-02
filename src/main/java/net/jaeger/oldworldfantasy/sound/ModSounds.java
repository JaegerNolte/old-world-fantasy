package net.jaeger.oldworldfantasy.sound;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, OldWorldFantasyMod.MOD_ID);

    public static final RegistryObject<SoundEvent> BEASTMEN_ROAR = registerSoundEvent("beastmen_roar");
    public static final RegistryObject<SoundEvent> BEASTMEN_WALK = registerSoundEvent("beastmen_walk");
    public static final RegistryObject<SoundEvent> BEASTMEN_HURT = registerSoundEvent("beastmen_hurt");
    public static final RegistryObject<SoundEvent> BEASTMEN_DEATH = registerSoundEvent("beastmen_death");

    public static final RegistryObject<SoundEvent> HUMAN_AMBIENT = registerSoundEvent("human_ambient");
    public static final RegistryObject<SoundEvent> HUMAN_HURT = registerSoundEvent("human_hurt");
    public static final RegistryObject<SoundEvent> HUMAN_DEATH = registerSoundEvent("human_death");
    public static final RegistryObject<SoundEvent> HUMAN_ANGRY = registerSoundEvent("human_angry");
    public static final RegistryObject<SoundEvent> HUMAN_SATISFIED = registerSoundEvent("human_satisfied");

    public static final RegistryObject<SoundEvent> CHAOS_HORN = registerSoundEvent("chaos_horn");


    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUNDS_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(OldWorldFantasyMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS_EVENTS.register(eventBus);
    }
}
