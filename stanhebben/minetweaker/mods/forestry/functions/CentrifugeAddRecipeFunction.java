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
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.actions.CentrifugeAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class CentrifugeAddRecipeFunction extends TweakerFunction {
	public static final CentrifugeAddRecipeFunction INSTANCE = new CentrifugeAddRecipeFunction();

	private CentrifugeAddRecipeFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 3) throw new TweakerExecuteException("centrifuge.addRecipe requires at least 3 arguments");
		TweakerItemStack[] outputs;
		if (arguments[0] == null) {
			throw new TweakerExecuteException("output cannot be null");
		} else if (arguments[0].asItemStack() != null) {
			outputs = new TweakerItemStack[] { arguments[0].asItemStack() };
		} else if (arguments[0].asArray() != null) {
			TweakerArray array = arguments[0].asArray();
			outputs = new TweakerItemStack[array.size()];
			for (int i = 0; i < array.size(); i++) {
				outputs[i] =
						notNull(array.get(i), "outputs cannot be null")
						.toItemStack("output element must be an item stack");
			}
		} else {
			throw new TweakerExecuteException("output must be an item stack or array of item stacks");
		}
		TweakerItem input =
				notNull(arguments[1], "input cannot be null")
				.toItem("input must be an item");
		int time =
				notNull(arguments[2], "time cannot be null")
				.toInt("time must be an int").get();
		int[] chances = new int[outputs.length];
		if (arguments.length < 4) {
			for (int i = 0; i < chances.length; i++) {
				chances[i] = 100;
			}
		} else {
			TweakerArray chancesArray =
					notNull(arguments[3], "chances cannot be null")
					.toArray("chances must be an array");
			for (int i = 0; i < chancesArray.size(); i++) {
				chances[i] =
						notNull(chancesArray.get(i), "chance cannot be null")
						.toInt("each chance must be an int").get();
			}
		}
		
		Tweaker.apply(new CentrifugeAddRecipeAction(outputs, input, time, chances));
		return null;
	}

	@Override
	public String toString() {
		return "centrifuge.addRecipe";
	}
}
