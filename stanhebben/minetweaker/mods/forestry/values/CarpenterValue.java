/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.functions.CarpenterAddRecipeFunction;

/**
 *
 * @author Stanneke
 */
public class CarpenterValue extends TweakerValue {
	public static final CarpenterValue INSTANCE = new CarpenterValue();
	
	private CarpenterValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return CarpenterAddRecipeFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "forestry.carpenter";
	}
}
