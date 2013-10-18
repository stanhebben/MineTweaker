/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.functions.BeesBlacklistFunction;
import stanhebben.minetweaker.mods.forestry.functions.PrintAlleleListFunction;

/**
 *
 * @author Stanneke
 */
public class BeesValue extends TweakerValue {
	public static final BeesValue INSTANCE = new BeesValue();
	
	private BeesValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case BLACKLIST:
				return BeesBlacklistFunction.INSTANCE;
			case PRINTGENES:
				return PrintAlleleListFunction.INSTANCE;
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "forestry.bees";
	}
}
