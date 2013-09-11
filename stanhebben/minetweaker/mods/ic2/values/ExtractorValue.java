//#fileifdef MC152
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.ic2.values;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.ic2.functions.ExtractorAddRecipeFunction;
import stanhebben.minetweaker.mods.ic2.functions.ExtractorRemoveFunction;
import stanhebben.minetweaker.mods.ic2.functions.ExtractorRemoveRecipeFunction;

/**
 *
 * @author Stanneke
 */
public class ExtractorValue extends TweakerValue {
	public static final ExtractorValue INSTANCE = new ExtractorValue();
	
	private ExtractorValue() {}

	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return ExtractorAddRecipeFunction.INSTANCE;
			case REMOVERECIPE:
				return ExtractorRemoveRecipeFunction.INSTANCE;
			case REMOVE:
				return ExtractorRemoveFunction.INSTANCE;
		}
		throw new TweakerExecuteException("no such member in extractor: " + index);
	}
	
	@Override
	public String toString() {
		return "ic2.extractor";
	}
}
