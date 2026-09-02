package net.jaeger.oldworldfantasy.world.inventory;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, OldWorldFantasyMod.MOD_ID);

    public static final RegistryObject<MenuType<ModMerchantMenu>> MERCHANT =
            MENUS.register("merchant", () ->
                    new MenuType<>(ModMerchantMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
