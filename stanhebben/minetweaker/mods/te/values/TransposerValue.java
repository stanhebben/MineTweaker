package stanhebben.minetweaker.mods.te.values;

import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.te.functions.TransposerAddExtractRecipeFunction;
import stanhebben.minetweaker.mods.te.functions.TransposerAddFillRecipeFunction;

public class TransposerValue extends TweakerValue {
	public static final TransposerValue INSTANCE = new TransposerValue();
	
	private TransposerValue() {}
	
	@Override
	public TweakerValue index(String index) {
		if (index.equals("addFillRecipe")) {
			return TransposerAddFillRecipeFunction.INSTANCE;
		} else if (index.equals("addFillRecipe")) {
			return TransposerAddExtractRecipeFunction.INSTANCE;
		} else {
			return super.index(index);
		}
	}
	
	@Override
	public String toString() {
		return "thermalexpansion.transposer";
	}
}
