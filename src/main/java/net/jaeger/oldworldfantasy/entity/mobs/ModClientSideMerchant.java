package net.jaeger.oldworldfantasy.entity.mobs;

import net.jaeger.oldworldfantasy.world.item.trading.ModMerchant;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import javax.annotation.Nullable;

public class ModClientSideMerchant implements ModMerchant {

    private final Player source;
    private MerchantOffers offers = new MerchantOffers();
    private int xp;

    public ModClientSideMerchant(Player pSource) {
        this.source = pSource;
    }

    @Override
    public Player getTradingPlayer() {
        return this.source;
    }

    @Override
    public void setTradingPlayer(@Nullable Player pPlayer) {
    }

    @Override
    public MerchantOffers getOffers() {
        return this.offers;
    }

    @Override
    public void overrideOffers(MerchantOffers pOffers) {
        this.offers = pOffers;
    }

    @Override
    public void notifyTrade(MerchantOffer pOffer) {
        pOffer.increaseUses();
    }

    @Override
    public void notifyTradeUpdated(ItemStack pStack) {
    }

    @Override
    public boolean isClientSide() {
        return this.source.level().isClientSide;
    }

    @Override
    public int getVillagerXp() {
        return this.xp;
    }

    @Override
    public void overrideXp(int pXp) {
        this.xp = pXp;
    }

    @Override
    public boolean showProgressBar() {
        return true;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return SoundEvents.VILLAGER_YES;
    }
}
