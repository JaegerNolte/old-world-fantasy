package net.jaeger.oldworldfantasy.world.item.trading;

import net.jaeger.oldworldfantasy.networking.ModNetworking;
import net.jaeger.oldworldfantasy.networking.packet.MerchantOfferPacket;
import net.jaeger.oldworldfantasy.world.inventory.ModMerchantMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;

import javax.annotation.Nullable;
import java.util.OptionalInt;

public interface ModMerchant {

    void setTradingPlayer(@Nullable Player pTradingPlayer);

    @Nullable
    Player getTradingPlayer();

    MerchantOffers getOffers();

    void overrideOffers(MerchantOffers pOffers);

    void notifyTrade(MerchantOffer pOffer);

    void notifyTradeUpdated(ItemStack pStack);

    int getVillagerXp();

    void overrideXp(int pXp);

    boolean showProgressBar();

    SoundEvent getNotifyTradeSound();

    default boolean canRestock() {
        return false;
    }

    default void openTradingScreen(Player pPlayer, Component pDisplayName, int pLevel) {
        OptionalInt optionalint = pPlayer.openMenu(
                new SimpleMenuProvider((level, inventory, player) -> new ModMerchantMenu(level, inventory, this), pDisplayName)
        );
        if (optionalint.isPresent() && pPlayer instanceof ServerPlayer serverPlayer) {
            MerchantOffers merchantoffers = this.getOffers();
            if (!merchantoffers.isEmpty()) {
                ModNetworking.INSTANCE.send(
                        new MerchantOfferPacket(
                                optionalint.getAsInt(),
                                merchantoffers,
                                pLevel,
                                this.getVillagerXp(),
                                this.showProgressBar(),
                                this.canRestock()
                        ),
                        serverPlayer.connection.getConnection()
                );
            }
        }
    }

    boolean isClientSide();
}
