/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.function.PlanterAddPlantableFunction;
import stanhebben.minetweaker.mods.mfr.function.PlanterRemovePlantableFunction;

/**
 *
 * @author Stanneke
 */
public class PlanterValue extends TweakerValue {
	public static final PlanterValue INSTANCE = new PlanterValue();
	
	private PlanterValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDPLANTABLE:
				return PlanterAddPlantableFunction.INSTANCE;
			case REMOVEPLANTABLE:
				return PlanterRemovePlantableFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "mfr.planter";
	}
}
