//#fileifdef MC152
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.ic2.actions;

import ic2.api.recipe.Recipes;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;

/**
 *
 * @author Stanneke
 */
public class CompressorAddRecipeAction implements IUndoableAction {
	private TweakerItemStack output;
	private TweakerItem input;
	
	public CompressorAddRecipeAction(TweakerItemStack output, TweakerItem input) {
		this.output = output;
		this.input = input;
	}

	public void apply() {
		Recipes.compressor.addRecipe(input.make(1), output.get());
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		// because I'm a mad evil scientist
		Recipes.compressor.getRecipes().remove(input.make(1));
	}

	public String describe() {
		return "Adding a compressor recipe for " + output.get().getDisplayName();
	}

	public String describeUndo() {
		return "Removing a compressor recipe for " + output.get().getDisplayName();
	}
}
