/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.functions;

//#ifdef MC152
import buildcraft.api.recipes.RefineryRecipe;
//#else
//+import buildcraft.api.recipes.RefineryRecipes.Recipe;
//#endif
import java.util.ArrayList;
import java.util.logging.Level;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFluid;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.BuildCraftUtil;
import stanhebben.minetweaker.mods.buildcraft.actions.RefineryRemoveRecipeAction;

/**
 *
 * @author Stanneke
 */
public class RefineryRemoveRecipeFunction extends TweakerFunction {
	public static final RefineryRemoveRecipeFunction INSTANCE = new RefineryRemoveRecipeFunction();
	
	private RefineryRemoveRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("refinery.removeRecipe requires at least one argument");
		} else if (arguments.length == 1) {
			return RefineryRemoveFunction.INSTANCE.call(namespace, arguments);
		} else {
			TweakerFluid output =
					notNull(arguments[0], "the refinery.removeRecipe output cannot be null")
					.toFluid("the refinery.removeRecipe output must be a fluid");
			TweakerFluid input1 =
					notNull(arguments[1], "the refinery.removeRecipe input cannot be null")
					.toFluid("the refinery.removeRecipe input must be a fluid");
			TweakerFluid input2 = null;
			if (arguments.length >= 3) {
				input2 =
						notNull(arguments[2], "the refinery.removeRecipe input2 cannot be null")
						.toFluid("the refinery.removeRecipe input2 must be a fluid");
			}
			
			if (BuildCraftUtil.getRefineryRecipes() == null) {
				Tweaker.log(Level.WARNING, "Could not execute the refinery recipe removed due to reflection failing. Operation ignored.");
				return null;
			} else {
				ArrayList<RefineryRemoveRecipeAction> actions = new ArrayList<RefineryRemoveRecipeAction>();
				//#ifdef MC152
				for (RefineryRecipe recipe : BuildCraftUtil.getRefineryRecipes()) {
					if (!output.equalsFluid(TweakerItem.get(recipe.result.asItemStack()))) continue;
					if (!input1.equalsFluid(TweakerItem.get(recipe.ingredient1.asItemStack()))) continue;
					if (input2 == null) {
						if (recipe.ingredient2 != null) continue;
					} else {
						if (!input2.equalsFluid(TweakerItem.get(recipe.ingredient2.asItemStack()))) continue;
					}
					actions.add(new RefineryRemoveRecipeAction(recipe));
				}
				//#else
				//+for (Recipe recipe : BuildCraftUtil.getRefineryRecipes()) {
					//+if (!output.equalsFluid(recipe.result.getFluid())) continue;
					//+if (!input1.equalsFluid(recipe.ingredient1.getFluid())) continue;
					//+if (input2 == null) {
						//+if (recipe.ingredient2 != null) continue;
					//+} else {
						//+if (!input2.equalsFluid(recipe.ingredient2.getFluid())) continue;
					//+}
					//+actions.add(new RefineryRemoveRecipeAction(recipe));
				//+}
				//#endif
				for (RefineryRemoveRecipeAction action : actions) {
					Tweaker.apply(action);
				}
				return null;
			}
		}
	}

	@Override
	public String toString() {
		return "buildcraft.refinery.removeRecipe";
	}
}
