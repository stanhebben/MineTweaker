/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.base.functions;

import java.util.logging.Level;
//#ifdef MC152
//+import net.minecraftforge.liquids.LiquidStack;
//+import net.minecraftforge.liquids.LiquidContainerRegistry;
//#else
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
//#endif
import stanhebben.minetweaker.MineTweakerUtil;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.RemoveLiquidContainerAction;

/**
 *
 * @author Stanneke
 */
public class RemoveFluidContainerFunction extends TweakerFunction {
	//#ifdef MC152
	//+private final TweakerItem fluid;
	//#else
	private Fluid fluid;
	//#endif
	
	//#ifdef MC152
	//+public RemoveFluidContainerFunction(TweakerItem fluid) {
		//+this.fluid = fluid;
	//+}
	//#else
	public RemoveFluidContainerFunction(Fluid fluid) {
		this.fluid = fluid;
	}
	//#endif

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 1) {
			throw new TweakerExecuteException("removeContainer requires one argument");
		}
		
		TweakerItem container =
				notNull(arguments[0], "removeContainer container argument cannot be null")
				.toItem("removeContainer container argument must be an item");
		
		//#ifdef MC152
		//+LiquidStack registered = LiquidContainerRegistry.getLiquidForFilledItem(container.make(1));
		//+if (registered == null) {
			//+throw new TweakerExecuteException(container.getDisplayName() + " is not registered as fluid container");
		//+} else if (registered.isLiquidEqual(fluid.make(1))) {
			//+throw new TweakerExecuteException(container.getDisplayName() + " does not contain " + fluid.getDisplayName());
		//+} else if (!MineTweakerUtil.canRemoveContainer()) {
			//+Tweaker.log(Level.WARNING, "Container removal is unavailable. Action skipped.");
		//+} else {
			//+Tweaker.apply(new RemoveLiquidContainerAction(container));
		//+}
		//#else
		FluidStack registered = FluidContainerRegistry.getFluidForFilledItem(container.make(1));
		if (registered == null) {
			throw new TweakerExecuteException(container.getDisplayName() + " is not registered as fluid container");
		} else if (registered.fluidID != fluid.getID()) {
			throw new TweakerExecuteException(container.getDisplayName() + " does not contain " + fluid.getLocalizedName());
		} else if (!MineTweakerUtil.canRemoveContainer()) {
			Tweaker.log(Level.WARNING, "Container removal is unavailable. Action skipped.");
		} else {
			Tweaker.apply(new RemoveLiquidContainerAction(container));
		}
		//#endif
		
		return null;
	}

	@Override
	public String toString() {
		return "fluid.removeContainer";
	}
}
