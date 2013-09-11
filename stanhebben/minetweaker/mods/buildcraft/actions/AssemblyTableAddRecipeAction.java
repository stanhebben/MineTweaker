/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.actions;

import buildcraft.api.recipes.AssemblyRecipe;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class AssemblyTableAddRecipeAction implements IUndoableAction {
	private AssemblyRecipe recipe;
	
	public AssemblyTableAddRecipeAction(AssemblyRecipe recipe) {
		this.recipe = recipe;
	}
	
	public void apply() {
		AssemblyRecipe.assemblyRecipes.add(recipe);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		AssemblyRecipe.assemblyRecipes.remove(AssemblyRecipe.assemblyRecipes.size() - 1);
	}

	public String describe() {
		return "Adding an assembly table recipe for " + recipe.output.getDisplayName();
	}

	public String describeUndo() {
		return "Removing an assembly table recipe for " + recipe.output.getDisplayName();
	}
}
