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
public class FluidSetDensityAction implements IUndoableAction {
	private Fluid fluid;
	private int newValue;
	private int oldValue;
	
	public FluidSetDensityAction(Fluid fluid, int newValue) {
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
