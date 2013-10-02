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
public class ExtractorAddRecipeAction implements IUndoableAction {
	private TweakerItemStack output;
	private TweakerItem input;
	
	public ExtractorAddRecipeAction(TweakerItemStack output, TweakerItem input) {
		this.output = output;
		this.input = input;
	}

	public void apply() {
		Recipes.extractor.addRecipe(input.make(1), output.get());
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		// because I'm a mad evil scientist
		Recipes.extractor.getRecipes().remove(input.make(1));
	}

	public String describe() {
		return "Adding an extractor recipe for " + output.get().getDisplayName();
	}

	public String describeUndo() {
		return "Removing an extractor recipe for " + output.get().getDisplayName();
	}
}
