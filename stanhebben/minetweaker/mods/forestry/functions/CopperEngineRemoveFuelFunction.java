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
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.forestry.actions.CopperEngineRemoveFuelAction;

/**
 *
 * @author Stanneke
 */
public class CopperEngineRemoveFuelFunction extends TweakerFunction {
	public static final CopperEngineRemoveFuelFunction INSTANCE = new CopperEngineRemoveFuelFunction();
	
	private CopperEngineRemoveFuelFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("peatFiredEngine.removeFuel requires 1 argument");
		TweakerItem item =
				notNull(arguments[0], "fuel cannot be null")
				.toItem("fuel must be an item");
		
		Tweaker.apply(new CopperEngineRemoveFuelAction(item));
		return null;
	}

	@Override
	public String toString() {
		return "peatFiredEngine.removeFuel";
	}
}
