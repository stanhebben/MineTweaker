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
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.actions.AssemblyTableRemoveRecipeAction;

/**
 *
 * @author Stanneke
 */
public class AssemblyTableRemoveFunction extends TweakerFunction {
	public static final AssemblyTableRemoveFunction INSTANCE = new AssemblyTableRemoveFunction();
	
	private AssemblyTableRemoveFunction() {}

	public int remove(TweakerItemStackPattern pattern) {
		List<AssemblyRecipe> recipes = AssemblyRecipe.assemblyRecipes;
		int numRecipesRemoved = 0;
		for (int i = recipes.size() - 1; i >= 0; i--) {
			if (pattern.matches(recipes.get(i).output)) {
				Tweaker.apply(new AssemblyTableRemoveRecipeAction(i));
				numRecipesRemoved++;
			}
		}
		return numRecipesRemoved;
	}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("assemblyTable.remove requires one argument");
		}
		
		TweakerItemStackPattern pattern =
				notNull(arguments[0], "the assemblyTable.remove argument cannot be null")
				.toItemStackPattern("the assemblyTable.remove argument must be an item stack pattern");
		return new TweakerInt(remove(pattern));
	}

	@Override
	public String toString() {
		return "assemblyTable.remove";
	}
}
