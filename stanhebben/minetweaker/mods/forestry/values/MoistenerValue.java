/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.functions.MoistenerAddFuelFunction;
import stanhebben.minetweaker.mods.forestry.functions.MoistenerAddRecipeFunction;
import stanhebben.minetweaker.mods.forestry.functions.MoistenerRemoveFuelFunction;

/**
 *
 * @author Stanneke
 */
public class MoistenerValue extends TweakerValue {
	public static final MoistenerValue INSTANCE = new MoistenerValue();
	
	private MoistenerValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return MoistenerAddRecipeFunction.INSTANCE;
			case ADDFUEL:
				return MoistenerAddFuelFunction.INSTANCE;
			case REMOVEFUEL:
				return MoistenerRemoveFuelFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "forestry.moistener";
	}
}
