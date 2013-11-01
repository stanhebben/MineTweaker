//#fileifndef OLDLIQUIDS
package stanhebben.minetweaker.base.actions;

import net.minecraftforge.fluids.Fluid;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 * Implements liquid.setLuminosity.
 * 
 * @author Stan Hebben
 */
public final class LiquidSetLuminosityAction implements IUndoableAction {
	private final Fluid fluid;
	private final int newValue;
	private final int oldValue;
	
	public LiquidSetLuminosityAction(Fluid fluid, int newValue) {
		this.fluid = fluid;
		this.newValue = newValue;
		this.oldValue = fluid.getLuminosity();
	}

	@Override
	public void apply() {
		fluid.setLuminosity(newValue);
	}

	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		fluid.setLuminosity(oldValue);
	}

	@Override
	public String describe() {
		return "Setting " + fluid.getLocalizedName() + " luminosity to " + newValue;
	}

	@Override
	public String describeUndo() {
		return "Restoring " + fluid.getLocalizedName() + " luminosity to " + oldValue;
	}
}
