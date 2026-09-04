package net.jaeger.oldworldfantasy.item.custom.items.shield;

import net.minecraft.world.item.Tier;

public class ShieldType {

    private int baseDurability;
    private float materialModifier;
    private float weight;
    private float maxBlockDamage;
    private boolean repairable = false;

    public ShieldType() {

    }

    public static ShieldType of(int baseDurability, float materialModifier, float weight, float maxBlockDamage, boolean repairable) {
        ShieldType shield = new ShieldType();
        shield.baseDurability = baseDurability;
        shield.materialModifier = materialModifier;
        shield.weight = weight;
        shield.maxBlockDamage = maxBlockDamage;
        shield.repairable = repairable;
        return shield;
    }

    public static ShieldType of(int baseDurability, float materialModifier, float weight, float maxBlockDamage) {
        return of(baseDurability, materialModifier, weight, maxBlockDamage, false);
    }

    public int getDurability(Tier material) {
        return (int) (this.baseDurability + this.materialModifier * material.getUses());
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
}
