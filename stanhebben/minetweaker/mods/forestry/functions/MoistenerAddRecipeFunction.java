/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.actions.MoistenerAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class MoistenerAddRecipeFunction extends TweakerFunction {
	public static final MoistenerAddRecipeFunction INSTANCE = new MoistenerAddRecipeFunction();
	
	private MoistenerAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 3) throw new TweakerExecuteException("moistener.addRecipe requires 3 arguments");
		TweakerItemStack output =
				notNull(arguments[0], "output cannot be null")
				.toItemStack("output must be an item stack");
		TweakerItem input =
				notNull(arguments[1], "input cannot be null")
				.toItem("input must be an item");
		int time =
				notNull(arguments[2], "time cannot be null")
				.toInt("time must be an int").get();
		
		Tweaker.apply(new MoistenerAddRecipeAction(output, input, time));
		return null;
	}

	@Override
	public String toString() {
		return "moistener.addRecipe";
	}
}
