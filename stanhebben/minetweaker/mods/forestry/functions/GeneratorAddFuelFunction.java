/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFluidStack;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.actions.GeneratorAddFuelAction;

/**
 *
 * @author Stanneke
 */
public class GeneratorAddFuelFunction extends TweakerFunction {
	public static final GeneratorAddFuelFunction INSTANCE = new GeneratorAddFuelFunction();
	
	private GeneratorAddFuelFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 3) throw new TweakerExecuteException("bioGenerator.addFuel requires 3 arguments");
		TweakerFluidStack fluidConsumed =
				notNull(arguments[0], "fuel cannot be null")
				.toFluidStack("fuel must be a liquid stack");
		int euPerTick =
				notNull(arguments[1], "eu per tick cannot be null")
				.toInt("eu per tick must be an int").get();
		int ticksPerMillibucket =
				notNull(arguments[2], "ticks per millibucket cannot be null")
				.toInt("ticks per millibucket must be an int").get();
		
		Tweaker.apply(new GeneratorAddFuelAction(fluidConsumed, euPerTick, ticksPerMillibucket));
		return null;
	}

	@Override
	public String toString() {
		return "bioGenerator.addFuel";
	}
}
