package net.jaeger.oldworldfantasy.entity;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.custom.gor.GorEntity;
import net.jaeger.oldworldfantasy.entity.custom.nurgling.NurglingEntity;
import net.jaeger.oldworldfantasy.entity.custom.ungor.UngorEntity;
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
            ENTITY_TYPES.register("nurgling", () -> EntityType.Builder.of(NurglingEntity::new, MobCategory.MONSTER)
                    .sized(.5f, 1f).build("nurgling"));

    public static final RegistryObject<EntityType<UngorEntity>> UNGOR =
            ENTITY_TYPES.register("ungor", () -> EntityType.Builder.of(UngorEntity::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("ungor"));

    public static final RegistryObject<EntityType<GorEntity>> GOR =
            ENTITY_TYPES.register("gor", () -> EntityType.Builder.of(GorEntity::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("gor"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
