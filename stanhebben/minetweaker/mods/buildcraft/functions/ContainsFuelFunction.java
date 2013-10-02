/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.functions;

import java.util.LinkedList;

import buildcraft.api.fuels.IronEngineFuel;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerBool;
import stanhebben.minetweaker.api.value.TweakerFluid;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class ContainsFuelFunction extends TweakerFunction {
	public static final ContainsFuelFunction INSTANCE = new ContainsFuelFunction();
	
	private ContainsFuelFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("fuels.contains requires one argument");
		}
		
		TweakerFluid fluid =
				notNull(arguments[0], "fuels.contains argument cannot be null")
				.toFluid("fuels.contains argument must be a fluid");
		
		//#ifdef MC152
		//+for (IronEngineFuel fuel : (LinkedList<IronEngineFuel>) IronEngineFuel.fuels) {
			//+if (fuel.liquid.equals(fluid.get().make(1))) {
				//+return TweakerBool.TRUE;
			//+}
		//+}
		//+return TweakerBool.FALSE;
		//#else
		return TweakerBool.get(IronEngineFuel.fuels.containsKey(fluid.get().getName()));
		//#endif
	}

	@Override
	public String toString() {
		return "buildcraft.fuels.contains";
	}
}
