/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerFluid;

/**
 *
 * @author Stanneke
 */
public class BronzeEngineAddFuelAction implements IUndoableAction {
	private final TweakerFluid fluid;
	private final int powerPerCycle;
	private final int burnDuration;
	private final int dissipationMultiplier;
	
	public BronzeEngineAddFuelAction(TweakerFluid fluid, int powerPerCycle, int burnDuration, int dissipationMultiplier) {
		this.fluid = fluid;
		this.powerPerCycle = powerPerCycle;
		this.burnDuration = burnDuration;
		this.dissipationMultiplier = dissipationMultiplier;
	}

	public void apply() {
		//#ifdef MC152
		//+FuelManager.bronzeEngineFuel.put(fluid.get().make(1), new EngineBronzeFuel(fluid.get().make(1), powerPerCycle, burnDuration, dissipationMultiplier));
		//#else
		FuelManager.bronzeEngineFuel.put(fluid.get(), new EngineBronzeFuel(fluid.get(), powerPerCycle, burnDuration, dissipationMultiplier));
		//#endif
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifdef MC152
		//+FuelManager.bronzeEngineFuel.remove(fluid.get().make(1));
		//#else
		FuelManager.bronzeEngineFuel.remove(fluid.get());
		//#endif
	}

	public String describe() {
		return "Adding biogas engine fuel " + fluid.getDisplayName();
	}

	public String describeUndo() {
		return "Removing biogas engine fuel " + fluid.getDisplayName();
	}
}
