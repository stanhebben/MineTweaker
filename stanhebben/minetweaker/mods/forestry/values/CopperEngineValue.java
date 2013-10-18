/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.functions.CopperEngineAddFuelFunction;
import stanhebben.minetweaker.mods.forestry.functions.CopperEngineRemoveFuelFunction;

/**
 *
 * @author Stanneke
 */
public class CopperEngineValue extends TweakerValue {
	public static final CopperEngineValue INSTANCE = new CopperEngineValue();
	
	private CopperEngineValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDFUEL:
				return CopperEngineAddFuelFunction.INSTANCE;
			case REMOVEFUEL:
				return CopperEngineRemoveFuelFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "forestry.peatFiredEngine";
	}
}
