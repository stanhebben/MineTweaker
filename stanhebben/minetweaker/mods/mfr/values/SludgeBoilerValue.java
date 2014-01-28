/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.values;

import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.function.SludgeBoilerAddDropFunction;
import stanhebben.minetweaker.mods.mfr.function.SludgeBoilerListDrops;
import stanhebben.minetweaker.mods.mfr.function.SludgeBoilerRemoveDropFunction;

/**
 *
 * @author Stanneke
 */
public class SludgeBoilerValue extends TweakerValue {
	public static final SludgeBoilerValue INSTANCE = new SludgeBoilerValue();
	
	private SludgeBoilerValue() {}

	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDDROP:
				return SludgeBoilerAddDropFunction.INSTANCE;
			case REMOVEDROP:
				return SludgeBoilerRemoveDropFunction.INSTANCE;
			case LISTDROPS:
				return SludgeBoilerListDrops.INSTANCE;
		}
		return index(index);
	}
	
	@Override
	public String toString() {
		return "mfr.sludgeBoiler";
	}
}
