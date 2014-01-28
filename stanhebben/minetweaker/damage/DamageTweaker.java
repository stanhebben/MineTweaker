/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.damage;

import java.util.HashMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

/**
 *
 * @author Stanneke
 */
public class DamageTweaker {
	public static HashMap<String, DamageModifier> modifiers = null;
	public static boolean logDamage = false;
	
	public static void registerModifier(DamageModifier modifier) {
		if (modifiers == null) modifiers = new HashMap<String, DamageModifier>();
		modifiers.put(modifier.getDamageType(), modifier);
	}
	
	public static void debugApplyArmorCalculations(EntityLivingBase entity, DamageSource source, float amount) {
		if (logDamage) {
			System.out.println("Damage: " + source.damageType + " - " + amount);
			System.out.println("  From: " + source.getEntity() == null ? "null" : source.getEntity().getEntityName());
			System.out.println("  To: " + entity.getEntityName());
			System.out.println("  Magic: " + source.isMagicDamage());
			System.out.println("  Unblockable: " + source.isUnblockable());
		}
	}
	
	public static DamageModifier getModifier(DamageSource source) {
		return modifiers == null ? null : modifiers.get(source.damageType);
	}
}
