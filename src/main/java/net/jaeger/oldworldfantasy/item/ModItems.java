package net.jaeger.oldworldfantasy.item;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.item.custom.FuelItem;
import net.minecraft.world.item.*;
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

    public static final RegistryObject<Item> RUNE_HARDEN_IRON_INGOT = ITEMS.register("rune_hardened_iron_ingot",
        () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ARCANE_COAL = ITEMS.register("arcane_coal",
            () -> new FuelItem(new Item.Properties(), 1800));


    public static final RegistryObject<Item> RUNE_HARDENED_IRON_SWORD = ITEMS.register("rune_hardened_iron_sword",
            () -> new SwordItem(ModToolTiers.RUNE_HARDENED_IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.RUNE_HARDENED_IRON, 3, -2.4f))));

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


    public static final RegistryObject<Item> RED_WINE = ITEMS.register("red_wine",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RED_WINE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
