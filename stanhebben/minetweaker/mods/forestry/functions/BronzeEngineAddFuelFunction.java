/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerLiquid;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.actions.BronzeEngineAddFuelAction;

/**
 *
 * @author Stanneke
 */
public class BronzeEngineAddFuelFunction extends TweakerFunction {
	public static final BronzeEngineAddFuelFunction INSTANCE = new BronzeEngineAddFuelFunction();
	
	private BronzeEngineAddFuelFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 3 || arguments.length > 4) throw new TweakerExecuteException("biogasEngine.addFuel requires 3-4 arguments");
		TweakerLiquid fluid =
				notNull(arguments[0], "fuel cannot be null")
				.toFluid("fuel must be a liquid");
		int powerPerCycle =
				notNull(arguments[1], "power per cycle cannot be null")
				.toInt("power per cycle must be an int").get();
		int burnDuration =
				notNull(arguments[2], "cycles cannot be null")
				.toInt("cycles must be an int").get();
		int dissipationMultiplier = arguments.length < 4 || arguments[3] == null ? 1 :
				arguments[3].toInt("dissipation factor must be an int").get();
		
		Tweaker.apply(new BronzeEngineAddFuelAction(fluid, powerPerCycle, burnDuration, dissipationMultiplier));
		return null;
	}

	@Override
	public String toString() {
		return "biogasEngine.addFuel";
	}
}
