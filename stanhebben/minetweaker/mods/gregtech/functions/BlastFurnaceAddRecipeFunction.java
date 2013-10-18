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
import stanhebben.minetweaker.mods.gregtech.actions.BlastFurnaceAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class BlastFurnaceAddRecipeFunction extends TweakerFunction {
	public static final BlastFurnaceAddRecipeFunction INSTANCE = new BlastFurnaceAddRecipeFunction();
	
	private BlastFurnaceAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 6) throw new TweakerExecuteException("blastFurnace.addRecipe requires 6 arguments");
		TweakerItemStack output1;
		TweakerItemStack output2;
		if (arguments[0] == null) {
			throw new TweakerExecuteException("output cannot be null");
		} else if (arguments[0].asItemStack() != null) {
			output1 = arguments[0].asItemStack();
			output2 = null;
		} else if (arguments[0].asArray() != null) {
			TweakerArray outputs = arguments[0].asArray();
			if (outputs.size() == 0) throw new TweakerExecuteException("outputs array cannot be empty");
			output1 = 
					notNull(outputs.get(0), "primary output cannot be null")
					.toItemStack("primary output must be an item");
			output2 = outputs.size() < 2 || outputs.get(1) == null ? null :
					outputs.get(1).toItemStack("secondary output must be an item");
		} else {
			throw new TweakerExecuteException("output must be either an item or an array with 1 or 2 items");
		}
		TweakerItemStack input1 =
				notNull(arguments[1], "primary input cannot be null")
				.toItemStack("primary input must be an item");
		TweakerItemStack input2 = arguments[2] == null ? null :
				arguments[2].toItemStack("secondary input must be an item");
		int duration =
				notNull(arguments[3], "duration cannot be null")
				.toInt("duration must be an int").get();
		int euPerTick =
				notNull(arguments[4], "eu per tick cannot be null")
				.toInt("eu per tick must be an int").get();
		int minHeat =
				notNull(arguments[5], "minimum temperature cannot be null")
				.toInt("minimum temperature must be an int").get();
		
		Tweaker.apply(new BlastFurnaceAddRecipeAction(output1, output2, input1, input2, duration, euPerTick, minHeat));
		return null;
	}

	@Override
	public String toString() {
		return "blastFurnace.addRecipe";
	}
}
