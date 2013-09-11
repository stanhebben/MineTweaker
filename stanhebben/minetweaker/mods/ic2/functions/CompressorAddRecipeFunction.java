//#fileifdef MC152
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.ic2.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.ic2.actions.CompressorAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class CompressorAddRecipeFunction extends TweakerFunction {
	public static final CompressorAddRecipeFunction INSTANCE = new CompressorAddRecipeFunction();
	
	private CompressorAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2) {
			throw new TweakerExecuteException("compressor.addRecipe requires two arguments");
		}
		
		TweakerItemStack output =
				notNull(arguments[0], "compressor.addRecipe output argument cannot be null")
				.toItemStack("compressor.addRecipe output argument must be an item stack");
		TweakerItem input =
				notNull(arguments[1], "compressor.addRecipe input argument cannot be null")
				.toItem("compressor.addRecipe input argument must be an item");
		Tweaker.apply(new CompressorAddRecipeAction(output, input));
		return null;
	}

	@Override
	public String toString() {
		return "ic2.compressor.addRecipe";
	}
}
