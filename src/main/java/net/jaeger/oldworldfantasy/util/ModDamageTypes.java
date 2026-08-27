package net.jaeger.oldworldfantasy.util;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> ADDITIONAL = ResourceKey.create(Registries.DAMAGE_TYPE, OldWorldFantasyMod.res("additional"));
    public static final ResourceKey<DamageType> ENTITY_ADDITIONAL = ResourceKey.create(Registries.DAMAGE_TYPE, OldWorldFantasyMod.res("entity_additional"));
    public static final ResourceKey<DamageType> ARMOR_PIERCING = ResourceKey.create(Registries.DAMAGE_TYPE, OldWorldFantasyMod.res("armor_piercing"));
}
