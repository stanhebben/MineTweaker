package stanhebben.minetweaker.base.actions;

//#ifdef MC152
//+import net.minecraftforge.liquids.LiquidContainerData;
//+import net.minecraftforge.liquids.LiquidContainerRegistry;
//+import net.minecraftforge.liquids.LiquidStack;
//#else
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
//#endif
import stanhebben.minetweaker.MineTweakerUtil;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;

/**
 * Executes liquid.addContainer.
 * 
 * @author Stan Hebben
 */
public final class AddLiquidContainerAction implements IUndoableAction {
	//#ifdef MC152
	//+private final LiquidContainerData data;
	//#else
	private final Fluid fluid;
	private final int amount;
	private final TweakerItem emptyContainer;
	private final TweakerItem fullContainer;
	//#endif
	private final boolean isNewEmpty;
	
	//#ifdef MC152
	//+public AddLiquidContainerAction(LiquidStack stack, TweakerItem emptyContainer, TweakerItem fullContainer) {
		//+data = new LiquidContainerData(stack, fullContainer.make(1), emptyContainer.make(1));
		//+isNewEmpty = LiquidContainerRegistry.isEmptyContainer(emptyContainer.make(1));
	//+}
	//#else
	public AddLiquidContainerAction(Fluid fluid, int amount, TweakerItem emptyContainer, TweakerItem fullContainer) {
		this.fluid = fluid;
		this.amount = amount;
		this.emptyContainer = emptyContainer;
		this.fullContainer = fullContainer;
		isNewEmpty = FluidContainerRegistry.isEmptyContainer(emptyContainer.make(1));
	}
	//#endif
	
	public void apply() {
		//#ifdef MC152
		//+LiquidContainerRegistry.registerLiquid(data);
		//#else
		FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, amount), fullContainer.make(1), emptyContainer.make(1));
		//#endif
	}
	
	public boolean canUndo() {
		return MineTweakerUtil.canRemoveContainer();
	}

	public void undo() {
		//#ifdef MC152
		//+if (isNewEmpty) MineTweakerUtil.removeEmptyContainer(data.container);
		//#else
		if (isNewEmpty) MineTweakerUtil.removeEmptyContainer(emptyContainer);
		MineTweakerUtil.removeContainer(fullContainer);
		//#endif
	}

	public String describe() {
		//#ifdef MC152
		//+return "Adding a fluid container: " + data.container.getDisplayName() + " + " + data.stillLiquid.asItemStack().getDisplayName() + " = " + data.filled.getDisplayName();
		//#else
		return "Adding a fluid container: " + emptyContainer.getDisplayName() + " + " + fluid.getLocalizedName() + " = " + fullContainer.getDisplayName();
		//#endif
	}

	public String describeUndo() {
		//#ifdef MC152
		//+return "Removing fluid container " + data.filled.getDisplayName();
		//#else
		return "Removing fluid container " + fullContainer.getDisplayName();
		//#endif
	}
}
