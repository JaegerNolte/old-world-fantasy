package net.jaeger.oldworldfantasy.component;

import com.mojang.serialization.Codec;
import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModDataComponents {

    public static final DeferredRegister<DataComponentType<?>> COMPONENT_TYPES = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, OldWorldFantasyMod.MOD_ID);


    public static final RegistryObject<DataComponentType<Integer>> TWO_HANDED_PENALTY = COMPONENT_TYPES.register("two_handed_penalty", () -> DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build());


    public static void register(IEventBus eventBus) {
        COMPONENT_TYPES.register(eventBus);
    }
}
