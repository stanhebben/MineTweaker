//#fileifdef MC152
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.ic2.functions;

import ic2.api.recipe.Recipes;
import java.util.Map;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.ic2.actions.MaceratorRemoveRecipeAction;

/**
 *
 * @author Stanneke
 */
public class MaceratorRemoveRecipeFunction extends TweakerFunction {
	public static final MaceratorRemoveRecipeFunction INSTANCE = new MaceratorRemoveRecipeFunction();
	
	private MaceratorRemoveRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("macerator.removeRecipe requires at least one argument");
		} else if (arguments.length == 1) {
			return MaceratorRemoveFunction.INSTANCE.call(namespace, arguments);
		} else {
			TweakerItemStackPattern output =
					notNull(arguments[0], "macerator.removeRecipe output cannot be null")
					.toItemStackPattern("macerator.removeRecipe output must be an item stack pattern");
			TweakerItemPattern input =
					notNull(arguments[1], "macerator.removeRecipe input cannot be null")
					.toItemPattern("macerator.removeRecipe input must be an item pattern");
			MaceratorRemoveRecipeAction action = null;
			for (Map.Entry<ItemStack, ItemStack> recipe : Recipes.macerator.getRecipes().entrySet()) {
				if (output.matches(recipe.getValue()) && input.matches(recipe.getKey())) {
					action = new MaceratorRemoveRecipeAction(recipe.getKey());
					break;
				}
			}
			if (action != null) Tweaker.apply(action);
		}
		return null;
	}

	@Override
	public String toString() {
		return "ic2.macerator.removeRecipe";
	}
}
