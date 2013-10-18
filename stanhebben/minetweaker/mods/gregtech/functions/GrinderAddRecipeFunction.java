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
import stanhebben.minetweaker.mods.gregtech.actions.GrinderAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class GrinderAddRecipeFunction extends TweakerFunction {
	public static final GrinderAddRecipeFunction INSTANCE = new GrinderAddRecipeFunction();
	
	private GrinderAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2) throw new TweakerExecuteException("grinder.addRecipe requires at least 2 arguments");
		TweakerItemStack output1;
		TweakerItemStack output2;
		TweakerItemStack output3;
		TweakerItemStack output4;
		if (arguments[0] == null) {
			throw new TweakerExecuteException("output cannot be null");
		} else if (arguments[0].asItemStack() != null) {
			output1 = arguments[0].asItemStack();
			output2 = null;
			output3 = null;
			output4 = null;
		} else if (arguments[0].asArray() != null) {
			TweakerArray outputs = arguments[0].asArray();
			if (outputs.size() == 0) throw new TweakerExecuteException("output array cannot be empty");
			output1 = 
					notNull(outputs.get(0), "output 1 cannot be null")
					.toItemStack("output 1 must be an item");
			output2 = outputs.size() < 2 || outputs.get(1) == null ? null :
					outputs.get(1).toItemStack("output 2 must be an item");
			output3 = outputs.size() < 3 || outputs.get(2) == null ? null :
					outputs.get(2).toItemStack("output 3 must be an item");
			output4 = outputs.size() < 4 || outputs.get(3) == null ? null :
					outputs.get(3).toItemStack("output 4 must be an item");
		} else {
			throw new TweakerExecuteException("output must be an item or item array");
		}
		TweakerItemStack input1 =
				notNull(arguments[1], "primary input cannot be null")
				.toItemStack("primary input must be an item");
		TweakerItemStack input2 = arguments.length < 3 || arguments[2] == null ? null :
				arguments[2].toItemStack("secondary input must be an item");
		
		Tweaker.apply(new GrinderAddRecipeAction(output1, output2, output3, output4, input1, input2));
		return null;
	}

	@Override
	public String toString() {
		return "grinder.addRecipe";
	}
}
