/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class MFRValue extends TweakerValue {
	public static final MFRValue INSTANCE = new MFRValue();
	
	private MFRValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case PLANTER:
				return PlanterValue.INSTANCE;
			case HARVESTER:
				return HarvesterValue.INSTANCE;
			case FERTILIZER:
				return FertilizerValue.INSTANCE;
			case RANCHER:
				return RancherValue.INSTANCE;
			case GRINDER:
				return GrinderValue.INSTANCE;
			case SLUDGEBOILER:
				return SludgeBoilerValue.INSTANCE;
			case BREEDER:
				return BreederValue.INSTANCE;
			case SAFARINET:
				return SafariNetValue.INSTANCE;
			case RUBBERTREE:
				return RubberTreeValue.INSTANCE;
			case MININGLASER:
				return MiningLaserValue.INSTANCE;
			case FRUITPICKER:
				return FruitPickerValue.INSTANCE;
			case AUTOSPAWNER:
				return AutoSpawnerValue.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "modSupport.mfr";
	}
}
