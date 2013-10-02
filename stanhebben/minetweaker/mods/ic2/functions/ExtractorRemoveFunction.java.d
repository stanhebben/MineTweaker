//#fileifdef MC152
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.ic2.functions;

import ic2.api.recipe.Recipes;
import java.util.ArrayList;
import java.util.Map;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.ic2.actions.ExtractorRemoveRecipeAction;

/**
 *
 * @author Stanneke
 */
public class ExtractorRemoveFunction extends TweakerFunction {
	public static final ExtractorRemoveFunction INSTANCE = new ExtractorRemoveFunction();
	
	private ExtractorRemoveFunction() {}

	public int remove(TweakerItemStackPattern pattern) {
		ArrayList<ExtractorRemoveRecipeAction> actions = new ArrayList<ExtractorRemoveRecipeAction>();
		for (Map.Entry<ItemStack, ItemStack> recipe : Recipes.extractor.getRecipes().entrySet()) {
			if (pattern.matches(recipe.getValue())) {
				actions.add(new ExtractorRemoveRecipeAction(recipe.getKey()));
			}
		}
		for (ExtractorRemoveRecipeAction action : actions) {
			Tweaker.apply(action);
		}
		return actions.size();
	}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("extractor.remove requires at least one argument");
		} else {
			TweakerItemStackPattern output =
					notNull(arguments[0], "extractor.remove argument must not be null")
					.toItemStackPattern("extractor.remove argument must be an item stack pattern");
			return new TweakerInt(remove(output));
		}
	}

	@Override
	public String toString() {
		return "ic2.extractor.remove";
	}
}
