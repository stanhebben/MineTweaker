/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech.actions;

import gregtechmod.api.GregTech_API;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;

/**
 *
 * @author Stanneke
 */
public class ElectrolyzerAddRecipeAction implements IUndoableAction {
	private final TweakerItemStack input;
	private final TweakerItemStack output1;
	private final TweakerItemStack output2;
	private final TweakerItemStack output3;
	private final TweakerItemStack output4;
	private final int numCells;
	private final int duration;
	private final int euPerTick;
	
	public ElectrolyzerAddRecipeAction(
			TweakerItemStack output1, TweakerItemStack output2, TweakerItemStack output3, TweakerItemStack output4,
			TweakerItemStack input, int numCells, int duration, int euPerTick) {
		this.output1 = output1;
		this.output2 = output2;
		this.output3 = output3;
		this.output4 = output4;
		this.input = input;
		this.numCells = numCells;
		this.duration = duration;
		this.euPerTick = euPerTick;
	}

	public void apply() {
		GregTech_API.sRecipeAdder.addElectrolyzerRecipe(
				input.get(),
				numCells,
				output1.get(),
				output2 == null ? null : output2.get(),
				output3 == null ? null : output3.get(),
				output4 == null ? null : output4.get(),
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
		return "Adding an electrolyzer recipe processing " + input.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
