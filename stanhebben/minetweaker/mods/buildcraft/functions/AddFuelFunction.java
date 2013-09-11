/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFluid;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.actions.AddFuelAction;

/**
 *
 * @author Stanneke
 */
public class AddFuelFunction extends TweakerFunction {
	public static final AddFuelFunction INSTANCE = new AddFuelFunction();
	
	private AddFuelFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 3) {
			throw new TweakerExecuteException("fuels.add requires 3 arguments");
		}
		
		TweakerFluid fluid =
				notNull(arguments[0], "fuels.add fuel cannot be null")
				.toFluid("fuels.add fuel must be a fluid");
		float powerPerCycle =
				notNull(arguments[1], "fuels.add power per cycle cannot be null")
				.toFloat("fuels.add power per cycle must be a float").get();
		int totalBurningTime =
				notNull(arguments[2], "fuels.add total burning time cannot be null")
				.toInt("fuels.add total burning time must ba an int").get();
		Tweaker.apply(new AddFuelAction(fluid, powerPerCycle, totalBurningTime));
		return null;
	}

	@Override
	public String toString() {
		return "buildcraft.fuels.add";
	}
}
