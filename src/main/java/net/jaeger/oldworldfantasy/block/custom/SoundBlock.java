package net.jaeger.oldworldfantasy.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SoundBlock extends Block {

    public SoundBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {

        if (!pLevel.isClientSide) {
            if (pPlayer.isCrouching()) {
                pLevel.playSound(null, pPos, SoundEvents.NOTE_BLOCK_BANJO.get(), SoundSource.BLOCKS, 1f, 1f);
                return InteractionResult.SUCCESS;
            } else {
                pLevel.playSound(null, pPos, SoundEvents.NOTE_BLOCK_COW_BELL.get(), SoundSource.BLOCKS, 1f, 1f);
                return InteractionResult.CONSUME;
            }
        }
        return super.useWithoutItem(pState, pLevel, pPos, pPlayer, pHitResult);
    }
}
