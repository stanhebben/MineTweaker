/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.actions;

import buildcraft.api.fuels.IronEngineFuel;
//#ifdef MC152
import net.minecraftforge.liquids.LiquidStack;
//#else
//+import buildcraft.api.fuels.IronEngineFuel.Fuel;
//+import net.minecraftforge.fluids.Fluid;
//#endif
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class SetFuelAction implements IUndoableAction {
	//#ifdef MC152
	private IronEngineFuel newFuel;
	private IronEngineFuel oldFuel;
	
	public SetFuelAction(LiquidStack liquid, float powerPerCycle, int numTotalCycles) {
		newFuel = new IronEngineFuel(liquid, powerPerCycle, numTotalCycles);
	}
	//#else
	//+private Fluid fluid;
	//+private float powerPerCycle;
	//+private int numTotalCycles;
	//+private Fuel old;
	
	//+public SetFuelAction(Fluid fluid, float powerPerCycle, int numTotalCycles) {
		//+this.fluid = fluid;
		//+this.powerPerCycle = powerPerCycle;
		//+this.numTotalCycles = numTotalCycles;
		//+old = IronEngineFuel.fuels.get(fluid.getName());
	//+}
	//#endif

	public void apply() {
		//#ifdef MC152
		for (IronEngineFuel fuel : IronEngineFuel.fuels) {
			if (fuel.liquid.itemID == newFuel.liquid.itemID
					&& fuel.liquid.itemMeta == newFuel.liquid.itemMeta) {
				oldFuel = fuel;
			}
		}
		if (oldFuel != null) {
			IronEngineFuel.fuels.remove(oldFuel);
		}
		IronEngineFuel.fuels.add(newFuel);
		//#else
		//+IronEngineFuel.fuels.remove(fluid.getName());
		//+IronEngineFuel.addFuel(fluid, powerPerCycle, numTotalCycles);
		//#endif
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifdef MC152
		IronEngineFuel.fuels.remove(newFuel);
		if (oldFuel != null) IronEngineFuel.fuels.add(oldFuel);
		//#else
		//+IronEngineFuel.fuels.put(fluid.getName(), old);
		//#endif
	}

	public String describe() {
		//#ifdef MC152
		return "Altering fuel " + newFuel.liquid.asItemStack().getDisplayName();
		//#else
		//+return "Altering fuel " + fluid.getLocalizedName();
		//#endif
	}

	public String describeUndo() {
		//#ifdef MC152
		return "Restoring fuel " + newFuel.liquid.asItemStack().getDisplayName();
		//#else
		//+return "Restoring fuel " + fluid.getLocalizedName();
		//#endif
	}
}
