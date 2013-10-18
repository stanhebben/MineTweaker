/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.functions.RainmakerAddItemFunction;
import stanhebben.minetweaker.mods.forestry.functions.RainmakerRemoveItemFunction;

/**
 *
 * @author Stanneke
 */
public class RainmakerValue extends TweakerValue {
	public static final RainmakerValue INSTANCE = new RainmakerValue();
	
	private RainmakerValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRAINITEM:
				return RainmakerAddItemFunction.INSTANCE_RAIN;
			case ADDSTOPITEM:
				return RainmakerAddItemFunction.INSTANCE_STOP;
			case REMOVEITEM:
				return RainmakerRemoveItemFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "forestry.rainmaker";
	}
}
