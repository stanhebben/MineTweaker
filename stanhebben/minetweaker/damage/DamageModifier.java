/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.damage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

/**
 *
 * @author Stanneke
 */
public class DamageModifier {
	private static final Method damageArmor;
	
	static {
		Method _damageArmor = null;
		try {
			_damageArmor = EntityLivingBase.class.getMethod("damageArmor", int.class);
			_damageArmor.setAccessible(true);
		} catch (NoSuchMethodException ex) {}
		damageArmor = _damageArmor;
	}
	
	private String damageType;
	private float armorMultiplier; // multiplier determining the effect of armor on this type of damage (0 = stops nothing, 1 = stops like usual)
	private float armorDamageMultiplier; // multiplier determinng the amount of damage the armor itself receiver (0 = no damage, 1 = full damage)
	
	public DamageModifier(String damageType, float armorMultiplier, float armorDamageMultiplier) {
		this.damageType = damageType;
		this.armorMultiplier = armorMultiplier;
		this.armorDamageMultiplier = armorDamageMultiplier;
	}
	
	public String getDamageType() {
		return damageType;
	}
	
	public float applyArmorDamage(EntityLivingBase entity, DamageSource source, float damage) {
		int i = 25 - (int) (entity.getTotalArmorValue() * getArmorMultiplier());
		float f1 = damage * (float) i;
		try {
			damageArmor.invoke(entity, damage * getArmorDamageMultiplier());
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		}
		damage = f1 / 25.0F;

		return damage;
	}
	
	public float getArmorMultiplier() {
		return armorMultiplier;
	}
	
	public float getArmorDamageMultiplier() {
		return armorDamageMultiplier;
	}
}
