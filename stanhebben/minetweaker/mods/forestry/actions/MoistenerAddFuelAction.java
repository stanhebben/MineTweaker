/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.fuels.FuelManager;
import forestry.api.fuels.MoistenerFuel;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;

/**
 *
 * @author Stanneke
 */
public class MoistenerAddFuelAction implements IUndoableAction {
	private final TweakerItem input;
	private final TweakerItem output;
	private final int value;
	private final int stage;
	
	public MoistenerAddFuelAction(TweakerItem output, TweakerItem input, int value, int stage) {
		this.input = input;
		this.output = output;
		this.value = value;
		this.stage = stage;
	}

	public void apply() {
		FuelManager.moistenerResource.put(input.make(1), new MoistenerFuel(input.make(1), output.make(1), value, stage));
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		FuelManager.moistenerResource.remove(input.make(1));
	}

	public String describe() {
		return "Adding moistener resource " + input.getDisplayName();
	}

	public String describeUndo() {
		return "Removing moistener resource " + input.getDisplayName();
	}
}
