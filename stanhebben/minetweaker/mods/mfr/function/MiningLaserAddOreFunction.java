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
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.action.MiningLaserAddOreAction;

/**
 *
 * @author Stanneke
 */
public class MiningLaserAddOreFunction extends TweakerFunction {
	public static final MiningLaserAddOreFunction INSTANCE = new MiningLaserAddOreFunction();
	
	private MiningLaserAddOreFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 2) throw new TweakerExecuteException("miningLaser.addOre requires 2 arguments");
		TweakerItem item =
				notNull(arguments[0], "ore cannot be null")
				.toItem("ore must be an item");
		int weight =
				notNull(arguments[1], "weight cannot be null")
				.toInt("weight must be an int").get();
		
		Tweaker.apply(new MiningLaserAddOreAction(item, weight));
		return null;
	}

	@Override
	public String toString() {
		return "miningLaser.addOre";
	}
}
