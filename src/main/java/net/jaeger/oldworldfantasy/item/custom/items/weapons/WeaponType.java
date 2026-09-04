package net.jaeger.oldworldfantasy.item.custom.items.weapons;

import net.jaeger.oldworldfantasy.item.ModItemTier;

public class WeaponType {

    private float baseAttackDamage;
    private float baseAttackSpeed;
    private float bonusAttackReach;
    private float sizeFactor;
    private float weight;

    public WeaponType() {

    }

    public static WeaponType of(float baseAttackDamage, float baseAttackSpeed, float bonusAttackReach, float sizeFactor, float weight) {
        WeaponType type = new WeaponType();
        type.baseAttackDamage = baseAttackDamage;
        type.baseAttackSpeed = baseAttackSpeed;
        type.bonusAttackReach = bonusAttackReach;
        type.sizeFactor = sizeFactor;
        type.weight = weight;

        return type;
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

    public float getBonusAttackReach() {
        return this.bonusAttackReach;
    }

    public int getDurability(ModItemTier material) {
        return (int) (material.getUses() * (1.0f + this.getSizeFactor() * 5.0f));
    }
}
