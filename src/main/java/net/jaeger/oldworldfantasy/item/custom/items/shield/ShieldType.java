package net.jaeger.oldworldfantasy.item.custom.items.shield;

import net.minecraft.world.item.Tier;

public class ShieldType {

    private int baseDurability;
    private float materialFactor;
    private float weight;
    private float maxBlockDamage;
    private boolean repairable = false;
    private boolean enabled = true;

    public ShieldType() {}

    public static ShieldType of(int baseDurability, float materialFactor, float weight, float maxBlockDamage, boolean enabled, boolean repairable) {
        ShieldType shield = new ShieldType();
        shield.baseDurability = baseDurability;
        shield.materialFactor = materialFactor;
        shield.weight = weight;
        shield.maxBlockDamage = maxBlockDamage;
        shield.enabled = enabled;
        shield.repairable = repairable;
        return shield;
    }

    public static ShieldType of(int baseDurability, float materialFactor, float weight, float maxBlockDamage) {
        return of(baseDurability, materialFactor, weight, maxBlockDamage, true, false);
    }

    public int getDurability(Tier material) {
        return (int) (this.baseDurability + this.materialFactor * material.getUses());
    }

    public int getBaseDurability() {
        return this.baseDurability;
    }

    public float getMaterialFactor() {
        return this.materialFactor;
    }

    public float getWeight() {
        return this.weight;
    }

    public float getMaxBlockDamage() {
        return this.maxBlockDamage;
    }

    public boolean isRepairable() {
        return this.repairable;
    }

    public boolean isDisabled() {
        return !this.enabled;
    }
}
