package net.jaeger.oldworldfantasy.entity;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.bestigor.Bestigor;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.gor.Gor;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.ungor.Ungor;
import net.jaeger.oldworldfantasy.entity.mobs.beastmen.wargor.Wargor;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.biguns.BigUns;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.goblin.Goblin;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.orc.Orc;
import net.jaeger.oldworldfantasy.entity.mobs.greenskin.warboss.OrcWarboss;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.archlector.EmpireArchLector;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.captain.EmpireCaptain;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.crossbowmen.EmpireCrossbowmen;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.spearmen.EmpireSpearmen;
import net.jaeger.oldworldfantasy.entity.mobs.human.empire.swordsmen.EmpireSwordsmen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(OldWorldFantasy.MOD_ID, Registries.ENTITY_TYPE);


    public static final RegistrySupplier<EntityType<Ungor>> UNGOR =
            ENTITY_TYPES.register("ungor", () -> EntityType.Builder.of(Ungor::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("ungor"));

    public static final RegistrySupplier<EntityType<Gor>> GOR =
            ENTITY_TYPES.register("gor", () -> EntityType.Builder.of(Gor::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("gor"));

    public static final RegistrySupplier<EntityType<Bestigor>> BESTIGOR =
            ENTITY_TYPES.register("bestigor", () -> EntityType.Builder.of(Bestigor::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("bestigor"));

    public static final RegistrySupplier<EntityType<Wargor>> WARGOR =
            ENTITY_TYPES.register("wargor", () -> EntityType.Builder.of(Wargor::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("wargor"));

    public static final RegistrySupplier<EntityType<Goblin>> GOBLIN =
            ENTITY_TYPES.register("goblin", () -> EntityType.Builder.of(Goblin::new, MobCategory.MONSTER)
                    .sized(0.5f, 1f).build("goblin"));

    public static final RegistrySupplier<EntityType<Orc>> ORC =
            ENTITY_TYPES.register("orc", () -> EntityType.Builder.of(Orc::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("orc"));

    public static final RegistrySupplier<EntityType<BigUns>> BIGUNS =
            ENTITY_TYPES.register("biguns", () -> EntityType.Builder.of(BigUns::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("biguns"));

    public static final RegistrySupplier<EntityType<OrcWarboss>> ORCWARBOSS =
            ENTITY_TYPES.register("orcwarboss", () -> EntityType.Builder.of(OrcWarboss::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("orcwarboss"));

    public static final RegistrySupplier<EntityType<EmpireSwordsmen>> EMPIRE_SWORDSMEN =
            ENTITY_TYPES.register("empire_swordsmen", () -> EntityType.Builder.of(EmpireSwordsmen::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("empire_swordsmen"));

    public static final RegistrySupplier<EntityType<EmpireSpearmen>> EMPIRE_SPEARMEN =
            ENTITY_TYPES.register("empire_spearmen", () -> EntityType.Builder.of(EmpireSpearmen::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("empire_spearmen"));

    public static final RegistrySupplier<EntityType<EmpireCrossbowmen>> EMPIRE_CROSSBOWMEN =
            ENTITY_TYPES.register("empire_crossbowmen", () -> EntityType.Builder.of(EmpireCrossbowmen::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("empire_crossbowmen"));

    public static final RegistrySupplier<EntityType<EmpireCaptain>> EMPIRE_CAPTAIN =
            ENTITY_TYPES.register("empire_captain", () -> EntityType.Builder.of(EmpireCaptain::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("empire_captain"));

    public static final RegistrySupplier<EntityType<EmpireArchLector>> EMPIRE_ARCH_LECTOR =
            ENTITY_TYPES.register("empire_arch_lector", () -> EntityType.Builder.of(EmpireArchLector::new, MobCategory.MONSTER)
                    .sized(1f, 2f).build("empire_arch_lector"));


    public static void init() {
        ENTITY_TYPES.register();
    }
}
