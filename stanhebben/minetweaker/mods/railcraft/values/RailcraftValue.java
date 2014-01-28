/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.railcraft.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class RailcraftValue extends TweakerValue {
	public static final RailcraftValue INSTANCE = new RailcraftValue();
	
	private RailcraftValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
/*			case FUELS:
				return FuelsValue.INSTANCE;*/
			case BLASTFURNACE:
//				return BlastFurnaceValue.INSTANCE;
/*			case COKEOVEN:
				return CokeOvenValue.INSTANCE;
			case ROCKCRUSHER:
				return RockCrusherValue.INSTANCE;
			case ROLLINGMACHINE:
				return RollingMachineValue.INSTANCE;*/
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "modSupport.railcraft";
	}
}
