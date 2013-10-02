//#fileifndef OLDLIQUIDS
package stanhebben.minetweaker.base.actions;

import net.minecraftforge.fluids.Fluid;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class FluidSetLuminosityAction implements IUndoableAction {
	private Fluid fluid;
	private int newValue;
	private int oldValue;
	
	public FluidSetLuminosityAction(Fluid fluid, int newValue) {
		this.fluid = fluid;
		this.newValue = newValue;
		this.oldValue = fluid.getLuminosity();
	}

	public void apply() {
		fluid.setLuminosity(newValue);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		fluid.setLuminosity(oldValue);
	}

	public String describe() {
		return "Setting " + fluid.getLocalizedName() + " luminosity to " + newValue;
	}

	public String describeUndo() {
		return "Restoring " + fluid.getLocalizedName() + " luminosity to " + oldValue;
	}
}
