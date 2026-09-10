package net.jaeger.oldworldfantasy.world.inventory.saddle;

import net.jaeger.oldworldfantasy.entity.mobs.monsters.griffons.AbstractGriffon;
import net.jaeger.oldworldfantasy.world.inventory.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Objects;

public class AbstractGriffonMenu  extends AbstractContainerMenu {

    private final int griffonId;
    private final Container container;
    private final AbstractGriffon abstractGriffon;
    private static final int SLOT_GRIFFON_INVENTORY_START = 1;

    public AbstractGriffonMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, playerInventory, (AbstractGriffon) Objects.requireNonNull(playerInventory.player.level().getEntity(buf.readInt())), null);
    }

    public AbstractGriffonMenu(int containerId, Inventory inventory, AbstractGriffon abstractGriffon, Container container) {
        super(ModMenus.GRIFFON.get(), containerId);
        this.container = container != null ? container : abstractGriffon.inventory;
        this.abstractGriffon = abstractGriffon;
        this.container.startOpen(inventory.player);
        int columns = abstractGriffon.getInventoryColumns();
        this.griffonId = abstractGriffon.getId();

        this.addSlot(new Slot(this.container, 0, 8, 18) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(Items.SADDLE) && !this.hasItem() && abstractGriffon.isSaddleable();
            }

            @Override
            public boolean isActive() {
                return abstractGriffon.isSaddleable();
            }
        });

        if (columns > 0) {
            for (int m = 0; m < 3; m++) {
                for (int n = 0; n < columns; n++) {
                    this.addSlot(new Slot(this.container, 1 + n + m * columns, 80 + n * 18, 18 + m * 18));
                }
            }
        }
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 9; n++) {
                this.addSlot(new Slot(inventory, n + m * 9 + 9, 8 + n * 18, 84));
            }
        }
        for (int m = 0; m < 9; m++) {
            this.addSlot(new Slot(inventory, m, 8 + m * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return !this.abstractGriffon.hasInventoryChanged(this.container)
                && this.container.stillValid(player)
                && this.container.stillValid(player)
                && this.abstractGriffon.isAlive()
                && player.canInteractWithEntity(this.abstractGriffon, 4.0);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(i);
        if (slot != null && slot.hasItem()) {
            ItemStack itemStack2 = slot.getItem();
            itemStack = itemStack2.copy();
            int j = this.container.getContainerSize() + 1;
            if (i < j) {
                if (!this.moveItemStackTo(itemStack2, j, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(1).mayPlace(itemStack2) && !this.getSlot(1).hasItem()) {
                if (!this.moveItemStackTo(itemStack2, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(0).mayPlace(itemStack2)) {
                if (!this.moveItemStackTo(itemStack2, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (j <= 1 || !this.moveItemStackTo(itemStack2, 2, j, false)) {
                int k = j;
                int l = k + 27;
                int m = l;
                int n = m + 9;
                if (i >= m && i < n) {
                    if (!this.moveItemStackTo(itemStack2, k, l, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (i >= k && i < l) {
                    if (!this.moveItemStackTo(itemStack2, m, n, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemStack2, m, l, false)) {
                    return ItemStack.EMPTY;
                }

                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }

    public int getGriffonId() {
        return this.griffonId;
    }

    public AbstractGriffon getGriffon() {
        return this.abstractGriffon;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.container.stopOpen(player);
    }
}
