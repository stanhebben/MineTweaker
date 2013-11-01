//#fileifndef OLDIC2
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.ic2.functions;

import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
public class CentrifugeAddRecipeFunction extends TweakerFunction {
	public static final CentrifugeAddRecipeFunction INSTANCE = new CentrifugeAddRecipeFunction();
	
	public CentrifugeAddRecipeFunction() {
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 3) {
			throw new TweakerExecuteException("adding a thermal centrifuge recipe requires 3 arguments");
		}
		
		TweakerItemStackPattern input =
				notNull(arguments[1], "input argument cannot be null")
				.toItemStackPattern("input argument must be an item stack pattern");
		TweakerValue output =
				notNull(arguments[0], "output argument cannot be null");
		int minHeat = 
				notNull(arguments[2], "minHeat argument cannot be null")
				.toInt("minHeat argument must be an int").get();
		
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("minHeat", minHeat);
		
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
					Recipes.centrifuge, "thermal centrifuge",
					new StackPatternRecipeInput(input),
					nbt,
					outputArray));
		} else if (output.asItemStack() != null) {
			// single output
			Tweaker.apply(new MachineAddRecipeAction(
					Recipes.centrifuge, "thermal certifuge",
					new StackPatternRecipeInput(input),
					nbt,
					output.asItemStack().get()));
		} else {
			throw new TweakerExecuteException("machine output must be either an item stack, or an array of item stacks");
		}
		return null;
	}

	@Override
	public String toString() {
		return "ic2.thermalCentrifuge.addRecipe";
	}
}
