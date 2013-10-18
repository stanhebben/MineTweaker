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
public class ChemicalAddRecipeAction implements IUndoableAction {
	private final TweakerItemStack input1;
	private final TweakerItemStack input2;
	private final TweakerItemStack output;
	private final int duration;
	
	public ChemicalAddRecipeAction(
			TweakerItemStack output,
			TweakerItemStack input1, TweakerItemStack input2, int duration) {
		this.output = output;
		this.input1 = input1;
		this.input2 = input2;
		this.duration = duration;
	}

	public void apply() {
		GregTech_API.sRecipeAdder.addChemicalRecipe(input1.get(), input2.get(), output.get(), duration);
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding chemical recipe for " + output.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
