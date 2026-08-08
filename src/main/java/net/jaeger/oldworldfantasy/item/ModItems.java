package net.jaeger.oldworldfantasy.item;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.entity.ModEntities;
import net.jaeger.oldworldfantasy.item.custom.FuelItem;
import net.jaeger.oldworldfantasy.item.custom.GunItem;
import net.jaeger.oldworldfantasy.item.custom.LongswordItem;
import net.jaeger.oldworldfantasy.item.custom.PolearmItem;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OldWorldFantasyMod.MOD_ID);

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


    public static final RegistryObject<Item> RUNE_HARDENED_IRON_SWORD = ITEMS.register("rune_hardened_iron_sword",
            () -> new LongswordItem(ModToolTiers.RUNE_HARDENED_IRON, new Item.Properties()
                    .attributes(LongswordItem.createAttributes(ModToolTiers.RUNE_HARDENED_IRON, 4, -3.2f, 1.0d))));

    public static final RegistryObject<Item> RUNE_HARDENED_IRON_PICKAXE = ITEMS.register("rune_hardened_iron_pickaxe",
            () -> new PickaxeItem(ModToolTiers.RUNE_HARDENED_IRON, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.RUNE_HARDENED_IRON, 1, -2.8f))));

    public static final RegistryObject<Item> RUNE_HARDENED_IRON_AXE = ITEMS.register("rune_hardened_iron_axe",
            () -> new AxeItem(ModToolTiers.RUNE_HARDENED_IRON, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.RUNE_HARDENED_IRON, 6, -3.2f))));

    public static final RegistryObject<Item> RUNE_HARDENED_IRON_SHOVEL = ITEMS.register("rune_hardened_iron_shovel",
            () -> new ShovelItem(ModToolTiers.RUNE_HARDENED_IRON, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.RUNE_HARDENED_IRON, 1.5f, -3.0f))));

    public static final RegistryObject<Item> RUNE_HARDENED_IRON_HOE = ITEMS.register("rune_hardened_iron_hoe",
            () -> new HoeItem(ModToolTiers.RUNE_HARDENED_IRON, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.RUNE_HARDENED_IRON, 0, -3.0f))));

    public static final RegistryObject<Item> IMPERIAL_SWORD = ITEMS.register("imperial_sword",
            () -> new SwordItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.STEEL, 3, -2.0f))));

    public static final RegistryObject<Item> IMPERIAL_GREATSWORD = ITEMS.register("imperial_greatsword",
            () -> new LongswordItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(LongswordItem.createAttributes(ModToolTiers.STEEL, 4, -3.5f, 1.0d))));

    public static final RegistryObject<Item> IMPERIAL_PIKE = ITEMS.register("imperial_pike",
            () -> new PolearmItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(PolearmItem.createAttributes(ModToolTiers.STEEL, 3, -3.2f, 2.5d))));

    public static final RegistryObject<Item> IMPERIAL_HALBERD = ITEMS.register("imperial_halberd",
            () -> new PolearmItem(ModToolTiers.STEEL, new Item.Properties()
                    .attributes(PolearmItem.createAttributes(ModToolTiers.STEEL, 4, -3.5f, 2.5d))));


    public static final RegistryObject<Item> BLUNDERBUSS = ITEMS.register("blunderbuss",
            () -> new GunItem(new Item.Properties().durability(500)));


    public static final RegistryObject<Item> RED_WINE = ITEMS.register("red_wine",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RED_WINE)));


    public static final RegistryObject<Item> NURGLING_SPAWN_EGG = ITEMS.register("nurgling_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.NURGLING, 0x68774c, 0x9ea94b, new Item.Properties()));

    public static final RegistryObject<Item> UNGOR_SPAWN_EGG = ITEMS.register("ungor_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.UNGOR, 0x361311, 0x5D0009, new Item.Properties()));

    public static final RegistryObject<Item> GOR_SPAWN_EGG = ITEMS.register("gor_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.GOR, 0x361311, 0x5D0009, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
