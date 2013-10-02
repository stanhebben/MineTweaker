/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.actions;

import buildcraft.api.fuels.IronEngineFuel;
//#ifdef MC152
//+import net.minecraftforge.liquids.LiquidStack;
//#endif
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerFluid;

/**
 *
 * @author Stanneke
 */
public class AddFuelAction implements IUndoableAction {
	//#ifdef MC152
	//+private IronEngineFuel fuel;
	//#else
	private TweakerFluid fluid;
	private float powerPerCycle;
	private int numTotalCycles;
	//#endif
	
	public AddFuelAction(TweakerFluid fluid, float powerPerCycle, int numTotalCycles) {
		//#ifdef MC152
		//+fuel = new IronEngineFuel(
				//+new LiquidStack(fluid.get().getItemId(), 1, fluid.get().getItemSubId()),
				//+powerPerCycle,
				//+numTotalCycles);
		//#else
		this.fluid = fluid;
		this.powerPerCycle = powerPerCycle;
		this.numTotalCycles = numTotalCycles;
		//#endif
	}

	public void apply() {
		//#ifdef MC152
		//+IronEngineFuel.fuels.add(fuel);
		//#else
		IronEngineFuel.addFuel(fluid.get(), powerPerCycle, numTotalCycles);
		//#endif
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifdef MC152
		//+IronEngineFuel.fuels.remove(fuel);
		//#else
		IronEngineFuel.fuels.remove(fluid.getName());
		//#endif
	}

	public String describe() {
		//#ifdef MC152
		//+return "Adding a fuel for " + fuel.liquid.asItemStack().getDisplayName();
		//#else
		return "Adding a fuel for " + fluid.getDisplayName();
		//#endif
	}

	public String describeUndo() {
		//#ifdef MC152
		//+return "Removing a fuel for " + fuel.liquid.asItemStack().getDisplayName();
		//#else
		return "Removing a fuel for " + fluid.getDisplayName();
		//#endif
	}
}
