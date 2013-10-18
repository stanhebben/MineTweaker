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
import stanhebben.minetweaker.api.value.TweakerFluidStack;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.actions.FabricatorAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class FabricatorAddRecipeFunction extends TweakerFunction {
	public static final FabricatorAddRecipeFunction INSTANCE = new FabricatorAddRecipeFunction();
	
	private FabricatorAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 3) throw new TweakerExecuteException("fabricator.addRecipe requires at least 3 arguments");
		TweakerItemStack output =
				notNull(arguments[0], "output cannot be null")
				.toItemStack("output must be an item stack");
		TweakerArray input =
				notNull(arguments[1], "input cannot be null")
				.toArray("input must be a twodimensional array");
		TweakerFluidStack liquid =
				notNull(arguments[2], "liquid cannot be null")
				.toFluidStack("liquid must be a liquid stack");
		TweakerItem cast = arguments.length < 4 || arguments[3] == null ? null :
				arguments[3].toItem("cast must be an item");
		
		Object[] pattern = new Object[9];
		if (input.size() > 3) throw new TweakerExecuteException("input cannot have more than 3 rows");
		for (int i = 0; i < input.size(); i++) {
			TweakerArray row =
					notNull(input.get(i), "input cannot be null")
					.toArray("input must be a recipe item array");
			if (row.size() > 3) throw new TweakerExecuteException("a row can contain up to 3 elements");
			for (int j = 0; j < row.size(); j++) {
				pattern[3 * i + j] = row.get(j) == null ? null :
						row.get(j).toRecipeItem("each input element must be null or a valid recipe item");
			}
		}
		Tweaker.apply(new FabricatorAddRecipeAction(output, pattern, liquid, cast));
		return null;
	}

	@Override
	public String toString() {
		return "fabricator.addRecipe";
	}
}
