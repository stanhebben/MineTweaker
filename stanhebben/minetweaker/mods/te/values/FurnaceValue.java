package stanhebben.minetweaker.mods.te.values;

import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.te.functions.FurnaceAddRecipeFunction;

public class FurnaceValue extends TweakerValue {
	public static final FurnaceValue INSTANCE = new FurnaceValue();
	
	private FurnaceValue() {}
	
	@Override
	public TweakerValue index(String index) {
		if (index.equals("addRecipe")) {
			return FurnaceAddRecipeFunction.INSTANCE;
		} else {
			return super.index(index);
		}
	}
	
	@Override
	public String toString() {
		return "thermalexpansion.furnace";
	}
}
