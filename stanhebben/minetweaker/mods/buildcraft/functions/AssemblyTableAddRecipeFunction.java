/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.functions;

import buildcraft.api.recipes.AssemblyRecipe;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.actions.AssemblyTableAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class AssemblyTableAddRecipeFunction extends TweakerFunction {
	public static final AssemblyTableAddRecipeFunction INSTANCE = new AssemblyTableAddRecipeFunction();
	
	private AssemblyTableAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 3) {
			throw new TweakerExecuteException("assemblyTable.addRecipe requires at least 3 arguments");
		}
		
		TweakerItemStack output = 
				notNull(arguments[0], "assemblyTable.addRecipe output cannot be null")
				.toItemStack("assemblyTable.addRecipe output must be an item stack");
		TweakerInt energy =
				notNull(arguments[1], "assemblyTable.addRecipe energy cannot be null")
				.toInt("assemblyTable.addRecipe energy must be an int");
		TweakerArray items =
				notNull(arguments[2], "assemblyTable.addRecipe recipe cannot be null")
				.toArray("assemblyTable.addRecipe recipe must be an array of items");
		
		ItemStack[] input = new ItemStack[items.size()];
		for (int i = 0; i < items.size(); i++) {
			input[i] = notNull(items.get(i), "assemblyTable.addRecipe recipe item cannot be null")
					.toItemStack("assemblyTable.addRecipe recipe item must be an item stack")
					.get();
		}
		
		Tweaker.apply(new AssemblyTableAddRecipeAction(new AssemblyRecipe(input, energy.get(), output.get())));
		return null;
	}

	@Override
	public String toString() {
		return "buildcraft.assemblyTable.addRecipe";
	}
}
