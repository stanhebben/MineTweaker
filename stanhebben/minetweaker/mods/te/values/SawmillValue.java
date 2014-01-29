package stanhebben.minetweaker.mods.te.values;

import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.te.functions.SawmillAddRecipeFunction;

public class SawmillValue extends TweakerValue {
	public static final SawmillValue INSTANCE = new SawmillValue();
	
	private SawmillValue() {}
	
	@Override
	public TweakerValue index(String index) {
		if (index.equals("addRecipe")) {
			return SawmillAddRecipeFunction.INSTANCE;
		} else {
			return super.index(index);
		}
	}
	
	@Override
	public String toString() {
		return "thermalexpansion.sawmill";
	}
}
