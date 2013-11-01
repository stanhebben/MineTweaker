/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.recipes.RecipeManagers;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerLiquid;

/**
 *
 * @author Stanneke
 */
public class StillAddRecipeAction implements IUndoableAction {
	private final TweakerLiquid input;
	private final TweakerLiquid output;
	private final int cyclesPerUnit;
	
	public StillAddRecipeAction(TweakerLiquid output, TweakerLiquid input, int cyclesPerUnit) {
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
