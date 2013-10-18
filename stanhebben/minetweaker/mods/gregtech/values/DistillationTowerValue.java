/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.gregtech.functions.DistillationTowerAddRecipeFunction;

/**
 *
 * @author Stanneke
 */
public class DistillationTowerValue extends TweakerValue {
	public static final DistillationTowerValue INSTANCE = new DistillationTowerValue();
	
	private DistillationTowerValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return DistillationTowerAddRecipeFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "gregtech.distillationTower";
	}
}
