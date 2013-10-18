/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.actions.CopperEngineAddFuelAction;

/**
 *
 * @author Stanneke
 */
public class CopperEngineAddFuelFunction extends TweakerFunction {
	public static final CopperEngineAddFuelFunction INSTANCE = new CopperEngineAddFuelFunction();
	
	private CopperEngineAddFuelFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 3) throw new TweakerExecuteException("peatFiredEngine.addFuel requires 3 arguments");
		TweakerItem item =
				notNull(arguments[0], "fuel cannot be null")
				.toItem("fuel must be an item");
		int powerPerCycle =
				notNull(arguments[1], "power per cycle cannot be null")
				.toInt("power per cycle must be an int").get();
		int numCycles =
				notNull(arguments[2], "cycles cannot be null")
				.toInt("cycles must be an int").get();
		
		Tweaker.apply(new CopperEngineAddFuelAction(item, powerPerCycle, numCycles));
		return null;
	}

	@Override
	public String toString() {
		return "peatFiredEngine.addFuel";
	}
}
