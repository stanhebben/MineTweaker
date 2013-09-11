/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.values;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.functions.RefineryAddRecipeFunction;
import stanhebben.minetweaker.mods.buildcraft.functions.RefineryRemoveFunction;
import stanhebben.minetweaker.mods.buildcraft.functions.RefineryRemoveRecipeFunction;

/**
 *
 * @author Stanneke
 */
public class RefineryValue extends TweakerValue {
	public static final RefineryValue INSTANCE = new RefineryValue();
	
	private RefineryValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return RefineryAddRecipeFunction.INSTANCE;
			case REMOVERECIPE:
				return RefineryRemoveRecipeFunction.INSTANCE;
			case REMOVE:
				return RefineryRemoveFunction.INSTANCE;
		}
		
		throw new TweakerExecuteException("No such member in refinery: " + index);
	}
	
	@Override
	public String toString() {
		return "buildcraft.refinery";
	}
}
