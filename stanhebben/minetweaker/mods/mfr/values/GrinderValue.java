/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.function.GrinderAddBlacklistFunction;
import stanhebben.minetweaker.mods.mfr.function.GrinderAddGrindableFunction;
import stanhebben.minetweaker.mods.mfr.function.GrinderRemoveBlacklistFunction;
import stanhebben.minetweaker.mods.mfr.function.GrinderRemoveGrindableFunction;

/**
 *
 * @author Stanneke
 */
public class GrinderValue extends TweakerValue {
	public static final GrinderValue INSTANCE = new GrinderValue();
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDGRINDABLE:
				return GrinderAddGrindableFunction.INSTANCE;
			case ADDBLACKLIST:
				return GrinderAddBlacklistFunction.INSTANCE;
			case REMOVEGRINDABLE:
				return GrinderRemoveGrindableFunction.INSTANCE;
			case REMOVEBLACKLIST:
				return GrinderRemoveBlacklistFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "mfr.grinder";
	}
}

