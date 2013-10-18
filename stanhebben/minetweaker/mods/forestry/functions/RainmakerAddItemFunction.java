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
import stanhebben.minetweaker.mods.forestry.actions.RainmakerAddItemAction;

/**
 *
 * @author Stanneke
 */
public class RainmakerAddItemFunction extends TweakerFunction {
	public static final RainmakerAddItemFunction INSTANCE_RAIN = new RainmakerAddItemFunction(false);
	public static final RainmakerAddItemFunction INSTANCE_STOP = new RainmakerAddItemFunction(true);
	
	private final boolean reverse;
	
	private RainmakerAddItemFunction(boolean reverse) {
		this.reverse = reverse;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 3) throw new TweakerExecuteException(
				(reverse ? "rainmaker.addStopItem" : "rainmaker.addRainItem") + " requires 3 arguments");
		TweakerItem item =
				notNull(arguments[0], "item cannot be null")
				.toItem("item must be an item");
		int duration =
				notNull(arguments[1], "duration cannot be null")
				.toInt("duration must be an int").get();
		float speed =
				notNull(arguments[2], "speed cannot be null")
				.toFloat("speed must be a float").get();
		
		Tweaker.apply(new RainmakerAddItemAction(item, duration, speed, reverse));
		return null;
	}

	@Override
	public String toString() {
		return reverse ? "rainmaker.addStopItem" : "rainmaker.addRainItem";
	}
}
