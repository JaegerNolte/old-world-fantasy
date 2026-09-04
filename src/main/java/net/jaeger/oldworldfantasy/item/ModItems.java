package net.jaeger.oldworldfantasy.item;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.effect.ModEffects;
import net.jaeger.oldworldfantasy.entity.ModEntities;
import net.jaeger.oldworldfantasy.item.custom.items.armor.ImperialArmorItem;
import net.jaeger.oldworldfantasy.item.custom.items.misc.FuelItem;
import net.jaeger.oldworldfantasy.item.custom.items.misc.RaidItem;
import net.jaeger.oldworldfantasy.item.custom.items.shield.ModShieldItem;
import net.jaeger.oldworldfantasy.item.custom.items.tools.ModHoeItem;
import net.jaeger.oldworldfantasy.item.custom.items.tools.ModPickaxeItem;
import net.jaeger.oldworldfantasy.item.custom.items.tools.ModShoveltem;
import net.jaeger.oldworldfantasy.config.ToolsConfig;
import net.jaeger.oldworldfantasy.item.custom.items.weapons.ModWeaponItem;
import net.jaeger.oldworldfantasy.item.custom.items.weapons.WeaponTypes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.jaeger.oldworldfantasy.item.custom.items.shield.ShieldsConfig.SHIELDS_CONFIG;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OldWorldFantasyMod.MOD_ID);

    // Crafting
    public static final RegistryObject<Item> RAW_LEAD = ITEMS.register("raw_lead",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LEAD_NUGGET = ITEMS.register("lead_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUNE_HARDEN_IRON_INGOT = ITEMS.register("rune_hardened_iron_ingot",
        () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARCANE_COAL = ITEMS.register("arcane_coal",
            () -> new FuelItem(new Item.Properties(), 1800));

    public static final RegistryObject<Item> LEATHER_STRAP = ITEMS.register("leather_strap",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HILT = ITEMS.register("hilt",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> POLE = ITEMS.register("pole",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SCRAP = ITEMS.register("scrap",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TEEF = ITEMS.register("teef",
            () -> new Item(new Item.Properties()));

    // Summons
    public static final RegistryObject<Item> TAINTED_HORN = ITEMS.register("tainted_horn",
            () -> new RaidItem(ModEffects.BEASTMEN_OMEN.getHolder().get()));

    // Tools and Weapons
    public static final RegistryObject<Item> RUNE_HARDENED_IRON_SWORD = ITEMS.register("rune_hardened_iron_sword",
            () -> new ModWeaponItem(ModItemTier.RUNE_HARDENED_IRON, WeaponTypes.RUNE_HARDENED_SWORD));

    public static final RegistryObject<Item> RUNE_HARDENED_IRON_PICKAXE = ITEMS.register("rune_hardened_iron_pickaxe",
            () -> new ModPickaxeItem(ModItemTier.RUNE_HARDENED_IRON, ToolsConfig.BASE_PICKAXE));

    public static final RegistryObject<Item> RUNE_HARDENED_IRON_AXE = ITEMS.register("rune_hardened_iron_axe",
            () -> new ModWeaponItem(ModItemTier.RUNE_HARDENED_IRON, WeaponTypes.RUNE_HARDENED_AXE));

    public static final RegistryObject<Item> RUNE_HARDENED_IRON_SHOVEL = ITEMS.register("rune_hardened_iron_shovel",
            () -> new ModShoveltem(ModItemTier.RUNE_HARDENED_IRON, ToolsConfig.BASE_SHOVEL));

    public static final RegistryObject<Item> RUNE_HARDENED_IRON_HOE = ITEMS.register("rune_hardened_iron_hoe",
            () -> new ModHoeItem(ModItemTier.RUNE_HARDENED_IRON, ToolsConfig.BASE_HOE));

    public static final RegistryObject<Item> IMPERIAL_SWORD = ITEMS.register("imperial_sword",
            () -> new ModWeaponItem(ModItemTier.STEEL, WeaponTypes.IMPERIAL_SWORD));

    public static final RegistryObject<Item> IMPERIAL_GREATSWORD = ITEMS.register("imperial_greatsword",
            () -> new ModWeaponItem(ModItemTier.STEEL, WeaponTypes.IMPERIAL_GREATSWORD));

    public static final RegistryObject<Item> IMPERIAL_PIKE = ITEMS.register("imperial_pike",
            () -> new ModWeaponItem(ModItemTier.STEEL, WeaponTypes.IMPERIAL_PIKE));

    public static final RegistryObject<Item> IMPERIAL_HALBERD = ITEMS.register("imperial_halberd",
            () ->new ModWeaponItem(ModItemTier.STEEL, WeaponTypes.IMPERIAL_HALBERD));

    public static final RegistryObject<Item> CHOPPA_SWORD = ITEMS.register("choppa_sword",
            () -> new ModWeaponItem(ModItemTier.SCRAP, WeaponTypes.CHOPPA_SWORD));

    public static final RegistryObject<Item> CHOPPA_AXE = ITEMS.register("choppa_axe",
            () -> new ModWeaponItem(ModItemTier.SCRAP, WeaponTypes.CHOPPA_AXE));

    // Armor
    public static final RegistryObject<Item> IMPERIAL_HELMET = ITEMS.register("imperial_helmet",
            () -> new ImperialArmorItem(ModArmorMaterials.IMPERIAL_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(18))));

    public static final RegistryObject<Item> IMPERIAL_CHESTPLATE = ITEMS.register("imperial_chestplate",
            () -> new ImperialArmorItem(ModArmorMaterials.IMPERIAL_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(18))));

    public static final RegistryObject<Item> IMPERIAL_LEGGINGS = ITEMS.register("imperial_leggings",
            () -> new ImperialArmorItem(ModArmorMaterials.IMPERIAL_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(18))));

    public static final RegistryObject<Item> IMPERIAL_BOOTS = ITEMS.register("imperial_boots",
            () -> new ImperialArmorItem(ModArmorMaterials.IMPERIAL_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(18))));

    // Shields
    public static final RegistryObject<Item> IMPERIAL_SHIELD = ITEMS.register("imperial_shield",
            () -> new ModShieldItem(ModItemTier.STEEL, SHIELDS_CONFIG.get("imperial_shield")));

    // Foods
    public static final RegistryObject<Item> RED_WINE = ITEMS.register("red_wine",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RED_WINE)));

    // Spawn Items
    public static final RegistryObject<Item> UNGOR_SPAWN_EGG = ITEMS.register("ungor_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.UNGOR, 0x361311, 0x5D0009, new Item.Properties()));

    public static final RegistryObject<Item> GOR_SPAWN_EGG = ITEMS.register("gor_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.GOR, 0x361311, 0x5D0009, new Item.Properties()));

    public static final RegistryObject<Item> BESTIGOR_SPAWN_EGG = ITEMS.register("bestigor_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BESTIGOR, 0x361311, 0x5D0009, new Item.Properties()));

    public static final RegistryObject<Item> WARGOR_SPAWN_EGG = ITEMS.register("wargor_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.WARGOR, 0x361311, 0x5D0009, new Item.Properties()));

    public static final RegistryObject<Item> GOBLIN_SPAWN_EGG = ITEMS.register("goblin_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.GOBLIN, 0x286546, 0x37895f, new Item.Properties()));

    public static final RegistryObject<Item> ORC_SPAWN_EGG = ITEMS.register("orc_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ORC, 0x286546, 0x37895f, new Item.Properties()));

    public static final RegistryObject<Item> BIGUNS_SPAWN_EGG = ITEMS.register("biguns_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BIGUNS, 0x286546, 0x37895f, new Item.Properties()));

    public static final RegistryObject<Item> ORCWARBOSS_SPAWN_EGG = ITEMS.register("orcwarboss_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ORCWARBOSS, 0x286546, 0x37895f, new Item.Properties()));

    public static final RegistryObject<Item> EMPIRE_SWORDSMEN_SPAWN_EGG = ITEMS.register("empire_swordsmen_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.EMPIRE_SWORDSMEN, 0x8f7772, 0xffc1c2, new Item.Properties()));

    public static final RegistryObject<Item> EMPIRE_SPEARMEN_SPAWN_EGG = ITEMS.register("empire_spearmen_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.EMPIRE_SPEARMEN, 0x8f7772, 0xffc1c2, new Item.Properties()));

    public static final RegistryObject<Item> EMPIRE_CROSSBOWMEN_SPAWN_EGG = ITEMS.register("empire_crossbowmen_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.EMPIRE_CROSSBOWMEN, 0x8f7772, 0xffc1c2, new Item.Properties()));

    public static final RegistryObject<Item> EMPIRE_CAPTAIN_SPAWN_EGG = ITEMS.register("empire_captain_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.EMPIRE_CAPTAIN, 0x8f7772, 0xffc1c2, new Item.Properties()));

    public static final RegistryObject<Item> EMPIRE_ARCH_LECTOR_SPAWN_EGG = ITEMS.register("empire_arch_lector_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.EMPIRE_ARCH_LECTOR, 0x8f7772, 0xffc1c2, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
