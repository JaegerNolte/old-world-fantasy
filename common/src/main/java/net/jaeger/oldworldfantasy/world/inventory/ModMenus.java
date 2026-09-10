package net.jaeger.oldworldfantasy.world.inventory;

import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.world.inventory.merchant.ModMerchantMenu;
import net.jaeger.oldworldfantasy.world.inventory.saddle.AbstractGriffonMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;


public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(OldWorldFantasy.MOD_ID, Registries.MENU);

    public static final RegistrySupplier<MenuType<ModMerchantMenu>> MERCHANT =
            MENUS.register("merchant", () ->
                    new MenuType<>(ModMerchantMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static final RegistrySupplier<MenuType<AbstractGriffonMenu>> GRIFFON =
            MENUS.register("griffon", () ->
                    MenuRegistry.ofExtended(AbstractGriffonMenu::new));

    public static void init() {
        MENUS.register();
    }
}
