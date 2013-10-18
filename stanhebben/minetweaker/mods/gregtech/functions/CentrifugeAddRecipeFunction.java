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
import stanhebben.minetweaker.mods.gregtech.actions.CentrifugeAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class CentrifugeAddRecipeFunction extends TweakerFunction {
	public static final CentrifugeAddRecipeFunction INSTANCE = new CentrifugeAddRecipeFunction();
	
	private CentrifugeAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 4) throw new TweakerExecuteException("centrifuge.addRecipe requires 4 arguments");
		TweakerArray output =
				notNull(arguments[0], "outputs cannot be null")
				.toArray("outputs must be an item array");
		TweakerItemStack input =
				notNull(arguments[1], "input cannot be null")
				.toItemStack("input must be an item");;
		int numCells =
				notNull(arguments[2], "cell count cannot be null")
				.toInt("cells must be an int").get();
		int duration =
				notNull(arguments[3], "duration cannot be null")
				.toInt("duration must be an int").get();
		
		if (output.size() == 0) throw new TweakerExecuteException("outputs array cannot be empty");
		if (output.size() > 4) throw new TweakerExecuteException("outputs array can contain up to 4 elements");
		
		TweakerItemStack output1 =
				notNull(output.get(0), "output 1 cannot be null")
				.toItemStack("output 1 must be an item");
		TweakerItemStack output2 = output.size() < 2 || output.get(1) == null ? null :
				output.get(1).toItemStack("output 2 must be an item");
		TweakerItemStack output3 = output.size() < 3 || output.get(2) == null ? null :
				output.get(2).toItemStack("output 3 must be an item");
		TweakerItemStack output4 = output.size() < 4 || output.get(3) == null ? null :
				output.get(3).toItemStack("output 4 must be an item");
		
		Tweaker.apply(new CentrifugeAddRecipeAction(output1, output2, output3, output4, input, numCells, duration));
		return null;
	}
	
	@Override
	public String toString() {
		return "centrifuge.addRecipe";
	}
}
