/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.fuels.EngineCopperFuel;
import forestry.api.fuels.FuelManager;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;

/**
 *
 * @author Stanneke
 */
public class CopperEngineAddFuelAction implements IUndoableAction {
	private final EngineCopperFuel fuel;
	
	public CopperEngineAddFuelAction(TweakerItem item, int powerPerCycle, int burnDuration) {
		fuel = new EngineCopperFuel(item.make(1), powerPerCycle, burnDuration);
	}

	public void apply() {
		FuelManager.copperEngineFuel.put(fuel.fuel, fuel);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		FuelManager.copperEngineFuel.remove(fuel.fuel);
	}

	public String describe() {
		return "Adding peat fired engine fuel " + fuel.fuel.getDisplayName();
	}

	public String describeUndo() {
		return "Removing peat fired engine fuel " + fuel.fuel.getDisplayName();
	}
}
