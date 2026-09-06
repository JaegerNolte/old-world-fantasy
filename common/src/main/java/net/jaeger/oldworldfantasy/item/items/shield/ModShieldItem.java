package net.jaeger.oldworldfantasy.item.items.shield;

import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jaeger.oldworldfantasy.OldWorldFantasy;
import net.jaeger.oldworldfantasy.item.ModItemTier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class ModShieldItem extends ShieldItem {

    private final ShieldType type;
    private Supplier<Ingredient> repairItem = () -> Ingredient.of(ItemTags.PLANKS);
    private final float maxBlockDamage;
    private final float weight;

    public ModShieldItem(ModItemTier material, ShieldType type) {
        super(new Properties().stacksTo(1).durability(type.getDurability(material)));
        this.type = type;
        this.maxBlockDamage = type.getMaxBlockDamage() + material.getAttackDamageBonus();
        this.weight = type.getWeight() + material.getAttackDamageBonus();

        if (type.isRepairable())
            this.repairItem = material::getRepairIngredient;
    }

    protected float getWeight() {
        return this.weight;
    }

    public float getMaxBlockDamage() {
        return this.maxBlockDamage;
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return super.getDescriptionId();
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return (int) (1000 * this.weight);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack stack2) {
        return this.repairItem.get().test(stack2);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, TooltipContext tooltipContext, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("tooltip.oldworldfantasy.maxdamageblock", this.getMaxBlockDamage()).withStyle(ChatFormatting.BLUE));
        list.add(Component.translatable("tooltip.oldworldfantasy.weight", this.getWeight()).withStyle(ChatFormatting.YELLOW));
        if (this.getWeight() >= 10)
            list.add(Component.translatable("tooltip.oldworldfantasy.overencumbered").withStyle(ChatFormatting.RED));
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean selected) {
        if (this.getWeight() >= 10 && entity instanceof LivingEntity livingentity) {
            if (livingentity.getOffhandItem() == itemStack || livingentity.getMainHandItem() == itemStack) {
                livingentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 35, 0, false, false, false));
            }
            super.inventoryTick(itemStack, level, entity, i, selected);
        }
    }

    @Environment(EnvType.CLIENT)
    public void registerModelProperty() {
        ItemPropertiesRegistry.register(this, OldWorldFantasy.res("blocking"), (stack, level, entity, i) ->
                entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
    }
}
