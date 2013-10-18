/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFluid;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.actions.GeneratorRemoveFuelAction;

/**
 *
 * @author Stanneke
 */
public class GeneratorRemoveFuelFunction extends TweakerFunction {
	public static final GeneratorRemoveFuelFunction INSTANCE = new GeneratorRemoveFuelFunction();
	
	private GeneratorRemoveFuelFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("bioGenerator.removeFuel requires 1 argument");
		TweakerFluid fluid =
				notNull(arguments[0], "fuel cannot be null")
				.toFluid("fuel must be a liquid");
		
		Tweaker.apply(new GeneratorRemoveFuelAction(fluid));
		return null;
	}

	@Override
	public String toString() {
		return "bioGenerator.removeFuel";
	}
}
