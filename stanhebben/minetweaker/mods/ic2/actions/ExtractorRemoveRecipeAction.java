//#fileifdef MC152
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.ic2.actions;

import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class ExtractorRemoveRecipeAction implements IUndoableAction {
	private ItemStack input;
	private ItemStack output;
	
	public ExtractorRemoveRecipeAction(ItemStack input) {
		this.input = input;
		output = Recipes.extractor.getRecipes().get(input);
	}

	public void apply() {
		Recipes.extractor.getRecipes().remove(input);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		Recipes.extractor.getRecipes().put(input, output);
	}

	public String describe() {
		return "Removing an extractor recipe for " + output.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring an extractor recipe for " + output.getDisplayName();
	}
}
