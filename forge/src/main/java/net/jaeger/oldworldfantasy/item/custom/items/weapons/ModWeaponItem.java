package net.jaeger.oldworldfantasy.item.custom.items.weapons;

import net.jaeger.oldworldfantasy.OldWorldFantasyMod;
import net.jaeger.oldworldfantasy.item.ModItemTier;
import net.jaeger.oldworldfantasy.util.CombatUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class ModWeaponItem extends SwordItem {

    private static final ResourceLocation BASE_ATTACK_RANGE_ID = ResourceLocation.fromNamespaceAndPath(OldWorldFantasyMod.MOD_ID, "attack_range");
    private final ItemAttributeModifiers defaultModifiers;
    public final WeaponType type;
    protected final float attackDamage;

    public ModWeaponItem(ModItemTier material, WeaponType type) {
        super(material, new Properties().stacksTo(1).durability(type.getDurability(material)).attributes(createDefaultAttributeModifiersBuilder(material, type).build()));
        this.type = type;
        this.attackDamage = CombatUtil.getBaseAttackDamage(material, type);
        this.defaultModifiers = createDefaultAttributeModifiersBuilder(material, type).build();
    }

    public static ItemAttributeModifiers.Builder createDefaultAttributeModifiersBuilder(ModItemTier material, WeaponType type) {
        return createAttributeModifiersBuilder(CombatUtil.getBaseAttackDamage(material, type), CombatUtil.getBaseAttackSpeed(material, type), type.getBonusAttackReach());
    }

    public static ItemAttributeModifiers.Builder createAttributeModifiersBuilder(float damage, float speed, float reach) {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        builder.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, damage, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        builder.add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, speed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        builder.add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(BASE_ATTACK_RANGE_ID, reach, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        return builder;
    }

    public float getWeight() {
        return type.getWeight();
    }
}
