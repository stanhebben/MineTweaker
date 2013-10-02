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
public class CompressorRemoveRecipeAction implements IUndoableAction {
	private ItemStack input;
	private ItemStack output;
	
	public CompressorRemoveRecipeAction(ItemStack input) {
		this.input = input;
		output = Recipes.compressor.getRecipes().get(input);
	}

	public void apply() {
		Recipes.compressor.getRecipes().remove(input);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		Recipes.compressor.getRecipes().put(input, output);
	}

	public String describe() {
		return "Removing a compressor recipe for " + output.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring a compressor recipe for " + output.getDisplayName();
	}
}
