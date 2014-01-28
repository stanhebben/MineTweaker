/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.function.MiningLaserAddOreFunction;
import stanhebben.minetweaker.mods.mfr.function.MiningLaserAddPreferredOreFunction;
import stanhebben.minetweaker.mods.mfr.function.MiningLaserRemoveOreFunction;
import stanhebben.minetweaker.mods.mfr.function.MiningLaserRemovePreferredOreFunction;

/**
 *
 * @author Stanneke
 */
public class MiningLaserValue extends TweakerValue {
	public static final MiningLaserValue INSTANCE = new MiningLaserValue();
	
	private MiningLaserValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDORE:
				return MiningLaserAddOreFunction.INSTANCE;
			case ADDPREFERREDORE:
				return MiningLaserAddPreferredOreFunction.INSTANCE;
			case REMOVEORE:
				return MiningLaserRemoveOreFunction.INSTANCE;
			case REMOVEPREFERREDORE:
				return MiningLaserRemovePreferredOreFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "mfr.miningLaser";
	}
}
