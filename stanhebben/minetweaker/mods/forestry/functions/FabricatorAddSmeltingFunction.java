/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerLiquidStack;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.actions.FabricatorAddSmeltingAction;

/**
 *
 * @author Stanneke
 */
public class FabricatorAddSmeltingFunction extends TweakerFunction {
	public static final FabricatorAddSmeltingFunction INSTANCE = new FabricatorAddSmeltingFunction();
	
	private FabricatorAddSmeltingFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 3) throw new TweakerExecuteException("fabricator.addSmelting must have 3 arguments");
		TweakerLiquidStack liquid =
				notNull(arguments[0], "liquid cannot be null")
				.toFluidStack("liquid must be a liquid stack");
		TweakerItem item =
				notNull(arguments[1], "input cannot be null")
				.toItem("input must be an item");
		int meltingPoint =
				notNull(arguments[2], "melting point cannot be null")
				.toInt("melting point must be an int").get();
		
		Tweaker.apply(new FabricatorAddSmeltingAction(liquid, item, meltingPoint));
		return null;
	}

	@Override
	public String toString() {
		return "fabricator.addSmelting";
	}
}
