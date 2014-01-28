/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.function.BreederAddFoodFunction;
import stanhebben.minetweaker.mods.mfr.function.BreederRemoveFoodFunction;

/**
 *
 * @author Stanneke
 */
public class BreederValue extends TweakerValue {
	public static final BreederValue INSTANCE = new BreederValue();
	
	private BreederValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDFOOD:
				return BreederAddFoodFunction.INSTANCE;
			case REMOVEFOOD:
				return BreederRemoveFoodFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "mfr.breeder";
	}
}
