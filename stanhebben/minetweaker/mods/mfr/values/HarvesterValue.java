/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.function.HarvesterAddHarvestableFunction;

/**
 *
 * @author Stanneke
 */
public class HarvesterValue extends TweakerValue {
	public static final HarvesterValue INSTANCE = new HarvesterValue();
	
	private HarvesterValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDHARVESTABLE:
				return HarvesterAddHarvestableFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "mfr.harvester";
	}
}
