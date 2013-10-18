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
import stanhebben.minetweaker.mods.gregtech.actions.ImplosionCompressorAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class ImplosionCompressorAddRecipeFunction extends TweakerFunction {
	public static final ImplosionCompressorAddRecipeFunction INSTANCE = new ImplosionCompressorAddRecipeFunction();
	
	private ImplosionCompressorAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 3) throw new TweakerExecuteException("implosionCompressor.addRecipe requires 3 arguments");
		TweakerItemStack output1;
		TweakerItemStack output2;
		if (arguments[0] == null) {
			throw new TweakerExecuteException("output cannot be null");
		} else if (arguments[0].asItemStack() != null) {
			output1 = arguments[0].asItemStack();
			output2 = null;
		} else if (arguments[0].asArray() != null) {
			TweakerArray outputs = arguments[0].asArray();
			if (outputs.size() == 0) throw new TweakerExecuteException("outputs cannot be empty");
			output1 =
					notNull(outputs.get(0), "primary output cannot be null")
					.toItemStack("primary output must be an item");
			output2 = outputs.size() < 2 || outputs.get(1) == null ? null :
					outputs.get(1).toItemStack("secondary output must be an item");
		} else {
			throw new TweakerExecuteException("output must be an item or an array with 1 or 2 elements");
		}
		TweakerItemStack input =
				notNull(arguments[1], "input cannot be null")
				.toItemStack("input must be an item");
		int itnt =
				notNull(arguments[2], "iTNT amount cannot be null")
				.toInt("iTNT amount must be an int").get();
		
		Tweaker.apply(new ImplosionCompressorAddRecipeAction(output1, output2, input, itnt));
		return null;
	}

	@Override
	public String toString() {
		return "implosionCompressor.addRecipe";
	}
}
