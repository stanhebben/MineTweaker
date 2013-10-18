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
import stanhebben.minetweaker.mods.gregtech.actions.CannerAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class CannerAddRecipeFunction extends TweakerFunction {
	public static final CannerAddRecipeFunction INSTANCE = new CannerAddRecipeFunction();
	
	private CannerAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2) throw new TweakerExecuteException("canner.addRecipe requires at least 2 arguments");
		TweakerItemStack output1;
		TweakerItemStack output2;
		if (arguments[0] == null) {
			throw new TweakerExecuteException("output cannot be null");
		} else if (arguments[0].asItemStack() != null) {
			output1 = arguments[0].asItemStack();
			output2 = null;
		} else if (arguments[0].asArray() != null) {
			TweakerArray outputs = arguments[0].asArray();
			if (outputs.size() == 0) throw new TweakerExecuteException("outputs cannot be an empty array");
			output1 =
					notNull(outputs.get(0), "primary output cannot be null")
					.toItemStack("primary output must be an item");
			output2 = outputs.size() < 2 || outputs.get(1) == null ? null :
					outputs.get(1).toItemStack("secondary output must be an item");
		} else {
			throw new TweakerExecuteException("output must be either an item or an array with 1 or 2 items");
		}
		
		TweakerItemStack input1 =
				notNull(arguments[1], "input 1 cannot be null")
				.toItemStack("input 1 must be an item");
		TweakerItemStack input2 = arguments.length < 3 || arguments[2] == null ? null :
				arguments[2].toItemStack("input 2 must be an item");
		int duration = arguments.length < 4 || arguments[3] == null ? 100 :
				arguments[3].toInt("duration must be an int").get();
		int euPerTick = arguments.length < 5 || arguments[4] == null ? 1 :
				arguments[4].toInt("eu per tick must be an int").get();
		Tweaker.apply(new CannerAddRecipeAction(output1, output2, input1, input2, duration, euPerTick));
		return null;
	}

	@Override
	public String toString() {
		return "canner.addRecipe";
	}
}
