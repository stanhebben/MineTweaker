package stanhebben.minetweaker.mods.te.values;

import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.te.functions.PulverizerAddRecipeFunction;

public class PulverizerValue extends TweakerValue {
	public static final PulverizerValue INSTANCE = new PulverizerValue();
	
	private PulverizerValue() {}
	
	@Override
	public TweakerValue index(String index) {
		if (index.equals("addRecipe")) {
			return PulverizerAddRecipeFunction.INSTANCE;
		} else {
			return super.index(index);
		}
	}
	
	@Override
	public String toString() {
		return "thermalexpansion.pulverizer";
	}
}
