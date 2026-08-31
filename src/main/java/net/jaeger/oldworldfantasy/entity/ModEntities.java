package net.jaeger.oldworldfantasy.entity;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.bestigor.Bestigor;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.gor.Gor;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.ungor.Ungor;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.wargor.Wargor;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.biguns.BigUns;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.goblin.Goblin;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.orc.Orc;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.warboss.OrcWarboss;
import net.jaeger.oldworldfantasy.entity.mobs.human.spearmen.EmpireSpearmen;
import net.jaeger.oldworldfantasy.entity.mobs.human.swordsmen.EmpireSwordsmen;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, OldWorldFantasyMod.MOD_ID);


    public static final RegistryObject<EntityType<Ungor>> UNGOR =
            ENTITY_TYPES.register("ungor", () -> EntityType.Builder.of(Ungor::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("ungor"));

    public static final RegistryObject<EntityType<Gor>> GOR =
            ENTITY_TYPES.register("gor", () -> EntityType.Builder.of(Gor::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("gor"));

    public static final RegistryObject<EntityType<Bestigor>> BESTIGOR =
            ENTITY_TYPES.register("bestigor", () -> EntityType.Builder.of(Bestigor::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("bestigor"));

    public static final RegistryObject<EntityType<Wargor>> WARGOR =
            ENTITY_TYPES.register("wargor", () -> EntityType.Builder.of(Wargor::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("wargor"));

    public static final RegistryObject<EntityType<Goblin>> GOBLIN =
            ENTITY_TYPES.register("goblin", () -> EntityType.Builder.of(Goblin::new, MobCategory.MONSTER)
                    .sized(0.5f, 1f).build("goblin"));

    public static final RegistryObject<EntityType<Orc>> ORC =
            ENTITY_TYPES.register("orc", () -> EntityType.Builder.of(Orc::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("orc"));

    public static final RegistryObject<EntityType<BigUns>> BIGUNS =
            ENTITY_TYPES.register("biguns", () -> EntityType.Builder.of(BigUns::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("biguns"));

    public static final RegistryObject<EntityType<OrcWarboss>> ORCWARBOSS =
            ENTITY_TYPES.register("orcwarboss", () -> EntityType.Builder.of(OrcWarboss::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("orcwarboss"));

    public static final RegistryObject<EntityType<EmpireSwordsmen>> EMPIRE_SWORDSMEN =
            ENTITY_TYPES.register("empire_swordsmen", () -> EntityType.Builder.of(EmpireSwordsmen::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("empire_swordsmen"));

    public static final RegistryObject<EntityType<EmpireSpearmen>> EMPIRE_SPEARMEN =
            ENTITY_TYPES.register("empire_spearmen", () -> EntityType.Builder.of(EmpireSpearmen::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("empire_spearmen"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
