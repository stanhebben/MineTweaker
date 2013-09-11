/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.actions;

import buildcraft.api.fuels.IronEngineCoolant;
//#ifdef MC152
import stanhebben.minetweaker.api.value.TweakerItem;
import net.minecraftforge.liquids.LiquidStack;
//#else
//+import net.minecraftforge.fluids.Fluid;
//#endif
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class AddLiquidCoolantAction implements IUndoableAction {
	//#ifdef MC152
	private IronEngineCoolant coolant;
	//#else
	//+private Fluid fluid;
	//+private float coolingPerMB;
	//#endif
	
	//#ifdef MC152
	public AddLiquidCoolantAction(TweakerItem fluid, float coolingPerMB) {
		coolant = new IronEngineCoolant(new LiquidStack(fluid.getItemId(), fluid.getItemSubId()), coolingPerMB);
	}
	//#else
	//+public AddLiquidCoolantAction(Fluid fluid, float coolingPerMB) {
		//+this.fluid = fluid;
		//+this.coolingPerMB = coolingPerMB;
	//+}
	//#endif

	public void apply() {
		//#ifdef MC152
		IronEngineCoolant.coolants.add(coolant);
		//#else
		//+IronEngineCoolant.addCoolant(fluid, coolingPerMB);
		//#endif
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifdef MC152
		IronEngineCoolant.coolants.remove(coolant);
		//#else
		//+IronEngineCoolant.liquidCoolants.remove(fluid.getName());
		//#endif
	}

	public String describe() {
		//#ifdef MC152
		return "Adding coolant liquid " + coolant.liquid.asItemStack().getDisplayName();
		//#else
		//+return "Adding coolant liquid " + fluid.getName();
		//#endif
	}

	public String describeUndo() {
		//#ifdef MC152
		return "Removing coolant liquid " + coolant.liquid.asItemStack().getDisplayName();
		//#else
		//+return "Removing coolant liquid " + fluid.getName();
		//#endif
	}
}
