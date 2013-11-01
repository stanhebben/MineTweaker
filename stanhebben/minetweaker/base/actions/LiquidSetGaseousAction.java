//#fileifndef OLDLIQUIDS
package stanhebben.minetweaker.base.actions;

import net.minecraftforge.fluids.Fluid;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 * Implements liquid.setGaseous .
 * 
 * @author Stan Hebben
 */
public final class LiquidSetGaseousAction implements IUndoableAction {
	private final Fluid fluid;
	private final boolean newValue;
	private final boolean oldValue;
	
	public LiquidSetGaseousAction(Fluid fluid, boolean newValue) {
		this.fluid = fluid;
		this.newValue = newValue;
		this.oldValue = fluid.isGaseous();
	}

	@Override
	public void apply() {
		fluid.setGaseous(newValue);
	}

	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		fluid.setGaseous(oldValue);
	}

	@Override
	public String describe() {
		return "Setting " + fluid.getLocalizedName() + " gaseous value to " + newValue;
	}

	@Override
	public String describeUndo() {
		return "Restoring " + fluid.getLocalizedName() + " gaseous value to " + oldValue;
	}
}
