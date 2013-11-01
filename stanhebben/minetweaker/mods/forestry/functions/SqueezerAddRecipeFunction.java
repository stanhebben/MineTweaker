/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerLiquidStack;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.actions.SqueezerAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class SqueezerAddRecipeFunction extends TweakerFunction {
	public static final SqueezerAddRecipeFunction INSTANCE = new SqueezerAddRecipeFunction();
	
	private SqueezerAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 3 || arguments.length > 5) throw new TweakerExecuteException("squeezer.addRecipe requires 3-5 inputs");
		TweakerLiquidStack output =
				notNull(arguments[0], "output cannot be null")
				.toFluidStack("output must be a liquid stack");
		TweakerItemStack[] input;
		if (arguments[1] == null) {
			throw new TweakerExecuteException("input cannot be null");
		} else if (arguments[1].asItemStack() != null) {
			input = new TweakerItemStack[] { arguments[1].asItemStack() };
		} else if (arguments[1].asArray() != null) {
			TweakerArray inputArray = arguments[1].asArray();
			input = new TweakerItemStack[inputArray.size()];
			for (int i = 0; i < input.length; i++) {
				input[i] =
						notNull(inputArray.get(i), "input element cannot be null")
						.toItemStack("input element must be an item stack");
			}
		} else {
			throw new TweakerExecuteException("input must be an item stack or an array of item stacks");
		}
		int time =
				notNull(arguments[2], "time cannot be null")
				.toInt("time must be an int").get();
		TweakerItem remnant = arguments.length < 4 || arguments[3] == null ? null :
				arguments[3].toItem("remnant must be an item");
		int remnantChance = arguments.length < 5 || arguments[4] == null ? 100 :
				arguments[4].toInt("remnant chance must be an int").get();
		
		Tweaker.apply(new SqueezerAddRecipeAction(output, input, time, remnant, remnantChance));
		return null;
	}

	@Override
	public String toString() {
		return "squeezer.addRecipe";
	}
}
