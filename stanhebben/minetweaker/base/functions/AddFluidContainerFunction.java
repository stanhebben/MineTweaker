/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.base.functions;

//#ifdef MC152
//+import net.minecraftforge.liquids.LiquidStack;
//#else
import net.minecraftforge.fluids.Fluid;
//#endif
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.AddLiquidContainerAction;

/**
 *
 * @author Stanneke
 */
public class AddFluidContainerFunction extends TweakerFunction {
	//#ifdef MC152
	//+private TweakerItem fluid;
	//#else
	private Fluid fluid;
	//#endif
	
	//#ifdef MC152
	//+public AddFluidContainerFunction(TweakerItem fluid) {
		//+this.fluid = fluid;
	//+}
	//#else
	public AddFluidContainerFunction(Fluid fluid) {
		this.fluid = fluid;
	}
	//#endif

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 3) {
			throw new TweakerExecuteException("addContainer requires 3 arguments");
		}
		TweakerItem emptyContainer =
				notNull(arguments[0], "addContainer empty container cannot be null")
				.toItem("addContainer empty container must be an item");
		int amount =
				notNull(arguments[1], "addContainer amount cannot be null")
				.toInt("addContainer amount must be an int").get();
		TweakerItem fullContainer =
				notNull(arguments[2], "addContainer filled container cannot be null")
				.toItem("addContainer filled container must be an item");
		//#ifdef MC152
		//+Tweaker.apply(new AddFluidContainerAction(new LiquidStack(fluid.getItemId(), amount, fluid.getItemSubId()), emptyContainer, fullContainer));
		//#else
		Tweaker.apply(new AddLiquidContainerAction(fluid, amount, emptyContainer, fullContainer));
		//#endif
		
		return null;
	}

	@Override
	public String toString() {
		return "fluid.addContainer";
	}
}
