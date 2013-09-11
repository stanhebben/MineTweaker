//#fileifdef MC152
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.ic2.values;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class IC2Value extends TweakerValue {
	public static final IC2Value INSTANCE = new IC2Value();
	
	private IC2Value() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case MACERATOR:
				return MaceratorValue.INSTANCE;
			case EXTRACTOR:
				return ExtractorValue.INSTANCE;
			case COMPRESSOR:
				return CompressorValue.INSTANCE;
		}
		throw new TweakerExecuteException("No such member in ic2: " + index);
	}

	@Override
	public String toString() {
		return "modSupport.ic2";
	}
}
