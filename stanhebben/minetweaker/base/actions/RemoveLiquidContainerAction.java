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
 * Implements liquid.removeContainer.
 * 
 * @author Stan Hebben
 */
public final class RemoveLiquidContainerAction implements IUndoableAction {
	private final TweakerItem filled;
	//#ifdef MC152
	//+private final LiquidContainerData data;
	//#else
	private final FluidContainerData data;
	//#endif
	
	public RemoveLiquidContainerAction(TweakerItem filled) {
		this.filled = filled;
		data = MineTweakerUtil.getContainerData(filled);
	}

	@Override
	public void apply() {
		MineTweakerUtil.removeContainer(filled);
	}

	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		//#ifdef MC152
		//+LiquidContainerRegistry.registerLiquid(data);
		//#else
		FluidContainerRegistry.registerFluidContainer(data);
		//#endif
	}

	@Override
	public String describe() {
		return "Removing fluid container " + filled.getDisplayName();
	}

	@Override
	public String describeUndo() {
		return "Restoring fluid container " + filled.getDisplayName();
	}
}
