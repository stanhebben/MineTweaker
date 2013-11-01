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
import stanhebben.minetweaker.api.value.TweakerLiquidStack;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.actions.FermenterAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class FermenterAddRecipeFunction extends TweakerFunction {
	public static final FermenterAddRecipeFunction INSTANCE = new FermenterAddRecipeFunction();
	
	private FermenterAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 3 || arguments.length > 5) throw new TweakerExecuteException("fermenter.addRecipe requires 4-5 arguments");
		TweakerLiquid output =
				notNull(arguments[0], "output cannot be null")
				.toFluid("output must be a liquid");
		TweakerItem input =
				notNull(arguments[1], "input cannot be null")
				.toItem("input must be an item");
		int outputValue =
				notNull(arguments[2], "output quantity cannot be null")
				.toInt("output quantity must be an item").get();
		float modifier = arguments.length < 4 || arguments[3] == null ? 1.0f :
				arguments[3].toFloat("multiplier must be a float value").get();
		TweakerLiquidStack inputFluid = arguments.length < 5 || arguments[4] == null ? null :
				arguments[4].toFluidStack("input liquid must be a liquid stack");
		
		Tweaker.apply(new FermenterAddRecipeAction(output, input, outputValue, modifier, inputFluid));
		return null;
	}

	@Override
	public String toString() {
		return "fermenter.addRecipe";
	}
}
