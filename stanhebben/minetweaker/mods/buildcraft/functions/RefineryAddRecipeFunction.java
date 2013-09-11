/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFluidStack;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.actions.RefineryAddRecipeAction;

/**
 *
 * @author Stanneke
 */
public class RefineryAddRecipeFunction extends TweakerFunction {
	public static final RefineryAddRecipeFunction INSTANCE = new RefineryAddRecipeFunction();
	
	private RefineryAddRecipeFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 4) {
			throw new TweakerExecuteException("refinery.addRecipe requires 4 arguments");
		}
		
		TweakerFluidStack output =
				notNull(arguments[0], "the refinery.addRecipe output argument cannot be null")
				.toFluidStack("the refinery.addRecipe output must be a fluid stack");
		int energy =
				notNull(arguments[1], "the refinery.addRecipe energy argument cannot be null")
				.toInt("the refinery.addRecipe energy must be an int").get();
		int delay =
				notNull(arguments[2], "the refinery.addRecipe delay argument cannot be null")
				.toInt("the refinery.addRecipe delay must be an int").get();
		TweakerFluidStack input1 =
				notNull(arguments[3], "the refinery.addRecipe input argument cannot be null")
				.toFluidStack("the refinery.addRecipe input argument must be a fluid stack");
		TweakerFluidStack input2 = null;
		if (arguments.length > 4) {
			input2 =
					notNull(arguments[4], "the refinery.addRecipe input2 argument cannot be null")
					.toFluidStack("the refinery.addRecipe input2 argument must be a fluid stack");
		}
		
		Tweaker.apply(new RefineryAddRecipeAction(output, energy, delay, input1, input2));
		return null;
	}

	@Override
	public String toString() {
		return "buildcraft.refinery.addRecipe";
	}
}
