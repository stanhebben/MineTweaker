/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.actions;

//#ifndef MC152
//+import buildcraft.api.core.StackWrapper;
//+import buildcraft.api.fuels.IronEngineCoolant;
//+import net.minecraft.item.ItemStack;
//+import net.minecraftforge.fluids.FluidStack;
//#endif
import stanhebben.minetweaker.api.IUndoableAction;

/**
 *
 * @author Stanneke
 */
public class AddSolidCoolantAction implements IUndoableAction {
	//#ifndef MC152
	//+private ItemStack item;
	//+private FluidStack fluidPerItem;
	
	//+public AddSolidCoolantAction(ItemStack item, FluidStack fluidPerItem) {
		//+this.item = item;
		//+this.fluidPerItem = fluidPerItem;
	//+}
	//#endif

	public void apply() {
		//#ifndef MC152
		//+IronEngineCoolant.addCoolant(item, fluidPerItem);
		//#endif
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifndef MC152
		//+IronEngineCoolant.solidCoolants.remove(new StackWrapper(item));
		//#endif
	}

	public String describe() {
		//#ifdef MC152
		return "NOP";
		//#else
		//+return "Adding solid coolant " + item.getDisplayName();
		//#endif
	}

	public String describeUndo() {
		//#ifdef MC152
		return "NOP";
		//#else
		//+return "Removing solid coolant " + item.getDisplayName();
		//#endif
	}
}
