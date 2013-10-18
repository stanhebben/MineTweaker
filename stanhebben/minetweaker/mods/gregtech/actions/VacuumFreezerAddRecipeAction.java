/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech.actions;

import gregtechmod.api.GregTech_API;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItemStack;

/**
 *
 * @author Stanneke
 */
public class VacuumFreezerAddRecipeAction implements IUndoableAction {
	private final TweakerItemStack input;
	private final TweakerItemStack output;
	private final int duration;
	
	public VacuumFreezerAddRecipeAction(TweakerItemStack output, TweakerItemStack input, int duration) {
		this.input = input;
		this.output = output;
		this.duration = duration;
	}

	public void apply() {
		GregTech_API.sRecipeAdder.addVacuumFreezerRecipe(input.get(), output.get(), duration);
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding a vacuum freezer recipe for " + output.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
