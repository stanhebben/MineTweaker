package stanhebben.minetweaker.mods.te.values;

import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.te.functions.SmelterAddRecipeFunction;

public class SmelterValue extends TweakerValue {
	public static final SmelterValue INSTANCE = new SmelterValue();
	
	private SmelterValue() {}
	
	@Override
	public TweakerValue index(String index) {
		if (index.equals("addRecipe")) {
			return SmelterAddRecipeFunction.INSTANCE;
		} else {
			return super.index(index);
		}
	}
	
	@Override
	public String toString() {
		return "thermalexpansion.smelter";
	}
}
