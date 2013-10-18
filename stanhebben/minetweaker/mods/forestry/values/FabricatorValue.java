/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.functions.FabricatorAddRecipeFunction;
import stanhebben.minetweaker.mods.forestry.functions.FabricatorAddSmeltingFunction;

/**
 *
 * @author Stanneke
 */
public class FabricatorValue extends TweakerValue {
	public static final FabricatorValue INSTANCE = new FabricatorValue();
	
	private FabricatorValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return FabricatorAddRecipeFunction.INSTANCE;
			case ADDSMELTING:
				return FabricatorAddSmeltingFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "forestry.fabricator";
	}
}
