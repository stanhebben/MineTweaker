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
import stanhebben.minetweaker.mods.gregtech.actions.SawmillAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class SawmillAddRecipeFunction extends TweakerFunction {
	public static final SawmillAddRecipeFunction INSTANCE = new SawmillAddRecipeFunction();
	
	private SawmillAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2) throw new TweakerExecuteException("sawmill.addRecipe requires at least 2 arguments");
		TweakerItemStack output1;
		TweakerItemStack output2;
		TweakerItemStack output3;
		if (arguments[0] == null) {
			throw new TweakerExecuteException("output cannot be null");
		} else if (arguments[0].asItemStack() != null) {
			output1 = arguments[0].asItemStack();
			output2 = null;
			output3 = null;
		} else if (arguments[0].asArray() != null) {
			TweakerArray outputs = arguments[0].asArray();
			if (outputs.size() == 0) throw new TweakerExecuteException("the output array cannot be empty");
			output1 =
					notNull(outputs.get(0), "the primary output cannot be null")
					.toItemStack("output 1 must be an item stack");
			output2 = outputs.size() < 2 || outputs.get(1) == null ? null :
					outputs.get(1).toItemStack("output 2 must be an item stack");
			output3 = outputs.size() < 3 || outputs.get(2) == null ? null :
					outputs.get(2).toItemStack("output 3 must be an item stack");
		} else {
			throw new TweakerExecuteException("output must be either an item or array of 1-3 items");
		}
		TweakerItemStack input =
				notNull(arguments[1], "input cannot be null")
				.toItemStack("input must be an item stack");
		int cells = arguments.length < 3 ? 0 :
				notNull(arguments[2], "cells cannot be null")
				.toInt("cells must be an int").get();
		
		Tweaker.apply(new SawmillAddRecipeAction(output1, output2, output3, input, cells));
		return null;
	}

	@Override
	public String toString() {
		return "sawmill.addRecipe";
	}
}
