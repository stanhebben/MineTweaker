/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.base.actions;

//#ifdef MC152
//+import net.minecraftforge.liquids.LiquidContainerData;
//+import net.minecraftforge.liquids.LiquidContainerRegistry;
//#else
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
//#endif
import stanhebben.minetweaker.MineTweakerUtil;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;

/**
 *
 * @author Stanneke
 */
public class RemoveFluidContainerAction implements IUndoableAction {
	private TweakerItem filled;
	//#ifdef MC152
	//+private LiquidContainerData data;
	//#else
	private FluidContainerData data;
	//#endif
	
	public RemoveFluidContainerAction(TweakerItem filled) {
		this.filled = filled;
		data = MineTweakerUtil.getContainerData(filled);
	}

	public void apply() {
		MineTweakerUtil.removeContainer(filled);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifdef MC152
		//+LiquidContainerRegistry.registerLiquid(data);
		//#else
		FluidContainerRegistry.registerFluidContainer(data);
		//#endif
	}

	public String describe() {
		return "Removing fluid container " + filled.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring fluid container " + filled.getDisplayName();
	}
}
