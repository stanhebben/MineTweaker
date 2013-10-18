/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.functions.BronzeEngineAddFuelFunction;
import stanhebben.minetweaker.mods.forestry.functions.BronzeEngineRemoveFuelFunction;

/**
 *
 * @author Stanneke
 */
public class BronzeEngineValue extends TweakerValue {
	public static final BronzeEngineValue INSTANCE = new BronzeEngineValue();
	
	private BronzeEngineValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDFUEL:
				return BronzeEngineAddFuelFunction.INSTANCE;
			case REMOVEFUEL:
				return BronzeEngineRemoveFuelFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "forestry.biogasEngine";
	}
}
