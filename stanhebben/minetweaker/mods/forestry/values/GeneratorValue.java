/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.functions.GeneratorAddFuelFunction;
import stanhebben.minetweaker.mods.forestry.functions.GeneratorRemoveFuelFunction;

/**
 *
 * @author Stanneke
 */
public class GeneratorValue extends TweakerValue {
	public static final GeneratorValue INSTANCE = new GeneratorValue();
	
	private GeneratorValue() {}

	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDFUEL:
				return GeneratorAddFuelFunction.INSTANCE;
			case REMOVEFUEL:
				return GeneratorRemoveFuelFunction.INSTANCE;
		}
		return super.index(index);
	}
	
	@Override
	public String toString() {
		return "forestry.bioGenerator";
	}
}
