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
import stanhebben.minetweaker.mods.gregtech.actions.ElectrolyzerAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class ElectrolyzerAddRecipeFunction extends TweakerFunction {
	public static final ElectrolyzerAddRecipeFunction INSTANCE = new ElectrolyzerAddRecipeFunction();
	
	private ElectrolyzerAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 5) throw new TweakerExecuteException("electrolyzer.addRecipe requires 5 arguments");
		TweakerArray output =
				notNull(arguments[0], "output array cannot be null")
				.toArray("output must be an item array");
		TweakerItemStack input =
				notNull(arguments[1], "input cannot be null")
				.toItemStack("input must be an item");
		int numCells =
				notNull(arguments[2], "number of cells cannot be null")
				.toInt("number of cells must be an int").get();
		int duration =
				notNull(arguments[3], "duration cannot be null")
				.toInt("duration must be an int").get();
		int euPerTick =
				notNull(arguments[4], "eu per tick cannot be null")
				.toInt("eu per tick must be an int").get();
		if (output.size() == 0) throw new TweakerExecuteException("The output array cannot be null");
		
		TweakerItemStack output1 =
				notNull(output.get(0), "output 1 cannot be null")
				.toItemStack("output 1 must be an item");
		TweakerItemStack output2 = output.size() < 2 || output.get(1) == null ? null :
				output.get(1).toItemStack("output 2 must be an item");
		TweakerItemStack output3 = output.size() < 3 || output.get(2) == null ? null :
				output.get(2).toItemStack("output 3 must be an item");
		TweakerItemStack output4 = output.size() < 4 || output.get(3) == null ? null :
				output.get(3).toItemStack("output 4 must be an item");
		
		Tweaker.apply(new ElectrolyzerAddRecipeAction(output1, output2, output3, output4, input, numCells, duration, euPerTick));
		return null;
	}

	@Override
	public String toString() {
		return "electrolyzer.addRecipe";
	}
}
