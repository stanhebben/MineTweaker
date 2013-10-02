/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.actions;

//#ifndef MC152
import buildcraft.api.core.StackWrapper;
import net.minecraftforge.fluids.FluidStack;
import buildcraft.api.fuels.IronEngineCoolant;
//#endif
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class RemoveSolidCoolantAction implements IUndoableAction {
	//#ifndef MC152
	private StackWrapper stack;
	private FluidStack fluid;
	
	public RemoveSolidCoolantAction(StackWrapper stack) {
		this.stack = stack;
		fluid = (FluidStack) IronEngineCoolant.solidCoolants.get(stack);
	}
	//#endif

	public void apply() {
		//#ifndef MC152
		IronEngineCoolant.solidCoolants.remove(stack);
		//#endif
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifndef MC152
		IronEngineCoolant.solidCoolants.put(stack, fluid);
		//#endif
	}

	public String describe() {
		//#ifdef MC152
		//+return "NOP";
		//#else
		return "Removing coolant item " + stack.stack.getDisplayName();
		//#endif
	}

	public String describeUndo() {
		//#ifdef MC152
		//+return "NOP";
		//#else
		return "Restoring coolant item " + stack.stack.getDisplayName();
		//#endif
	}
}
