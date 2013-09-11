/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.functions;

//#ifdef MC152
import buildcraft.api.recipes.RefineryRecipe;
import stanhebben.minetweaker.api.value.TweakerItem;
//#else
//+import buildcraft.api.recipes.RefineryRecipes;
//#endif
import java.util.ArrayList;
import java.util.logging.Level;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFluid;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import static stanhebben.minetweaker.api.value.TweakerValue.notNull;
import stanhebben.minetweaker.mods.buildcraft.BuildCraftUtil;
import stanhebben.minetweaker.mods.buildcraft.actions.RefineryRemoveRecipeAction;

/**
 *
 * @author Stanneke
 */
public class RefineryRemoveFunction extends TweakerFunction {
	public static final RefineryRemoveFunction INSTANCE = new RefineryRemoveFunction();
	
	private RefineryRemoveFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("refinery.removeRecipe requires at least one argument");
		} else {
			TweakerFluid output =
					notNull(arguments[0], "the refinery.remove output cannot be null")
					.toFluid("the refinery.remove output must be a fluid");
			
			if (BuildCraftUtil.getRefineryRecipes() == null) {
				Tweaker.log(Level.WARNING, "Could not execute the refinery recipe removed due to reflection failing. Operation ignored.");
				return null;
			} else {
				ArrayList<RefineryRemoveRecipeAction> actions = new ArrayList<RefineryRemoveRecipeAction>();
				//#ifdef MC152
				for (RefineryRecipe recipe : BuildCraftUtil.getRefineryRecipes()) {
					if (!output.equalsFluid(TweakerItem.get(recipe.result.asItemStack()))) continue;
					actions.add(new RefineryRemoveRecipeAction(recipe));
				}
				//#else
				//+for (RefineryRecipes.Recipe recipe : BuildCraftUtil.getRefineryRecipes()) {
					//+if (!output.equalsFluid(recipe.result.getFluid())) continue;
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
		return "buildcraft.refinery.remove";
	}
}
