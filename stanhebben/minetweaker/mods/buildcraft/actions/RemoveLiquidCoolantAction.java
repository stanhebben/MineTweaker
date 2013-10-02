/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.actions;

import buildcraft.api.fuels.IronEngineCoolant;
//#ifdef MC152
//#else
import buildcraft.api.fuels.IronEngineCoolant.Coolant;
import net.minecraftforge.fluids.Fluid;
//#endif
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class RemoveLiquidCoolantAction implements IUndoableAction {
	//#ifdef MC152
	//+private IronEngineCoolant coolant;
	
	//+public RemoveLiquidCoolantAction(IronEngineCoolant coolant) {
		//+this.coolant = coolant;
	//+}
	//#else
	private Fluid fluid;
	private Coolant coolant;
	
	public RemoveLiquidCoolantAction(Fluid fluid) {
		this.fluid = fluid;
		coolant = (IronEngineCoolant.Coolant) IronEngineCoolant.liquidCoolants.get(fluid.getName());
	}
	//#endif
	
	public void apply() {
		//#ifdef MC152
		//+IronEngineCoolant.coolants.remove(coolant);
		//#else
		IronEngineCoolant.liquidCoolants.remove(fluid.getName());
		//#endif
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifdef MC152
		//#else
		IronEngineCoolant.liquidCoolants.put(fluid.getName(), coolant);
		//#endif
	}

	public String describe() {
		//#ifdef MC152
		//+return "Removing coolant liquid " + coolant.liquid.asItemStack().getDisplayName();
		//#else
		return "Removing coolant liquid " + fluid.getName();
		//#endif
	}

	public String describeUndo() {
		//#ifdef MC152
		//+return "Restoring coolant liquid " + coolant.liquid.asItemStack().getDisplayName();
		//#else
		return "Restoring coolant liquid " + fluid.getName();
		//#endif
	}
}
