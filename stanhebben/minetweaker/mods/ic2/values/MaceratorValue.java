//#fileifdef MC152
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.ic2.values;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.ic2.functions.MaceratorAddRecipeFunction;
import stanhebben.minetweaker.mods.ic2.functions.MaceratorRemoveFunction;
import stanhebben.minetweaker.mods.ic2.functions.MaceratorRemoveRecipeFunction;

/**
 *
 * @author Stanneke
 */
public class MaceratorValue extends TweakerValue {
	public static final MaceratorValue INSTANCE = new MaceratorValue();
	
	private MaceratorValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return MaceratorAddRecipeFunction.INSTANCE;
			case REMOVERECIPE:
				return MaceratorRemoveRecipeFunction.INSTANCE;
			case REMOVE:
				return MaceratorRemoveFunction.INSTANCE;
		}
		throw new TweakerExecuteException("No such member in macerator: " + index);
	}

	@Override
	public String toString() {
		return "ic2.macerator";
	}
}
