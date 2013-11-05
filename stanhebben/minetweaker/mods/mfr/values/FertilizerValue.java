/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.function.FertilizerAddFertilizableFunction;
import stanhebben.minetweaker.mods.mfr.function.FertilizerAddFertilizerFunction;
import stanhebben.minetweaker.mods.mfr.function.FertilizerRemoveFertilizableFunction;
import stanhebben.minetweaker.mods.mfr.function.FertilizerRemoveFertilizerFunction;

/**
 *
 * @author Stanneke
 */
public class FertilizerValue extends TweakerValue {
	public static final FertilizerValue INSTANCE = new FertilizerValue();
	
	private FertilizerValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDFERTILIZER:
				return FertilizerAddFertilizerFunction.INSTANCE;
			case ADDFERTILIZABLE:
				return FertilizerAddFertilizableFunction.INSTANCE;
			case REMOVEFERTILIZER:
				return FertilizerRemoveFertilizerFunction.INSTANCE;
			case REMOVEFERTILIZABLE:
				return FertilizerRemoveFertilizableFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "mfr.fertilizer";
	}
}
