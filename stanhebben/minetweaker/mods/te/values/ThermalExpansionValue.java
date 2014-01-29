package stanhebben.minetweaker.mods.te.values;

import stanhebben.minetweaker.api.value.TweakerValue;

public class ThermalExpansionValue extends TweakerValue {
	public static final ThermalExpansionValue INSTANCE = new ThermalExpansionValue();
	
	private ThermalExpansionValue() {}
	
	@Override
	public TweakerValue index(String index) {
		if (index.equals("crucible")) {
			return CrucibleValue.INSTANCE;
		} else if (index.equals("furnace")) {
			return FurnaceValue.INSTANCE;
		} else if (index.equals("pulverizer")) {
			return PulverizerValue.INSTANCE;
		} else if (index.equals("sawmill")) {
			return SawmillValue.INSTANCE;
		} else if (index.equals("smelter")) {
			return SmelterValue.INSTANCE;
		} else if (index.equals("transposer")) {
			return TransposerValue.INSTANCE;
		} else if (index.equals("magmaticDynamo")) {
			return MagmaticDynamoValue.INSTANCE;
		} else {
			return super.index(index);
		}
	}
	
	@Override
	public String toString() {
		return "thermalexpansion";
	}
}
