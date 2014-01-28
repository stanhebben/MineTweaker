/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.base.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.ModifyArmorDamageAction;

/**
 *
 * @author Stanneke
 */
public class ModifyArmorDamageFunction extends TweakerFunction {
	public static final ModifyArmorDamageFunction INSTANCE = new ModifyArmorDamageFunction();
	
	private ModifyArmorDamageFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2 || arguments.length > 3) throw new TweakerExecuteException("minetweaker.modifyArmorDamage requires 2-3 arguments");
		String damageType =
				notNull(arguments[0], "damage type cannot be null")
				.toBasicString();
		float armorEffectiveness =
				notNull(arguments[1], "armor effectiveness cannot be null")
				.toFloat("armor effectiveness must be a float").get();
		float armorDamage = arguments.length < 3 || arguments[3] == null ? armorEffectiveness :
				arguments[2].toFloat("armor damage must be a float").get();
		
		Tweaker.apply(new ModifyArmorDamageAction(damageType, armorEffectiveness, armorDamage));
		return null;
	}

	@Override
	public String toString() {
		return "minetweaker.modifyArmorDamage";
	}
}
