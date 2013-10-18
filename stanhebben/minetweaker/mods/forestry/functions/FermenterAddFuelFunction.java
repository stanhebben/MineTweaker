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
import stanhebben.minetweaker.mods.forestry.actions.FermenterAddFuelAction;

/**
 *
 * @author Stanneke
 */
public class FermenterAddFuelFunction extends TweakerFunction {
	public static final FermenterAddFuelFunction INSTANCE = new FermenterAddFuelFunction();
	
	private FermenterAddFuelFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 3) throw new TweakerExecuteException("fermenter.addFuel requires 3 arguments");
		TweakerItem fuel =
				notNull(arguments[0], "fuel cannot be null")
				.toItem("fuel must be an item");
		int fermentPerCycle =
				notNull(arguments[1], "ferment per cycle cannot be null")
				.toInt("ferment per cycle must be an int").get();
		int cycles =
				notNull(arguments[2], "cycles cannot be null")
				.toInt("cycles must be an int").get();
		
		Tweaker.apply(new FermenterAddFuelAction(fuel, fermentPerCycle, cycles));
		return null;
	}

	@Override
	public String toString() {
		return "fermenter.addFuel";
	}
}
