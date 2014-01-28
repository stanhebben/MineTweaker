/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.action.SludgeBoilerAddDropAction;

/**
 *
 * @author Stanneke
 */
public class SludgeBoilerAddDropFunction extends TweakerFunction {
	public static final SludgeBoilerAddDropFunction INSTANCE = new SludgeBoilerAddDropFunction();
	
	private SludgeBoilerAddDropFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 2) throw new TweakerExecuteException("sludgeBoiler.addDrop requires 2 arguments");
		int weight =
				notNull(arguments[0], "weight cannot be null")
				.toInt("weight must be an int").get();
		TweakerItemStack item =
				notNull(arguments[1], "item cannot be null")
				.toItemStack("item must be an item stack");
		
		Tweaker.apply(new SludgeBoilerAddDropAction(item, weight));
		return null;
	}

	@Override
	public String toString() {
		return "sludgeBoiler.addDrop";
	}
}
