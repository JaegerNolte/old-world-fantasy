package net.jaeger.oldworldfantasy.component;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;


public class ModDataComponents {

    public static final DeferredRegister<DataComponentType<?>> COMPONENT_TYPES = DeferredRegister.create(OldWorldFantasy.MOD_ID, Registries.DATA_COMPONENT_TYPE);

    public static final RegistrySupplier<DataComponentType<Integer>> TWO_HANDED_PENALTY = COMPONENT_TYPES.register("two_handed_penalty",
            () -> DataComponentType.<Integer>builder().persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT).build());

    public static void init() {
        COMPONENT_TYPES.register();
    }
}
