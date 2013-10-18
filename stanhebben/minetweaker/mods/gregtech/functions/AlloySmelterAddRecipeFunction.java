/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.gregtech.actions.AlloySmelterAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class AlloySmelterAddRecipeFunction extends TweakerFunction {
	public static final AlloySmelterAddRecipeFunction INSTANCE = new AlloySmelterAddRecipeFunction();
	
	private AlloySmelterAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 5) throw new TweakerExecuteException("alloySmelter.addRecipe requires 5 arguments");
		TweakerItemStack output =
				notNull(arguments[0], "output cannot be null")
				.toItemStack("output must be an item");
		TweakerItemStack input1 = 
				notNull(arguments[1], "input 1 cannot be null")
				.toItemStack("input 1 must be an item");
		TweakerItemStack input2 = arguments[2] == null ? null :
				arguments[2].toItemStack("input 2 must be an item");
		int duration =
				notNull(arguments[3], "duration cannot be null")
				.toInt("duration must be an int").get();
		int euPerTick =
				notNull(arguments[4], "eu per tick cannot be null")
				.toInt("eu per tick must be an int").get();
		
		Tweaker.apply(new AlloySmelterAddRecipeAction(output, input1, input2, duration, euPerTick));
		return null;
	}

	@Override
	public String toString() {
		return "alloySmelter.addRecipe";
	}
}
