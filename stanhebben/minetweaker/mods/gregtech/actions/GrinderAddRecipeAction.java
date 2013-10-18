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
public class GrinderAddRecipeAction implements IUndoableAction {
	private final TweakerItemStack input1;
	private final TweakerItemStack input2;
	private final TweakerItemStack output1;
	private final TweakerItemStack output2;
	private final TweakerItemStack output3;
	private final TweakerItemStack output4;
	
	public GrinderAddRecipeAction(
			TweakerItemStack output1, TweakerItemStack output2, TweakerItemStack output3, TweakerItemStack output4,
			TweakerItemStack input1, TweakerItemStack input2) {
		this.input1 = input1;
		this.input2 = input2;
		this.output1 = output1;
		this.output2 = output2;
		this.output3 = output3;
		this.output4 = output4;
	}

	public void apply() {
		GregTech_API.sRecipeAdder.addGrinderRecipe(
				input1.get(),
				input2 == null ? null : input2.get(),
				output1.get(),
				output2 == null ? null : output2.get(),
				output3 == null ? null : output3.get(),
				output4 == null ? null : output4.get());
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding a grinder recipe to process " + input1.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
