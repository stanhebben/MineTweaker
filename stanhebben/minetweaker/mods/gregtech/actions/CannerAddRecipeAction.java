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
public class CannerAddRecipeAction implements IUndoableAction {
	private final TweakerItemStack input1;
	private final TweakerItemStack input2;
	private final TweakerItemStack output1;
	private final TweakerItemStack output2;
	private final int duration;
	private final int euPerTick;
	
	public CannerAddRecipeAction(
			TweakerItemStack output1, TweakerItemStack output2,
			TweakerItemStack input1, TweakerItemStack input2,
			int duration, int euPerTick) {
		this.input1 = input1;
		this.input2 = input2;
		this.output1 = output1;
		this.output2 = output2;
		this.duration = duration;
		this.euPerTick = euPerTick;
	}

	public void apply() {
		GregTech_API.sRecipeAdder.addCannerRecipe(
				input1.get(),
				input2 == null ? null : input2.get(),
				output1.get(),
				output2 == null ? null : output2.get(),
				duration,
				euPerTick);
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding a canner recipe for " + output1.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
