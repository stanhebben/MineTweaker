package stanhebben.minetweaker.mods.te.values;

import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.te.functions.MagmaticDynamoAddFuelFunction;

public class MagmaticDynamoValue extends TweakerValue {
	public static final MagmaticDynamoValue INSTANCE = new MagmaticDynamoValue();
	
	private MagmaticDynamoValue() {}
	
	@Override
	public TweakerValue index(String index) {
		if (index.equals("addFuel")) {
			return MagmaticDynamoAddFuelFunction.INSTANCE;
		} else {
			return super.index(index);
		}
	}
	
	@Override
	public String toString() {
		return "thermalexpansion.magmaticDynamo";
	}
}
