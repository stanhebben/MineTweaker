//#fileifndef OLDLIQUIDS
package stanhebben.minetweaker.base.actions;

import net.minecraftforge.fluids.Fluid;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class FluidSetTemperatureAction implements IUndoableAction {
	private Fluid fluid;
	private int newValue;
	private int oldValue;
	
	public FluidSetTemperatureAction(Fluid fluid, int newValue) {
		this.fluid = fluid;
		this.newValue = newValue;
		this.oldValue = fluid.getTemperature();
	}

	public void apply() {
		fluid.setTemperature(newValue);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		fluid.setTemperature(oldValue);
	}

	public String describe() {
		return "Setting " + fluid.getLocalizedName() + " temperature to " + newValue;
	}

	public String describeUndo() {
		return "Restoring " + fluid.getLocalizedName() + " temperature to " + oldValue;
	}
}
