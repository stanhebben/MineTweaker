/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.gregtech.actions.FusionReactorAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class FusionReactorAddRecipeFunction extends TweakerFunction {
	public static final FusionReactorAddRecipeFunction INSTANCE = new FusionReactorAddRecipeFunction();
	
	private FusionReactorAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		TweakerItemStack output =
				notNull(arguments[0], "fusion reactor output cannot be null")
				.toItemStack("fusion reactor output must be an item");
		TweakerItemStack input1 =
				notNull(arguments[1], "fusion reactor first input cannot be null")
				.toItemStack("fusion reactor first input must be an item");
		TweakerItemStack input2 =
				notNull(arguments[2], "fusion reactor second input cannot be null")
				.toItemStack("fusion reactor second input must be an item");
		int duration =
				notNull(arguments[3], "duration cannot be null")
				.toInt("duration must be an integer").get();
		int energyPerTick =
				notNull(arguments[4], "energy per tick cannot be null")
				.toInt("energy per tick must be an integer").get();
		int startupEnergy =
				notNull(arguments[5], "startup energy cannot be null")
				.toInt("startup per tick must be an integer").get();
		
		Tweaker.apply(new FusionReactorAddRecipeAction(output, input1, input2, duration, energyPerTick, startupEnergy));
		return null;
	}

	@Override
	public String toString() {
		return "fusionReactor.addRecipe";
	}
}
