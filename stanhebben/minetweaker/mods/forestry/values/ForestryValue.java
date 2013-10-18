/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class ForestryValue extends TweakerValue {
	public static final ForestryValue INSTANCE = new ForestryValue();
	
	private ForestryValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case BEES:
				return BeesValue.INSTANCE;
			case CARPENTER:
				return CarpenterValue.INSTANCE;
			case CENTRIFUGE:
				return CentrifugeValue.INSTANCE;
			case FABRICATOR:
				return FabricatorValue.INSTANCE;
			case FERMENTER:
				return FermenterValue.INSTANCE;
			case MOISTENER:
				return MoistenerValue.INSTANCE;
			case SQUEEZER:
				return SqueezerValue.INSTANCE;
			case STILL:
				return StillValue.INSTANCE;
			case RAINMAKER:
				return RainmakerValue.INSTANCE;
			case BIOGASENGINE:
				return BronzeEngineValue.INSTANCE;
			case PEATFIREDENGINE:
				return CopperEngineValue.INSTANCE;
			case BIOGENERATOR:
				return GeneratorValue.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "forestry";
	}
}
