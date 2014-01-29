package stanhebben.minetweaker.mods.te.values;

import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.te.functions.CrucibleAddRecipeFunction;

public class CrucibleValue extends TweakerValue {
	public static final CrucibleValue INSTANCE = new CrucibleValue();
	
	private CrucibleValue() {}
	
	@Override
	public TweakerValue index(String index) {
		if (index.equals("addRecipe")) {
			return CrucibleAddRecipeFunction.INSTANCE;
		} else {
			return super.index(index);
		}
	}
	
	@Override
	public String toString() {
		return "thermalexpansion.crucible";
	}
}
