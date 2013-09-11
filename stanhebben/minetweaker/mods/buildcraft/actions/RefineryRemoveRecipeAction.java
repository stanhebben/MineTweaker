/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.actions;

//#ifdef MC152
import buildcraft.api.recipes.RefineryRecipe;
//#else
//+import buildcraft.api.recipes.RefineryRecipes.Recipe;
//#endif
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.mods.buildcraft.BuildCraftUtil;

/**
 *
 * @author Stanneke
 */
public class RefineryRemoveRecipeAction implements IUndoableAction {
	//#ifdef MC152
	private RefineryRecipe recipe;
	
	public RefineryRemoveRecipeAction(RefineryRecipe recipe) {
		this.recipe = recipe;
	}
	//#else
	//+private Recipe recipe;
	
	//+public RefineryRemoveRecipeAction(Recipe recipe) {
		//+this.recipe = recipe;
	//+}
	//#endif

	public void apply() {
		BuildCraftUtil.getRefineryRecipes().remove(recipe);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		BuildCraftUtil.getRefineryRecipes().add(recipe);
	}

	public String describe() {
		//#ifdef MC152
		return "Removing a refinery recipe for " + recipe.result.asItemStack().getDisplayName();
		//#else
		//+return "Removing a refinery recipe for " + recipe.result.getFluid().getLocalizedName();
		//#endif
	}

	public String describeUndo() {
		//#ifdef MC152
		return "Restoring a refinery recipe for " + recipe.result.asItemStack().getDisplayName();
		//#else
		//+return "Restoring a refinery recipe for " + recipe.result.getFluid().getLocalizedName();
		//#endif
	}
}
