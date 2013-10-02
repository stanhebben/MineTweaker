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
public class AssemblyTableRemoveRecipeAction implements IUndoableAction {
	private int index;
	private AssemblyRecipe value;
	
	public AssemblyTableRemoveRecipeAction(int index) {
		this.index = index;
		value = (AssemblyRecipe) AssemblyRecipe.assemblyRecipes.get(index);
	}

	public void apply() {
		AssemblyRecipe.assemblyRecipes.remove(index);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		AssemblyRecipe.assemblyRecipes.add(index, value);
	}

	public String describe() {
		return "Removing an assembly table recipe for " + value.output.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring an assembly table recipe for " + value.output.getDisplayName();
	}
}
