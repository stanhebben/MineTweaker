/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.ic2.values;

import ic2.api.recipe.Recipes;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class IC2Value extends TweakerValue {
	public static final IC2Value INSTANCE = new IC2Value();
	
	//#ifndef MC152
	private static final MachineValue COMPRESSOR = new MachineValue(Recipes.compressor, "compressor");
	private static final MachineValue EXTRACTOR = new MachineValue(Recipes.extractor, "extractor");
	private static final MachineValue MACERATOR = new MachineValue(Recipes.macerator, "macerator");
	//#endif
	
	private IC2Value() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case MACERATOR:
				//#ifdef MC152
				//+return MaceratorValue.INSTANCE;
				//#else
				return MACERATOR;
				//#endif
			case EXTRACTOR:
				//#ifdef MC152
				//+return ExtractorValue.INSTANCE;
				//#else
				return EXTRACTOR;
				//#endif
			case COMPRESSOR:
				//#ifdef MC152
				//+return CompressorValue.INSTANCE;
				//#else
				return COMPRESSOR;
				//#endif
			//#ifndef MC152
			case THERMALCENTRIFUGE:
				return CentrifugeValue.INSTANCE;
			case METALFORMER:
				return MetalFormer.INSTANCE;
			case OREWASHINGPLANT:
				return OreWashingValue.INSTANCE;
			//#endif
		}
		throw new TweakerExecuteException("No such member in ic2: " + index);
	}

	@Override
	public String toString() {
		return "modSupport.ic2";
	}
}
