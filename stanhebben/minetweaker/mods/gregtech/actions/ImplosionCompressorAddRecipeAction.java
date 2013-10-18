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
public class ImplosionCompressorAddRecipeAction implements IUndoableAction {
	private final TweakerItemStack input;
	private final int itntAmount;
	private final TweakerItemStack output1;
	private final TweakerItemStack output2;
	
	public ImplosionCompressorAddRecipeAction(
			TweakerItemStack output1, TweakerItemStack output2,
			TweakerItemStack input, int itntAmount) {
		this.input = input;
		this.itntAmount = itntAmount;
		this.output1 = output1;
		this.output2 = output2;
	}

	public void apply() {
		GregTech_API.sRecipeAdder.addImplosionRecipe(
				input.get(),
				itntAmount,
				output1.get(),
				output2 == null ? null : output2.get());
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding an implosion compressor recipe for " + output1.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
