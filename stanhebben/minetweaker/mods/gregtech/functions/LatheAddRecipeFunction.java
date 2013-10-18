/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.gregtech.actions.LatheAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class LatheAddRecipeFunction extends TweakerFunction {
	public static final LatheAddRecipeFunction INSTANCE = new LatheAddRecipeFunction();
	
	private LatheAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 4) throw new TweakerExecuteException("lathe.addRecipe requires 4 arguments");
		TweakerItemStack output1;
		TweakerItemStack output2;
		if (arguments[0] == null) {
			throw new TweakerExecuteException("output cannot be null");
		} else if (arguments[0].asItemStack() != null) {
			output1 = arguments[0].asItemStack();
			output2 = null;
		} else if (arguments[0].asArray() != null) {
			TweakerArray array = arguments[0].asArray();
			if (array.size() == 0) throw new TweakerExecuteException("outputs must be an array of 1 or 2 elements");
			output1 =
					notNull(array.get(0), "output 1 cannot be null")
					.toItemStack("output 1 must be an item");
			output2 = array.size() < 2 || array.get(1) == null ? null :
					array.get(1).toItemStack("output 2 must be an item");
		} else {
			throw new TweakerExecuteException("output must be an item or an array with 1 or 2 elements");
		}
		TweakerItemStack input =
				notNull(arguments[1], "input cannot be null")
				.toItemStack("input must be an item");
		int duration =
				notNull(arguments[2], "duration cannot be null")
				.toInt("duration must be an int").get();
		int euPerTick =
				notNull(arguments[3], "eu per tick cannot be null")
				.toInt("eu per tick must be an int").get();
		
		Tweaker.apply(new LatheAddRecipeAction(output1, output2, input, duration, euPerTick));
		return null;
	}

	@Override
	public String toString() {
		return "lathe.addRecipe";
	}
}
