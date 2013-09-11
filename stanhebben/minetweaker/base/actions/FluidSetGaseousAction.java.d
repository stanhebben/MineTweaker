//#fileifndef MC152
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.base.actions;

import net.minecraftforge.fluids.Fluid;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class FluidSetGaseousAction implements IUndoableAction {
	private Fluid fluid;
	private boolean newValue;
	private boolean oldValue;
	
	public FluidSetGaseousAction(Fluid fluid, boolean newValue) {
		this.fluid = fluid;
		this.newValue = newValue;
		this.oldValue = fluid.isGaseous();
	}

	public void apply() {
		fluid.setGaseous(newValue);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		fluid.setGaseous(oldValue);
	}

	public String describe() {
		return "Setting " + fluid.getLocalizedName() + " gaseous value to " + newValue;
	}

	public String describeUndo() {
		return "Restoring " + fluid.getLocalizedName() + " gaseous value to " + oldValue;
	}
}
