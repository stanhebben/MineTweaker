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
public class OreWashingAddRecipeFunction extends TweakerFunction {
	public static final OreWashingAddRecipeFunction INSTANCE = new OreWashingAddRecipeFunction();
	
	private OreWashingAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2 || arguments.length > 3) {
			throw new TweakerExecuteException("adding an ore washing plant recipe requires 2-3 arguments");
		}
		
		TweakerItemStackPattern input =
				notNull(arguments[1], "input argument cannot be null")
				.toItemStackPattern("input argument must be an item stack pattern");
		TweakerValue output =
				notNull(arguments[0], "output argument cannot be null");
		int amount = arguments.length < 3 || arguments[2] == null ? 1000 :
				arguments[2].toInt("amount must be an int").get();
		
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("amount", amount);
		
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
					Recipes.oreWashing, "ore washing plant",
					new StackPatternRecipeInput(input),
					nbt,
					outputArray));
		} else if (output.asItemStack() != null) {
			// single output
			Tweaker.apply(new MachineAddRecipeAction(
					Recipes.oreWashing, "ore washing plant",
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
		return "oreWashingPlant.addRecipe";
	}
}
