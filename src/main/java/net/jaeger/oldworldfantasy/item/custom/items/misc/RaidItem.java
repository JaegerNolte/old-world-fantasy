package net.jaeger.oldworldfantasy.item.custom.items.misc;

import net.jaeger.oldworldfantasy.sound.ModSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class RaidItem extends Item {

    private final Holder<MobEffect> effect;

    public RaidItem(Holder<MobEffect> effect) {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
        this.effect = effect;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        Holder<MobEffect> effects = this.effect;

        if (!level.isClientSide) {
            level.playSound(null, livingEntity.blockPosition(), ModSounds.CHAOS_HORN.get(), livingEntity.getSoundSource(),
                    1.2F, 0.8F);livingEntity.addEffect(new MobEffectInstance(effects, 120000, 1, false, false, true));
        }

        stack.consume(1, livingEntity);
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity livingEntity_) {
        return 32;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.TOOT_HORN;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return ItemUtils.startUsingInstantly(level, player, interactionHand);
    }
}
