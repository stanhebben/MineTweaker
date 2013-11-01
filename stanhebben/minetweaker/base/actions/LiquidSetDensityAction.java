//#fileifndef OLDLIQUIDS
package stanhebben.minetweaker.base.actions;

import net.minecraftforge.fluids.Fluid;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 * Implements liquid.setDensity.
 * 
 * @author Stan Hebben
 */
public final class LiquidSetDensityAction implements IUndoableAction {
	private final Fluid fluid;
	private final int newValue;
	private final int oldValue;
	
	public LiquidSetDensityAction(Fluid fluid, int newValue) {
		this.fluid = fluid;
		this.newValue = newValue;
		this.oldValue = fluid.getDensity();
	}

	public void apply() {
		fluid.setDensity(newValue);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		fluid.setDensity(oldValue);
	}

	public String describe() {
		return "Setting " + fluid.getLocalizedName() + " density to " + newValue;
	}

	public String describeUndo() {
		return "Restoring " + fluid.getLocalizedName() + " density to " + oldValue;
	}
}
