/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.functions;

import buildcraft.api.fuels.IronEngineFuel;
import java.util.logging.Level;
//#ifdef MC152
import stanhebben.minetweaker.api.value.TweakerItem;
//#else
//+import net.minecraftforge.fluids.Fluid;
//#endif
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.actions.RemoveFuelAction;

/**
 *
 * @author Stanneke
 */
public class RemoveFuelFunction extends TweakerFunction {
	public static final RemoveFuelFunction INSTANCE = new RemoveFuelFunction();
	
	private RemoveFuelFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("fuels.remove requires one argument");
		}
		
		//#ifdef MC152
		TweakerItem fluid =
				notNull(arguments[0], "fuels.remove argument cannot be null")
				.toFluid("fuels.remove argument must be a fluid").get();
		
		RemoveFuelAction action = null;
		for (IronEngineFuel fuel : IronEngineFuel.fuels) {
			if (fuel.liquid.itemID == fluid.getItemId()
					&& fuel.liquid.itemMeta == fluid.getItemSubId()) {
				action = new RemoveFuelAction(fuel);
			}
		}
		if (action == null) {
			Tweaker.log(Level.INFO, "No such fuel: " + fluid.getName());
		} else {
			Tweaker.apply(action);
		}
		//#else
		//+Fluid fluid =
				//+notNull(arguments[0], "fuels.remove argument cannot be null")
				//+.toFluid("fuels.remove argument must be a fluid").get();
		
		//+if (IronEngineFuel.fuels.containsKey(fluid.getName())) {
			//+Tweaker.apply(new RemoveFuelAction(fluid));
		//+} else {
			//+Tweaker.log(Level.INFO, "No such fuel: " + fluid.getName());
		//+}
		//#endif
		
		return null;
	}

	@Override
	public String toString() {
		return "buildcraft.fuels.remove";
	}
}
