/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.actions;

import buildcraft.api.fuels.IronEngineFuel;
//#ifndef MC152
import buildcraft.api.fuels.IronEngineFuel.Fuel;
import net.minecraftforge.fluids.Fluid;
//#endif
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class RemoveFuelAction implements IUndoableAction {
	//#ifdef MC152
	//+private IronEngineFuel fuel;
	
	//+public RemoveFuelAction(IronEngineFuel fuel) {
		//+this.fuel = fuel;
	//+}
	//#else
	private Fluid fluid;
	private Fuel fuel;
	
	public RemoveFuelAction(Fluid fluid) {
		this.fluid = fluid;
		fuel = IronEngineFuel.getFuelForFluid(fluid);
	}
	//#endif

	public void apply() {
		//#ifdef MC152
		//+IronEngineFuel.fuels.remove(fuel);
		//#else
		IronEngineFuel.fuels.remove(fluid.getName());
		//#endif
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifdef MC152
		//+IronEngineFuel.fuels.add(fuel);
		//#else
		IronEngineFuel.fuels.put(fluid.getName(), fuel);
		//#endif
	}

	public String describe() {
		//#ifdef MC152
		//+return "Removing fuel " + fuel.liquid.asItemStack().getDisplayName();
		//#else
		return "Removing fuel " + fluid.getName();
		//#endif
	}

	public String describeUndo() {
		//#ifdef MC152
		//+return "Restoring fuel " + fuel.liquid.asItemStack().getDisplayName();
		//#else
		return "Restoring fuel " + fluid.getName();
		//#endif
	}
}
