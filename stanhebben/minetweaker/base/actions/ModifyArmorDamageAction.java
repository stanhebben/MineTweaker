/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.base.actions;

import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.damage.DamageModifier;
import stanhebben.minetweaker.damage.DamageTweaker;

/**
 *
 * @author Stanneke
 */
public class ModifyArmorDamageAction implements IUndoableAction {
	private final DamageModifier oldModifier;
	private final DamageModifier newModifier;
	
	public ModifyArmorDamageAction(String type, float armorEffectiveness, float armorDamage) {
		oldModifier = DamageTweaker.modifiers == null ? null : DamageTweaker.modifiers.get(type);
		newModifier = new DamageModifier(type, armorEffectiveness, armorDamage);
	}
	
	public void apply() {
		DamageTweaker.registerModifier(newModifier);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		if (oldModifier == null) {
			DamageTweaker.modifiers.remove(newModifier.getDamageType());
		} else {
			DamageTweaker.registerModifier(oldModifier);
		}
	}

	public String describe() {
		return "Modifying " + newModifier.getDamageType() + " damage";
	}

	public String describeUndo() {
		return "Restoring " + newModifier.getDamageType() + " damage";
	}
}
