package net.jaeger.oldworldfantasy.item.custom.items.weapons;

import net.jaeger.oldworldfantasy.item.ModItemTier;

public class WeaponType {

    private float baseAttackDamage;
    private float baseAttackSpeed;
    private float bonusAttackReach;
    private float sizeFactor;
    private float weight;
    private int armorPiercing;
    private int twoHanded;
    private float maxBlockDamage;
    private boolean canBlock;
    private boolean isHalberd;

    public WeaponType() {

    }

    public static WeaponType of(float baseAttackDamage, float baseAttackSpeed, float bonusAttackReach, float sizeFactor, float weight, int armorPiercing,
                                int twoHanded, float maxBlockDamage, boolean canBlock, boolean isHalberd) {
        WeaponType type = new WeaponType();
        type.baseAttackDamage = baseAttackDamage;
        type.baseAttackSpeed = baseAttackSpeed;
        type.bonusAttackReach = bonusAttackReach;
        type.sizeFactor = sizeFactor;
        type.weight = weight;
        type.armorPiercing = armorPiercing;
        type.twoHanded = twoHanded;
        type.maxBlockDamage = maxBlockDamage;
        type.canBlock = canBlock;
        type.isHalberd = isHalberd;
        return type;
    }

    public static WeaponType of(float baseAttackDamage, float baseAttackSpeed, float bonusAttackReach, float sizeFactor, float weight, int armorPiercing) {
        return of(baseAttackDamage, baseAttackSpeed, bonusAttackReach, sizeFactor, weight, armorPiercing, 0, 0.0f, false, false);
    }

    public float getAttackSpeed(ModItemTier material) {
        return -material.getDensity() * this.getSizeFactor() + this.getBaseAttackSpeed();
    }

    public float getBaseAttackDamage() {
        return this.baseAttackDamage;
    }

    public float getBaseAttackSpeed() {
        return this.baseAttackSpeed;
    }

    public float getSizeFactor() {
        return this.sizeFactor;
    }

    public float getWeight() {
        return this.weight;
    }

    public int getArmorPiercing() {
        return this.armorPiercing;
    }

    public float getBonusAttackReach() {
        return this.bonusAttackReach;
    }

    public int getTwoHanded() {
        return this.twoHanded;
    }

    public float getMaxBlockDamage() {
        return this.maxBlockDamage;
    }

    public boolean canBlock() {
        return this.canBlock;
    }

    public boolean isHalberd() {
        return this.isHalberd;
    }

    public int getDurability(ModItemTier material) {
        return (int) (material.getUses() * (1.0f + this.getSizeFactor() * 5.0f));
    }
}
