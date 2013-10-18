/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.gregtech.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.gregtech.actions.ChemicalAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class ChemicalAddRecipeFunction extends TweakerFunction {
	public static final ChemicalAddRecipeFunction INSTANCE = new ChemicalAddRecipeFunction();
	
	private ChemicalAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 4) throw new TweakerExecuteException("chemical.addRecipe requires 4 arguments");
		TweakerItemStack output =
				notNull(arguments[0], "output cannot be null")
				.toItemStack("output must be an item");
		TweakerItemStack input1 =
				notNull(arguments[1], "input 1 cannot be null")
				.toItemStack("input 1 must be an item");
		TweakerItemStack input2 =
				notNull(arguments[2], "input 2 cannot be null")
				.toItemStack("input 2 must be an item");
		int duration =
				notNull(arguments[3], "duration cannot be null")
				.toInt("duration must be an integer").get();
		
		Tweaker.apply(new ChemicalAddRecipeAction(output, input1, input2, duration));
		return null;
	}

	@Override
	public String toString() {
		return "chemical.addRecipe";
	}
}
