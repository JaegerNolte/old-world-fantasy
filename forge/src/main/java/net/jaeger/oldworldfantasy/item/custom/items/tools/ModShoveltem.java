package net.jaeger.oldworldfantasy.item.custom.items.tools;

import net.jaeger.oldworldfantasy.item.ModItemTier;
import net.jaeger.oldworldfantasy.item.custom.items.weapons.WeaponType;
import net.jaeger.oldworldfantasy.util.CombatUtil;
import net.minecraft.world.item.HoeItem;

import static net.jaeger.oldworldfantasy.item.custom.items.weapons.ModWeaponItem.createDefaultAttributeModifiersBuilder;

public class ModShoveltem extends HoeItem {

    public final WeaponType type;
    protected final float attackDamage;
    protected final float attackSpeed;

    public ModShoveltem(ModItemTier material, WeaponType type) {
        super(material, new Properties().stacksTo(1).durability(type.getDurability(material)).attributes(createDefaultAttributeModifiersBuilder(material, type).build()));
        this.type = type;

        this.attackDamage = CombatUtil.getBaseAttackDamage(material, type);
        this.attackSpeed = CombatUtil.getBaseAttackSpeed(material, type);
    }
}
