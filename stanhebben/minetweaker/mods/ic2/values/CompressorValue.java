//#fileifdef MC152
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.ic2.values;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.ic2.functions.CompressorAddRecipeFunction;
import stanhebben.minetweaker.mods.ic2.functions.CompressorRemoveFunction;
import stanhebben.minetweaker.mods.ic2.functions.CompressorRemoveRecipeFunction;

/**
 *
 * @author Stanneke
 */
public class CompressorValue extends TweakerValue {
	public static final CompressorValue INSTANCE = new CompressorValue();
	
	private CompressorValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return CompressorAddRecipeFunction.INSTANCE;
			case REMOVERECIPE:
				return CompressorRemoveRecipeFunction.INSTANCE;
			case REMOVE:
				return CompressorRemoveFunction.INSTANCE;
		}
		throw new TweakerExecuteException("No such member in ic2.compressor: " + index);
	}

	@Override
	public String toString() {
		return "ic2.compressor";
	}
}
