package net.jaeger.oldworldfantasy.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties RED_WINE = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 200), 1f)
            .alwaysEdible().build();
}
