/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.gregtech.functions.WiremillAddRecipeFunction;

/**
 *
 * @author Stanneke
 */
public class WiremillValue extends TweakerValue {
	public static final WiremillValue INSTANCE = new WiremillValue();
	
	private  WiremillValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return WiremillAddRecipeFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "gregtech.wiremill";
	}
}
