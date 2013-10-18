//#fileifndef MC152
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
public class CutterAddRecipeAction implements IUndoableAction {
	private final TweakerItemStack input;
	private final TweakerItemStack output;
	private final int duration;
	private final int euPerTick;
	
	public CutterAddRecipeAction(TweakerItemStack output, TweakerItemStack input, int duration, int euPerTick) {
		this.input = input;
		this.output = output;
		this.duration = duration;
		this.euPerTick = euPerTick;
	}

	public void apply() {
		GregTech_API.sRecipeAdder.addCutterRecipe(input.get(), output.get(), duration, euPerTick);
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding cutter recipe for " + output.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
