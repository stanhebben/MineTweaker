/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.fuels.GeneratorFuel;
import java.util.logging.Level;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.value.TweakerFluid;

/**
 *
 * @author Stanneke
 */
public class GeneratorRemoveFuelAction implements IUndoableAction {
	private final TweakerFluid fluid;
	private GeneratorFuel fuel;
	
	public GeneratorRemoveFuelAction(TweakerFluid fluid) {
		this.fluid = fluid;
	}

	public void apply() {
		//#ifdef MC152
		//+fuel = GeneratorFuel.fuels.remove(fluid.get().getItemId());
		//#else
		fuel = GeneratorFuel.fuels.remove(fluid.get().getID());
		//#endif
		if (fuel == null) Tweaker.log(Level.WARNING, "Could not find biogenetator fuel " + fluid.getDisplayName());
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifdef MC152
		//+if (fuel != null) GeneratorFuel.fuels.put(fluid.get().getItemId(), fuel);
		//#else
		if (fuel != null) GeneratorFuel.fuels.put(fluid.get().getID(), fuel);
		//#endif
	}

	public String describe() {
		return "Removing bio generator fuel " + fluid.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring bio generator fuel " + fluid.getDisplayName();
	}
}
