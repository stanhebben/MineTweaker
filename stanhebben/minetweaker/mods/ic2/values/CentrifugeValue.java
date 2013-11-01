//#fileifndef MC152
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.ic2.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.ic2.functions.CentrifugeAddRecipeFunction;

/**
 *
 * @author Stanneke
 */
public class CentrifugeValue extends TweakerValue {
	public static final CentrifugeValue INSTANCE = new CentrifugeValue();
	
	private CentrifugeValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return CentrifugeAddRecipeFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "ic2.thermalCentrifuge";
	}
}
