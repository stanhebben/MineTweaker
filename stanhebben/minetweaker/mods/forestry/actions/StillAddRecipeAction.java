/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.recipes.RecipeManagers;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerFluid;

/**
 *
 * @author Stanneke
 */
public class StillAddRecipeAction implements IUndoableAction {
	private final TweakerFluid input;
	private final TweakerFluid output;
	private final int cyclesPerUnit;
	
	public StillAddRecipeAction(TweakerFluid output, TweakerFluid input, int cyclesPerUnit) {
		this.input = input;
		this.output = output;
		this.cyclesPerUnit = cyclesPerUnit;
	}

	public void apply() {
		RecipeManagers.stillManager.addRecipe(cyclesPerUnit, input.make(1), output.make(1));
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding a still recipe for " + output.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
