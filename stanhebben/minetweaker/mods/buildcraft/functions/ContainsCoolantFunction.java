/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.functions;

//#ifndef MC152
//+import buildcraft.api.core.StackWrapper;
//#endif
import buildcraft.api.fuels.IronEngineCoolant;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerBool;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class ContainsCoolantFunction extends TweakerFunction {
	public static final ContainsCoolantFunction INSTANCE = new ContainsCoolantFunction();
	
	private ContainsCoolantFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("coolants.contains requires one argument");
		}
		
		TweakerValue value = notNull(arguments[0], "the coolants.contains argument cannot be null");
		if (value.asFluid() != null) {
			//#ifdef MC152
			TweakerItem fluid = value.asFluid().get();
			for (IronEngineCoolant coolant : IronEngineCoolant.coolants) {
				if (coolant.liquid.itemID == fluid.getItemId()
						&& coolant.liquid.itemMeta == fluid.getItemSubId()) {
					return TweakerBool.TRUE;
				}
			}
			return TweakerBool.FALSE;
			//#else
			//+return TweakerBool.get(
					//+IronEngineCoolant.liquidCoolants.containsKey(value.asFluid().get().getName()));
			//#endif
		} else if (value.asItem() != null) {
			//#ifdef MC152
			return TweakerBool.FALSE;
			//#else
			//+return TweakerBool.get(
					//+IronEngineCoolant.solidCoolants.containsKey(new StackWrapper(value.asItem().make(1))));
			//#endif
		} else {
			throw new TweakerExecuteException("the coolants.contains argument must be a fluid or item");
		}
	}

	@Override
	public String toString() {
		return "buildcraft.coolants.contains";
	}
}
