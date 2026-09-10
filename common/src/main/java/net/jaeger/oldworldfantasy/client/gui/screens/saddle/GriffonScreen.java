package net.jaeger.oldworldfantasy.client.gui.screens.saddle;

import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.entity.mobs.monsters.griffons.AbstractGriffon;
import net.jaeger.oldworldfantasy.world.inventory.saddle.AbstractGriffonMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GriffonScreen extends AbstractContainerScreen<AbstractGriffonMenu> {

    private static final ResourceLocation CHEST_SLOTS_SPRITE = OldWorldFantasy.res("container/griffon/chest_slots");
    private static final ResourceLocation SADDLE_SLOT_SPRITE = OldWorldFantasy.res("container/griffon/saddle_slot");
    private static final ResourceLocation GRIFFON_INVENTORY_LOCATION = OldWorldFantasy.res("textures/gui/container/griffon.png");
    private float xMouse;
    private float yMouse;
    private final AbstractGriffon griffon;
//    private final int inventoryColumns;

    public GriffonScreen(AbstractGriffonMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.griffon = menu.getGriffon();
//        inventoryColumns = 1;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        int k = (this.width - this.imageWidth) / 2;
        int l = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(GRIFFON_INVENTORY_LOCATION, k, l, 0, 0, this.imageWidth, this.imageHeight);
//        if (this.inventoryColumns > 0) {
//            guiGraphics.blitSprite(CHEST_SLOTS_SPRITE, 90, 54, 0, 0, k + 79, l + 17, this.inventoryColumns * 18, 54);
//        }
        if (this.griffon.isSaddleable()) {
            guiGraphics.blitSprite(SADDLE_SLOT_SPRITE, k + 7, l + 35 - 18, 18, 18);
        }
        InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics, k + 26, l + 18, k + 78, l + 70, 17, 0.25F, this.xMouse, this.yMouse, this.griffon);
    }

    @Override
    public void render(GuiGraphics arg, int i, int j, float f) {
        this.xMouse = i;
        this.yMouse = j;
        super.render(arg, i, j, f);
        this.renderTooltip(arg, i, j);
    }
}
