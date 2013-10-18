/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.functions.StillAddRecipeFunction;

/**
 *
 * @author Stanneke
 */
public class StillValue extends TweakerValue {
	public static final StillValue INSTANCE = new StillValue();
	
	private StillValue() {}

	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return StillAddRecipeFunction.INSTANCE;
		}
		return super.index(index);
	}
	
	@Override
	public String toString() {
		return "forestry.still";
	}
}
