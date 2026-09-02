package net.jaeger.oldworldfantasy.item.custom.items.weapons;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.component.ModDataComponents;
import net.jaeger.oldworldfantasy.item.ModItemTier;
import net.jaeger.oldworldfantasy.util.CombatUtil;
import net.jaeger.oldworldfantasy.util.ModDamageSources;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;

import java.util.List;

public class ModWeaponItem extends SwordItem {

    private static final ResourceLocation BASE_ATTACK_RANGE_ID = ResourceLocation.fromNamespaceAndPath(OldWorldFantasyMod.MOD_ID, "attack_range");

    private final ItemAttributeModifiers defaultModifiers;
    private final ItemAttributeModifiers decreasedModifiers;

    public final WeaponType type;
    protected final float attackDamage;
    private boolean blockingPriority = false;

    public ModWeaponItem(ModItemTier material, WeaponType type) {

        super(material, new Properties().stacksTo(1).durability(type.getDurability(material)).attributes(createDefaultAttributeModifiersBuilder(material, type).build()));
        this.type = type;
        this.attackDamage = CombatUtil.getBaseAttackDamage(material, type);

        this.defaultModifiers = createDefaultAttributeModifiersBuilder(material, type).build();
        this.decreasedModifiers = createDecreasedAttributeModifiersBuilder(material, type).build();
    }

    public static ItemAttributeModifiers.Builder createDefaultAttributeModifiersBuilder(ModItemTier material, WeaponType type) {
        return createAttributeModifiersBuilder(CombatUtil.getBaseAttackDamage(material, type), CombatUtil.getBaseAttackSpeed(material, type), type.getBonusAttackReach());
    }

    public static ItemAttributeModifiers.Builder createDecreasedAttributeModifiersBuilder(ModItemTier material, WeaponType type) {
        return createAttributeModifiersBuilder(CombatUtil.getDecreasedAttackDamage(material, type), CombatUtil.getDecreasedAttackSpeed(material, type), type.getBonusAttackReach());
    }

    public static ItemAttributeModifiers.Builder createAttributeModifiersBuilder(float damage, float speed, float reach) {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        builder.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, damage, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        builder.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, speed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        builder.add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(BASE_ATTACK_RANGE_ID, reach, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        return builder;
    }

    public boolean onAttackClickEntity(ItemStack stack, Player player, Entity entity) {
        return true;
    }

    public ItemAttributeModifiers getAttributeModifiers(ItemStack stack) {
        return this.hasTwoHandedPenalty(stack) ? this.decreasedModifiers : this.defaultModifiers;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean selected) {
        if (entity instanceof LivingEntity livingentity) {
            boolean penalty = this.type.getTwoHanded() > 0 && !livingentity.getOffhandItem().getItem().equals(Items.AIR);
            if (this.hasTwoHandedPenalty(stack) != penalty) {
                stack.set(ModDataComponents.TWO_HANDED_PENALTY.get(), penalty ? this.type.getTwoHanded() : 0);
                stack.set(DataComponents.ATTRIBUTE_MODIFIERS, this.getAttributeModifiers(stack));
            }

            if (this.canBlock())
                this.blockingPriority = !(livingentity.getMainHandItem().getItem() instanceof ShieldItem) && !(livingentity.getOffhandItem().getItem() instanceof ShieldItem);
        }
        super.inventoryTick(stack, level, entity, i, selected);
    }

    public boolean onHurtEntity(DamageSource source, LivingEntity entity, float damage) {
        if (entity.level().isClientSide() || ModDamageSources.isAdditional(source) || !(source.getEntity() instanceof LivingEntity attacker))
            return true;

        float attackscale = source.getEntity() instanceof LivingEntity livingentity ? damage / this.getAttackDamage(livingentity.getMainHandItem()) : 1.0f;

        if (type.isHalberd() && entity.isPassenger() && entity.level().getRandom().nextInt(20) * attackscale >= 14)
            entity.stopRiding();

        boolean flag = false;
        if (!flag && this.type.getArmorPiercing() != 0 && entity.getArmorValue() > 0)
            flag = this.dealArmorPiercingDamage(source, attacker, entity, damage);

        postHurtEnemy(attacker.getWeaponItem(), attacker, entity);
        return flag;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag) {
        if (type.isHalberd()) {
            tooltip.add(Component.translatable("tooltip.oldworldfantasy.halberd.hurt").withStyle(ChatFormatting.BLUE));
        }
        if (type.getArmorPiercing() != 0) {
            tooltip.add(Component.translatable("tooltip.oldworldfantasy.armorpiercing", this.type.getArmorPiercing()).withStyle(ChatFormatting.BLUE));
        }
        if (type.getTwoHanded() == 1) {
            tooltip.add(Component.translatable("tooltip.oldworldfantasy.twohandedi").withStyle(ChatFormatting.BLUE));
        } else if (type.getTwoHanded() > 1) {
            tooltip.add(Component.translatable("tooltip.oldworldfantasy.twohandedii").withStyle(ChatFormatting.BLUE));
        }
        if (this.canBlock()) {
            tooltip.add(Component.translatable("tooltip.oldworldfantasy.maxdamageblock", this.getMaxBlockDamage()).withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.translatable("tooltip.oldworldfantasy.weight", this.getWeight()).withStyle(ChatFormatting.YELLOW));
        }
        if (this.hasTwoHandedPenalty(stack)) {
            tooltip.add(Component.translatable("tooltip.oldworldfantasy.twohandedpenalty_1").withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("tooltip.oldworldfantasy.twohandedpenalty_2").withStyle(ChatFormatting.RED));
        }
        super.appendHoverText(stack, tooltipContext, tooltip, flag);
    }

    public boolean hasTwoHandedPenalty(ItemStack stack) {
        Integer value = stack.get(ModDataComponents.TWO_HANDED_PENALTY.get());
        return value != null && value > 0;
    }

    public float getAttackDamage(ItemStack stack) {
        return (float) this.getAttributeModifiers(stack).modifiers().stream().filter(m -> m.modifier().id().equals(BASE_ATTACK_DAMAGE_ID)).findFirst().orElseThrow().modifier().amount();
    }

    public float getAttackReach(float baseReach) {
        return baseReach + getBonusAttackReach();
    }

    public float getBonusAttackReach() {
        return type.getBonusAttackReach();
    }

    public float getMaxBlockDamage() {
        return type.getMaxBlockDamage();
    }

    public float getWeight() {
        return type.getWeight();
    }

    public boolean canBlock(Player player) {
        return player.getAttackStrengthScale(0.0f) == 1.0f && this.canBlock();
    }

    public boolean canBlock() {
        return type.canBlock();
    }

    boolean haveBlocked(RandomSource rand, DamageSource source) {
        return source.isDirect() && rand.nextInt(18) > this.getWeight();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (canBlock(player) && blockingPriority)
        {
            ItemStack stack = player.getItemInHand(hand);
            player.startUsingItem(hand);

            return InteractionResultHolder.consume(stack);
        }

        return super.use(level, player, hand);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return this.canBlock() ? (int) (500 / this.getWeight()) : 0;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return (canBlock() && blockingPriority) ? UseAnim.BLOCK : super.getUseAnimation(stack);
    }

    public void onBlocked(ItemStack stack, float damage, LivingEntity entity, DamageSource source)
    {
        if (!this.canBlock() || ModDamageSources.isAdditional(source))
            return;

        Entity attacker = source.getEntity();
        float f = CombatUtil.getArmorPiercingFactor(attacker);

        if (source.is(DamageTypes.PLAYER_EXPLOSION) || source.is(DamageTypes.EXPLOSION)) {
            entity.hurt(ModDamageSources.additional(), damage);
        }
        else if (!haveBlocked(entity.getRandom(), source)) {
            entity.hurt(ModDamageSources.additional(), damage);
        }
        else if (damage > this.getMaxBlockDamage()) {
            f *= 1.5f;
            float damage1 = damage - getMaxBlockDamage();
            entity.hurt(ModDamageSources.additional(), damage1);
        }

        stack.hurtAndBreak((int) (f * damage), entity, EquipmentSlot.MAINHAND);
    }

    public boolean dealArmorPiercingDamage(DamageSource source, LivingEntity attacker, LivingEntity victim, float damage) {
        float afterabsorb = CombatUtil.getDamageAfterAbsorb(source, victim, damage);
        afterabsorb = Math.max(afterabsorb - victim.getAbsorptionAmount(), 0.0f);
        float pierced = Math.max(((float) type.getArmorPiercing()) / 100.0f * (damage - afterabsorb), 0.0f);
        victim.hurt(ModDamageSources.armorPiercing(attacker), damage + pierced);
        return true;
    }
}
