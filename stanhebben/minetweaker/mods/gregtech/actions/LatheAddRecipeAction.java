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
public class LatheAddRecipeAction implements IUndoableAction {
	private final TweakerItemStack input;
	private final TweakerItemStack output1;
	private final TweakerItemStack output2;
	private final int duration;
	private final int euPerTick;
	
	public LatheAddRecipeAction(
			TweakerItemStack output1, TweakerItemStack output2,
			TweakerItemStack input,
			int duration, int euPerTick) {
		this.input = input;
		this.output1 = output1;
		this.output2 = output2;
		this.duration = duration;
		this.euPerTick = euPerTick;
	}

	public void apply() {
		GregTech_API.sRecipeAdder.addLatheRecipe(
				input.get(),
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
		return "Adding a lathe recipe for " + output1.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
