/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.fuels.GeneratorFuel;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerLiquidStack;

/**
 *
 * @author Stanneke
 */
public class GeneratorAddFuelAction implements IUndoableAction {
	private final TweakerLiquidStack fluid;
	private final GeneratorFuel fuel;
	
	public GeneratorAddFuelAction(TweakerLiquidStack fluidPerCycle, int euPerTick, int numTicks) {
		fluid = fluidPerCycle;
		fuel = new GeneratorFuel(fluidPerCycle.get(), euPerTick, numTicks);
	}

	public void apply() {
		//#ifdef MC152
		//+GeneratorFuel.fuels.put(fuel.fuelConsumed.itemID, fuel);
		//#else
		GeneratorFuel.fuels.put(fuel.fuelConsumed.fluidID, fuel);
		//#endif
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifdef MC152
		//+GeneratorFuel.fuels.remove(fuel.fuelConsumed.itemID);
		//#else
		GeneratorFuel.fuels.remove(fuel.fuelConsumed.fluidID);
		//#endif
	}

	public String describe() {
		return "Adding bio generator fuel " + fluid.getDisplayName();
	}

	public String describeUndo() {
		return "Removing bio generator fuel " + fluid.getDisplayName();
	}
}
