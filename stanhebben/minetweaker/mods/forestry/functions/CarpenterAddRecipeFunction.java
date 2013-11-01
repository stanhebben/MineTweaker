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
import stanhebben.minetweaker.mods.forestry.actions.CarpenterAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class CarpenterAddRecipeFunction extends TweakerFunction {
	public static final CarpenterAddRecipeFunction INSTANCE = new CarpenterAddRecipeFunction();
	
	private CarpenterAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2) throw new TweakerExecuteException("carpenter.addRecipe requires at least 2 arguments");
		TweakerItemStack output =
				notNull(arguments[0], "output cannot be null")
				.toItemStack("output must be an item stack");
		TweakerArray recipe =
				notNull(arguments[1], "recipe cannot be null")
				.toArray("recipe must be a recipe item array");
		TweakerItem box = arguments.length < 3 || arguments[2] == null ? null :
				arguments[2].toItem("box must be an item");
		int time = arguments.length < 4 || arguments[3] == null ? 0 :
				arguments[3].toInt("time must be an int").get();
		TweakerLiquidStack liquid = arguments.length < 5 || arguments[4] == null ? null :
				arguments[4].toFluidStack("liquid must be a liquid stack");
		
		Object[] recipeArray = new Object[recipe.size()];
		for (int i = 0; i < recipeArray.length; i++) {
			recipeArray[i] = recipe.get(i).toRecipeItem("recipe contents must all be valid recipe items");
		}
		Tweaker.apply(new CarpenterAddRecipeAction(output, recipeArray, box, time, liquid));
		return null;
	}

	@Override
	public String toString() {
		return "carpenter.addRecipe";
	}
}
