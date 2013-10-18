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
import stanhebben.minetweaker.mods.forestry.actions.RainmakerRemoveItemAction;

/**
 *
 * @author Stanneke
 */
public class RainmakerRemoveItemFunction extends TweakerFunction {
	public static final RainmakerRemoveItemFunction INSTANCE = new RainmakerRemoveItemFunction();
	
	private RainmakerRemoveItemFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("rainmaker.removeItem requires 1 argument");
		TweakerItem item =
				notNull(arguments[0], "item cannot be null")
				.toItem("item must be an item");
		
		Tweaker.apply(new RainmakerRemoveItemAction(item.make(1)));
		return null;
	}

	@Override
	public String toString() {
		return "rainmaker.removeItem";
	}
}
