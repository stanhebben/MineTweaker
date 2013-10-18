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
public class GeneratorAddFuelAction implements IUndoableAction {
	private final int type;
	private final TweakerItemStack input;
	private final int euPerMB;
	private final TweakerItemStack output;
	
	public GeneratorAddFuelAction(int type, TweakerItemStack input, int euPerMB, TweakerItemStack output) {
		this.type = type;
		this.input = input;
		this.euPerMB = euPerMB;
		this.output = output;
	}

	public void apply() {
		GregTech_API.sRecipeAdder.addFuel(input.get(), output == null ? null : output.get(), euPerMB, type);
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding fuel " + input.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
