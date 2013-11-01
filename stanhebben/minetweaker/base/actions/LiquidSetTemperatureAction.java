//#fileifndef OLDLIQUIDS
package stanhebben.minetweaker.base.actions;

import net.minecraftforge.fluids.Fluid;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 * Implements liquid.setTemperature.
 * 
 * @author Stan Hebben
 */
public final class LiquidSetTemperatureAction implements IUndoableAction {
	private final Fluid fluid;
	private final int newValue;
	private final int oldValue;
	
	public LiquidSetTemperatureAction(Fluid fluid, int newValue) {
		this.fluid = fluid;
		this.newValue = newValue;
		this.oldValue = fluid.getTemperature();
	}

	@Override
	public void apply() {
		fluid.setTemperature(newValue);
	}

	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		fluid.setTemperature(oldValue);
	}

	@Override
	public String describe() {
		return "Setting " + fluid.getLocalizedName() + " temperature to " + newValue;
	}

	@Override
	public String describeUndo() {
		return "Restoring " + fluid.getLocalizedName() + " temperature to " + oldValue;
	}
}
