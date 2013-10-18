/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.recipes.RecipeManagers;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;

/**
 *
 * @author Stanneke
 */
public class MoistenerAddRecipeAction implements IUndoableAction {
	private final TweakerItemStack output;
	private final TweakerItem input;
	private final int time;
	
	public MoistenerAddRecipeAction(TweakerItemStack output, TweakerItem input, int time) {
		this.output = output;
		this.input = input;
		this.time = time;
	}

	public void apply() {
		RecipeManagers.moistenerManager.addRecipe(input.make(1), output.get(), time);
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding a moistener recipe for " + output.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
