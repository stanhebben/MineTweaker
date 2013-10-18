/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.functions.FermenterAddFuelFunction;
import stanhebben.minetweaker.mods.forestry.functions.FermenterAddRecipeFunction;
import stanhebben.minetweaker.mods.forestry.functions.FermenterRemoveFuelFunction;

/**
 *
 * @author Stanneke
 */
public class FermenterValue extends TweakerValue {
	public static final FermenterValue INSTANCE = new FermenterValue();
	
	private FermenterValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return FermenterAddRecipeFunction.INSTANCE;
			case ADDFUEL:
				return FermenterAddFuelFunction.INSTANCE;
			case REMOVEFUEL:
				return FermenterRemoveFuelFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "forestry.fermenter";
	}
}
