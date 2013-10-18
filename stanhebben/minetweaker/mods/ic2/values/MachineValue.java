//#fileifndef MC152
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.ic2.values;

import ic2.api.recipe.IMachineRecipeManager;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.ic2.functions.MachineAddRecipeFunction;

/**
 *
 * @author Stanneke
 */
public class MachineValue extends TweakerValue {
	private final IMachineRecipeManager machine;
	private final String machineName;
	private final MachineAddRecipeFunction addFunction;
	
	public MachineValue(IMachineRecipeManager machine, String machineName) {
		this.machine = machine;
		this.machineName = machineName;
		addFunction = new MachineAddRecipeFunction(machine, machineName);
	}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return addFunction;
			/*case REMOVERECIPE:
				return CompressorRemoveRecipeFunction.INSTANCE;
			case REMOVE:
				return CompressorRemoveFunction.INSTANCE;*/
		}
		return super.index(index);
	}

	@Override
	public String toString() {
		return "ic2." + machineName;
	}
}
