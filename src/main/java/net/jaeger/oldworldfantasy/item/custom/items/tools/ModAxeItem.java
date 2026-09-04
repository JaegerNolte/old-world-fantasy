package net.jaeger.oldworldfantasy.item.custom.items.tools;

import net.jaeger.oldworldfantasy.item.ModItemTier;
import net.jaeger.oldworldfantasy.item.custom.items.weapons.WeaponType;
import net.jaeger.oldworldfantasy.util.CombatUtil;
import net.minecraft.world.item.AxeItem;

import static net.jaeger.oldworldfantasy.item.custom.items.weapons.ModWeaponItem.createDefaultAttributeModifiersBuilder;

public class ModAxeItem extends AxeItem {

    public final WeaponType type;
    protected final float attackDamage;
    protected final float attackSpeed;

    public ModAxeItem(ModItemTier material, WeaponType type) {
        super(material, new Properties().stacksTo(1).durability(type.getDurability(material)).attributes(createDefaultAttributeModifiersBuilder(material, type).build()));
        this.type = type;

        this.attackDamage = CombatUtil.getBaseAttackDamage(material, type);
        this.attackSpeed = CombatUtil.getBaseAttackSpeed(material, type);
    }
}