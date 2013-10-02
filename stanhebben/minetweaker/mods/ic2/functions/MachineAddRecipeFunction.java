//#fileifndef OLDIC2
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.ic2.functions;

import ic2.api.recipe.IMachineRecipeManager;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.ic2.StackPatternRecipeInput;
import stanhebben.minetweaker.mods.ic2.actions.MachineAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class MachineAddRecipeFunction extends TweakerFunction {
	private final IMachineRecipeManager machine;
	private final String machineName;
	
	public MachineAddRecipeFunction(IMachineRecipeManager machine, String machineName) {
		this.machine = machine;
		this.machineName = machineName;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2) {
			throw new TweakerExecuteException("adding a machine recipe requires two arguments");
		}
		
		TweakerItemStackPattern input =
				notNull(arguments[1], "input argument cannot be null")
				.toItemStackPattern("input argument must be an item stack pattern");
		TweakerValue output =
				notNull(arguments[0], "output argument cannot be null");
		if (output.asArray() != null) {
			// multiple outputs
			TweakerArray outputTA = output.asArray();
			ItemStack[] outputArray = new ItemStack[outputTA.size()];
			for (int i = 0; i < outputTA.size(); i++) {
				outputArray[i] = 
						notNull(outputTA.get(i), "output value cannot be null")
						.toItemStack("output values must be item stacks")
						.get();
			}
			Tweaker.apply(new MachineAddRecipeAction(
					machine, machineName,
					new StackPatternRecipeInput(input),
					outputArray));
		} else if (output.asItemStack() != null) {
			// single output
			Tweaker.apply(new MachineAddRecipeAction(
					machine, machineName,
					new StackPatternRecipeInput(input),
					output.asItemStack().get()));
		} else {
			throw new TweakerExecuteException("machine output must be either an item stack, or an array of item stacks");
		}
		return null;
	}

	@Override
	public String toString() {
		return "ic2.addRecipe[" + machineName + "]";
	}
}
