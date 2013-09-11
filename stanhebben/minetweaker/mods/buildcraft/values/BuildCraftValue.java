/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.values;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.functions.SetSoftBlockFunction;

/**
 *
 * @author Stanneke
 */
public class BuildCraftValue extends TweakerValue {
	public static final BuildCraftValue INSTANCE = new BuildCraftValue();
	
	private BuildCraftValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ASSEMBLYTABLE:
				return AssemblyTableValue.INSTANCE;
			case SETSOFTBLOCK:
				return SetSoftBlockFunction.INSTANCE;
			case FUELS:
				return FuelsValue.INSTANCE;
			case COOLANTS:
				return CoolantsValue.INSTANCE;
			case REFINERY:
				return RefineryValue.INSTANCE;
		}
		throw new TweakerExecuteException("No such member in buildcraft mod support: " + index);
	}
	
	@Override
	public String toString() {
		return "buildcraft";
	}
}
