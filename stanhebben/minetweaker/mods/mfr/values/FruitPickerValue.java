/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.function.FruitPickerAddFruitFunction;
import stanhebben.minetweaker.mods.mfr.function.FruitPickerAddLogFunction;
import stanhebben.minetweaker.mods.mfr.function.FruitPickerRemoveFruitFunction;
import stanhebben.minetweaker.mods.mfr.function.FruitPickerRemoveLogFunction;

/**
 *
 * @author Stanneke
 */
public class FruitPickerValue extends TweakerValue {
	public static final FruitPickerValue INSTANCE = new FruitPickerValue();
	
	private FruitPickerValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDFRUIT:
				return FruitPickerAddFruitFunction.INSTANCE;
			case ADDLOG:
				return FruitPickerAddLogFunction.INSTANCE;
			case REMOVEFRUIT:
				return FruitPickerRemoveFruitFunction.INSTANCE;
			case REMOVELOG:
				return FruitPickerRemoveLogFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "mfr.fruitPicker";
	}
}
