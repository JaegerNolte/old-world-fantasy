package net.jaeger.oldworldfantasy.event.entity.player;

import net.jaeger.oldworldfantasy.world.item.trading.ModMerchant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ModTradeWithMerchantEvent extends PlayerEvent {

    private final MerchantOffer offer;
    private final ModMerchant merchant;

    public ModTradeWithMerchantEvent(Player player, MerchantOffer offer, ModMerchant merchant) {
        super(player);
        this.offer = offer;
        this.merchant = merchant;
    }

    public MerchantOffer getMerchantOffer() {
        return this.offer;
    }

    public ModMerchant getMerchant() {
        return this.merchant;
    }
}
