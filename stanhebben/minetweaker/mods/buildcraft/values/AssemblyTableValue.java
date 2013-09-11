/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.values;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.functions.AssemblyTableAddRecipeFunction;
import stanhebben.minetweaker.mods.buildcraft.functions.AssemblyTableRemoveFunction;
import stanhebben.minetweaker.mods.buildcraft.functions.AssemblyTableRemoveRecipeFunction;
import stanhebben.minetweaker.mods.buildcraft.functions.AssemblyTableSetEnergyFunction;

/**
 *
 * @author Stanneke
 */
public class AssemblyTableValue extends TweakerValue {
	public static final AssemblyTableValue INSTANCE = new AssemblyTableValue();
	
	private AssemblyTableValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return AssemblyTableAddRecipeFunction.INSTANCE;
			case REMOVERECIPE:
				return AssemblyTableRemoveRecipeFunction.INSTANCE;
			case SETENERGY:
				return AssemblyTableSetEnergyFunction.INSTANCE;
			case REMOVE:
				return AssemblyTableRemoveFunction.INSTANCE;
		}
		throw new TweakerExecuteException("No such field in buildcraft.assemblyTable: " + index);
	}

	@Override
	public String toString() {
		return "buildcraft.assemblyTable";
	}
}
