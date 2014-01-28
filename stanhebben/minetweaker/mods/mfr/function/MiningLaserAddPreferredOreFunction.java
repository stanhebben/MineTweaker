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
import stanhebben.minetweaker.mods.mfr.action.MiningLaserAddPreferredOreAction;

/**
 *
 * @author Stanneke
 */
public class MiningLaserAddPreferredOreFunction extends TweakerFunction {
	public static final MiningLaserAddPreferredOreFunction INSTANCE = new MiningLaserAddPreferredOreFunction();
	
	private MiningLaserAddPreferredOreFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 2) throw new TweakerExecuteException("miningLaser.addPreferredOre requires 2 arguments");
		int color =
				notNull(arguments[0], "color cannot be null")
				.toInt("color must be an int").get();
		if (color < 0 || color >= 16) throw new TweakerExecuteException("color must be a value of 0-15");
		TweakerItem item =
				notNull(arguments[1], "ore cannot be null")
				.toItem("ore must be an item");
		
		Tweaker.apply(new MiningLaserAddPreferredOreAction(color, item));
		return null;
	}

	@Override
	public String toString() {
		return "miningLaser.addPreferredOre";
	}
}
