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
import stanhebben.minetweaker.mods.buildcraft.AssemblyTableUtil;
import stanhebben.minetweaker.mods.buildcraft.actions.AssemblyTableRemoveRecipeAction;

/**
 *
 * @author Stanneke
 */
public class AssemblyTableRemoveRecipeFunction extends TweakerFunction {
	public static final AssemblyTableRemoveRecipeFunction INSTANCE = new AssemblyTableRemoveRecipeFunction();
	
	private AssemblyTableRemoveRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("The assemblyTable.remove function requires at least one argument");
		} else if (arguments.length == 1) {
			return AssemblyTableRemoveFunction.INSTANCE.call(namespace, arguments);
		} else {
			TweakerItemStackPattern output = 
					notNull(arguments[0], "the assemblyTable.removeRecipe output argument cannot be null")
					.toItemStackPattern("the assemblyTable.removeRecipe output argument must be an item stack pattern value");
			TweakerArray recipeArray =
					notNull(arguments[1], "the assemblyTable.removeRecipe recipe argument cannot be null")
					.toArray("the assemblyTable.removeRecipe recipe argument must be an item stack pattern array");
			boolean wildcard = arguments.length >= 3 &&
					notNull(arguments[2], "the assemblyTable.removeRecipe wildcard argument cannot be null")
					.toBool("the wildcard argument must be a bool value").get();
			
			TweakerItemStackPattern[] recipePattern = new TweakerItemStackPattern[recipeArray.size()];
			for (int i = 0; i < recipeArray.size(); i++) {
				recipePattern[i] = 
						notNull(recipeArray.get(i), "an assembly table remove recipe cannot contain null values")
						.toItemStackPattern("an assembly table remove recipe must only contain item stack patterns");
			}
			
			List<AssemblyRecipe> recipes = AssemblyRecipe.assemblyRecipes;
			for (int i = recipes.size() - 1; i >= 0; i--) {
				AssemblyRecipe recipe = recipes.get(i);
				
				if (output.matches(recipe.output)
						&& AssemblyTableUtil.matches(recipe.input, recipePattern, wildcard)) {
					Tweaker.apply(new AssemblyTableRemoveRecipeAction(i));
				}
			}
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "assemblyTable.removeRecipe";
	}
}
