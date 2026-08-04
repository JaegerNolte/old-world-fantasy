package net.jaeger.oldworldfantasy.entity;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.NurglingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, OldWorldFantasyMod.MOD_ID);


    public static final RegistryObject<EntityType<NurglingEntity>> NURGLING =
            ENTITY_TYPES.register("nurgling", () -> EntityType.Builder.of(NurglingEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.5f).build("nurgling"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
