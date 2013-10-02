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
import stanhebben.minetweaker.mods.ic2.actions.MaceratorRemoveRecipeAction;

/**
 *
 * @author Stanneke
 */
public class MaceratorRemoveFunction extends TweakerFunction {
	public static final MaceratorRemoveFunction INSTANCE = new MaceratorRemoveFunction();
	
	private MaceratorRemoveFunction() {}

	public int remove(TweakerItemStackPattern pattern) {
		ArrayList<MaceratorRemoveRecipeAction> actions = new ArrayList<MaceratorRemoveRecipeAction>();
		for (Map.Entry<ItemStack, ItemStack> recipe : Recipes.extractor.getRecipes().entrySet()) {
			if (pattern.matches(recipe.getValue())) {
				actions.add(new MaceratorRemoveRecipeAction(recipe.getKey()));
			}
		}
		for (MaceratorRemoveRecipeAction action : actions) {
			Tweaker.apply(action);
		}
		return actions.size();
	}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("macerator.remove requires at least one argument");
		} else {
			TweakerItemStackPattern output =
					notNull(arguments[0], "macerator.remove argument must not be null")
					.toItemStackPattern("macerator.remove argument must be an item stack pattern");
			return new TweakerInt(remove(output));
		}
	}

	@Override
	public String toString() {
		return "ic2.macerator.remove";
	}
}
