/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.functions;

import buildcraft.api.recipes.AssemblyRecipe;
import java.util.List;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import static stanhebben.minetweaker.api.value.TweakerValue.notNull;
import stanhebben.minetweaker.mods.buildcraft.AssemblyTableUtil;
import stanhebben.minetweaker.mods.buildcraft.actions.AssemblyTableAddRecipeAction;
import stanhebben.minetweaker.mods.buildcraft.actions.AssemblyTableRemoveRecipeAction;

/**
 *
 * @author Stanneke
 */
public class AssemblyTableSetEnergyFunction extends TweakerFunction {
	public static final AssemblyTableSetEnergyFunction INSTANCE = new AssemblyTableSetEnergyFunction();
	
	private AssemblyTableSetEnergyFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2) {
			throw new TweakerExecuteException("assemblyTable.setEnergy requires at least 2 arguments");
		} else if (arguments.length == 2) {
			TweakerItemStackPattern output =
					notNull(arguments[0], "the assemblyTable.setEnergy output argument cannot be null")
					.toItemStackPattern("the assemblyTable.setEnergy output argument must be an item stack pattern");
			int energy =
					notNull(arguments[1], "the assemblyTable.setEnergy energy argument cannot be null")
					.toInt("the assemblyTable.setEnergy energy argument must be an int").get();
			
			List<AssemblyRecipe> recipes = AssemblyRecipe.assemblyRecipes;
			for (int i = recipes.size() - 1; i >= 0; i--) {
				AssemblyRecipe recipe = recipes.get(i);
				if (output.matches(recipe.output)) {
					Tweaker.apply(new AssemblyTableRemoveRecipeAction(i));
					Tweaker.apply(new AssemblyTableAddRecipeAction(new AssemblyRecipe(recipe.input, energy, recipe.output)));
				}
			}
		} else {
			TweakerItemStackPattern output =
					notNull(arguments[0], "the assemblyTable.setEnergy output argument cannot be null")
					.toItemStackPattern("the assemblyTable.setEnergy output argument must be an item stack pattern");
			int energy =
					notNull(arguments[1], "the assemblyTable.setEnergy energy argument cannot be null")
					.toInt("the assemblyTable.setEnergy energy argument must be an int").get();
			TweakerArray inputArray =
					notNull(arguments[2], "the assemblyTable.setEnergy recipe argument cannot be null")
					.toArray("the assemblyTable.setEnergy recipe argument must be an item stack pattern array");
			boolean wildcard = arguments.length >= 4 &&
					notNull(arguments[3], "the assemblyTable.setEnergy wildcard argument cannot be null")
					.toBool("the assemblyTable.setEnergy wildcard argument must be a boolean").get();
			
			TweakerItemStackPattern[] inputPattern = new TweakerItemStackPattern[inputArray.size()];
			for (int i = 0; i < inputArray.size(); i++) {
				inputPattern[i] =
						notNull(inputArray.get(i), "each recipe item must not be null")
						.toItemStackPattern("each recipe item must be an item stack pattern");
			}
			
			List<AssemblyRecipe> recipes = AssemblyRecipe.assemblyRecipes;
			for (int i = recipes.size() - 1; i >= 0; i--) {
				AssemblyRecipe recipe = recipes.get(i);
				if (output.matches(recipe.output) && AssemblyTableUtil.matches(recipe.input, inputPattern, wildcard)) {
					Tweaker.apply(new AssemblyTableRemoveRecipeAction(i));
					Tweaker.apply(new AssemblyTableAddRecipeAction(new AssemblyRecipe(recipe.input, energy, recipe.output)));
				}
			}
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "assemblyTable.setEnergy";
	}
}
