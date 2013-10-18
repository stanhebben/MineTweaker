/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.gregtech.functions.FusionReactorAddRecipeFunction;

/**
 *
 * @author Stanneke
 */
public class FusionReactorValue extends TweakerValue {
	public static final FusionReactorValue INSTANCE = new FusionReactorValue();
	
	private FusionReactorValue() {}
	
	@Override
	public TweakerValue index(String value) {
		switch (TweakerField.get(value)) {
			case ADDRECIPE:
				return FusionReactorAddRecipeFunction.INSTANCE;
		}
		return super.index(value);
	}

	@Override
	public String toString() {
		return "gregtech.fusionReactor";
	}
}
