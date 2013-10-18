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
import stanhebben.minetweaker.mods.gregtech.actions.DistillationTowerAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class DistillationTowerAddRecipeFunction extends TweakerFunction {
	public static final DistillationTowerAddRecipeFunction INSTANCE = new DistillationTowerAddRecipeFunction();
	
	private DistillationTowerAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 5) throw new TweakerExecuteException("distillationTower.addRecipe requires 5 arguments");
		TweakerArray outputs =
				notNull(arguments[0], "output cannot be null")
				.toArray("output must be an item array");
		TweakerItemStack input =
				notNull(arguments[1], "input cannot be null")
				.toItemStack("input must be an item");
		int cells =
				notNull(arguments[2], "cells cannot be null")
				.toInt("cells must be an int").get();
		int duration =
				notNull(arguments[3], "duration cannot be null")
				.toInt("duration must be an int").get();
		int euPerTick =
				notNull(arguments[4], "eu per tick cannot be null")
				.toInt("eu per tick must be an int").get();
		
		if (outputs.size() == 0) throw new TweakerExecuteException("outputs cannot be an empty array");
		TweakerItemStack output1 =
				notNull(outputs.get(0), "output 1 cannot be null")
				.toItemStack("output 1 must be an item");
		TweakerItemStack output2 = outputs.size() < 2 || outputs.get(1) == null ? null :
				outputs.get(1).toItemStack("output 2 must be an item");
		TweakerItemStack output3 = outputs.size() < 3 || outputs.get(2) == null ? null :
				outputs.get(2).toItemStack("output 3 must be an item");
		TweakerItemStack output4 = outputs.size() < 4 || outputs.get(3) == null ? null :
				outputs.get(3).toItemStack("output 4 must be an item");
		
		Tweaker.apply(new DistillationTowerAddRecipeAction(
				output1, output2, output3, output4, 
				input, cells,
				duration, euPerTick));
		return null;
	}

	@Override
	public String toString() {
		return "distillationTower.addRecipe";
	}
}
