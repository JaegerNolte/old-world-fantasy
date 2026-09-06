package net.jaeger.oldworldfantasy.item.items.misc;

import net.minecraft.world.item.Item;


public class FuelItem extends Item {
    private int burnTime = 0;

    public FuelItem(Properties properties, int burnTime) {
        super(properties);
        this.burnTime = burnTime;
    }
}