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
import stanhebben.minetweaker.mods.ic2.actions.CompressorRemoveRecipeAction;

/**
 *
 * @author Stanneke
 */
public class CompressorRemoveRecipeFunction extends TweakerFunction {
	public static final CompressorRemoveRecipeFunction INSTANCE = new CompressorRemoveRecipeFunction();
	
	private CompressorRemoveRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("compressor.removeRecipe requires at least one argument");
		} else if (arguments.length == 1) {
			return CompressorRemoveFunction.INSTANCE.call(namespace, arguments);
		} else {
			TweakerItemStackPattern output =
					notNull(arguments[0], "compressor.removeRecipe output cannot be null")
					.toItemStackPattern("compressor.removeRecipe output must be an item stack pattern");
			TweakerItemPattern input =
					notNull(arguments[1], "compressor.removeRecipe input cannot be null")
					.toItemPattern("compressor.removeRecipe input must be an item pattern");
			CompressorRemoveRecipeAction action = null;
			for (Map.Entry<ItemStack, ItemStack> recipe : Recipes.compressor.getRecipes().entrySet()) {
				if (output.matches(recipe.getValue()) && input.matches(recipe.getKey())) {
					action = new CompressorRemoveRecipeAction(recipe.getKey());
					break;
				}
			}
			if (action != null) Tweaker.apply(action);
		}
		return null;
	}

	@Override
	public String toString() {
		return "ic2.compressor.removeRecipe";
	}
}
