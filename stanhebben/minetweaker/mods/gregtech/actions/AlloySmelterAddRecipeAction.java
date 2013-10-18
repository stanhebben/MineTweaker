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
public class AlloySmelterAddRecipeAction implements IUndoableAction {
	private final TweakerItemStack input1;
	private final TweakerItemStack input2;
	private final TweakerItemStack output;
	private final int duration;
	private final int euPerTick;
	
	public AlloySmelterAddRecipeAction(
			TweakerItemStack output,
			TweakerItemStack input1, TweakerItemStack input2,
			int duration, int euPerTick) {
		this.input1 = input1;
		this.input2 = input2;
		this.output = output;
		this.duration = duration;
		this.euPerTick = euPerTick;
	}

	public void apply() {
		GregTech_API.sRecipeAdder.addAlloySmelterRecipe(
				input1.get(),
				input2 == null ? null : input2.get(),
				output.get(),
				duration, euPerTick);
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding an alloy smelter recipe for " + output.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
