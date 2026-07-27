package net.jaeger.oldworldfantasy.item.custom;

import net.jaeger.oldworldfantasy.util.ModTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class MetalDetectorItem extends Item {

    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if(!context.getLevel().isClientSide) {
            BlockPos positionClicked = context.getClickedPos();
            boolean foundBlock = false;
            for (int i = 0; positionClicked.getY() + 64 >= i; i++) {
                BlockState blockState = context.getLevel().getBlockState(positionClicked.below(i));
                if (isValuableBlock(blockState)) {
                    outputValuableCoordinates(positionClicked.below(i), player, blockState.getBlock());
                    foundBlock = true;
                    break;
                }
            }
            if (!foundBlock) {
                outputNoValuableFound(player);
            }
            context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), (ServerPlayer) player,
                    item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.oldworldfantasy.metal_detector.tooltip.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.oldworldfantasy.metal_detector.tooltip"));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

    private void outputNoValuableFound(Player player) {
        player.sendSystemMessage(Component.translatable("item.oldworldfantasy.metal_detector.no_valuables"));
    }

    // I18N converts block IDs to lang reference
    private void outputValuableCoordinates(BlockPos below, Player player, Block block) {

        player.sendSystemMessage(Component.literal("Valuable Found " + I18n.get(block.getDescriptionId())
        + " at (" + below.getX() + ", " + below.getY() + ", " + below.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState blockState) {

        return blockState.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }
}
