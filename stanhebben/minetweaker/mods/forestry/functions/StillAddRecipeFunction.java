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
import stanhebben.minetweaker.mods.forestry.actions.StillAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class StillAddRecipeFunction extends TweakerFunction {
	public static final StillAddRecipeFunction INSTANCE = new StillAddRecipeFunction();
	
	private StillAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 3) throw new TweakerExecuteException("still.addRecipe requires 3 arguments");
		TweakerFluid output =
				notNull(arguments[0], "output cannot be null")
				.toFluid("output must be a liquid");
		TweakerFluid input =
				notNull(arguments[1], "input cannot be null")
				.toFluid("input must be a liquid");
		int cyclesPerUnit =
				notNull(arguments[2], "cycles per unit cannot be null")
				.toInt("cycles per unit must be an int").get();
		
		Tweaker.apply(new StillAddRecipeAction(output, input, cyclesPerUnit));
		return null;
	}

	@Override
	public String toString() {
		return "stille.addRecipe";
	}
}
