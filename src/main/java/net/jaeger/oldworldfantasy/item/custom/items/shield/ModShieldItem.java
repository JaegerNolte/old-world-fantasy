package net.jaeger.oldworldfantasy.item.custom.items.shield;

import net.jaeger.oldworldfantasy.client.render.tileentity.ModClientRenderer;
import net.jaeger.oldworldfantasy.item.ModItemTier;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModShieldItem extends ShieldItem {

    private final ShieldType type;
    private Supplier<Ingredient> repairItem = () -> Ingredient.of(ItemTags.PLANKS);
    private final float maxBlockDamage;
    private final float weight;

    public ModShieldItem(ModItemTier material, ShieldType type) {
        super(new Item.Properties().stacksTo(1).durability(type.getDurability(material)));
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
        return (int) (12000 * this.weight);
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
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean selected) {
        if (this.getWeight() >= 10 && entity instanceof LivingEntity livingentity && (livingentity.getOffhandItem() == stack || livingentity.getMainHandItem() == stack)) {
            livingentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0, false, false, false));
        }
        super.inventoryTick(stack, level, entity, i, selected);
    }

    public void registerModelProperty() {
        ItemProperties.register(this, ResourceLocation.withDefaultNamespace("blocking"), (itemStack, level, entity, useDur) ->
                entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F
        );
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ModClientRenderer.get();
            }
        });
    }
}
