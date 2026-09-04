package net.jaeger.oldworldfantasy.util;

import net.jaeger.oldworldfantasy.item.ModItemTier;
import net.jaeger.oldworldfantasy.item.custom.items.weapons.WeaponType;

public class CombatUtil {

    public static float getBaseAttackDamage(ModItemTier material, WeaponType type) {
        return type.getBaseAttackDamage() + 1.6f * material.getAttackDamageBonus() / type.getBaseAttackSpeed();
    }

    public static float getBaseAttackSpeed(ModItemTier material, WeaponType type) {
        return type.getAttackSpeed(material) - 4;
    }
}
