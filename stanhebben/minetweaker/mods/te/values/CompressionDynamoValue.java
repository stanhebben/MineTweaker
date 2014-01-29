package stanhebben.minetweaker.mods.te.values;

import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.te.functions.CompressionDynamoAddCoolantFunction;
import stanhebben.minetweaker.mods.te.functions.CompressionDynamoAddFuelFunction;

public class CompressionDynamoValue extends TweakerValue {
	public static final CompressionDynamoValue INSTANCE = new CompressionDynamoValue();
	
	private CompressionDynamoValue() {}
	
	@Override
	public TweakerValue index(String index) {
		if (index.equals("addFuel")) {
			return CompressionDynamoAddFuelFunction.INSTANCE;
		} else if (index.equals("addCoolant")) {
			return CompressionDynamoAddCoolantFunction.INSTANCE;
		} else {
			return super.index(index);
		}
	}
	
	@Override
	public String toString() {
		return "thermalexpansion.compressionDynamo";
	}
}
