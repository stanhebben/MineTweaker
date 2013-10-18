//#fileifndef MC152
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
import stanhebben.minetweaker.mods.gregtech.actions.CutterAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class CutterAddRecipeFunction extends TweakerFunction{
	public static final CutterAddRecipeFunction INSTANCE = new CutterAddRecipeFunction();
	
	private CutterAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 4) throw new TweakerExecuteException("cutter.addRecipe requires 4 arguments");
		TweakerItemStack output =
				notNull(arguments[0], "output cannot be null")
				.toItemStack("output must be an item");
		TweakerItemStack input =
				notNull(arguments[1], "input cannot be null")
				.toItemStack("input must be an item");
		int duration =
				notNull(arguments[2], "duration cannot be null")
				.toInt("duration must be an int").get();
		int euPerTick =
				notNull(arguments[3], "eu per tick cannot be null")
				.toInt("eu per tick must be an int").get();
		
		Tweaker.apply(new CutterAddRecipeAction(output, input, duration, euPerTick));
		return null;
	}

	@Override
	public String toString() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
