/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import powercrystals.core.random.WeightedRandomItemStack;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.MFRHacks;
import stanhebben.minetweaker.mods.mfr.action.MiningLaserRemoveOreAction;

/**
 *
 * @author Stanneke
 */
public class MiningLaserRemoveOreFunction extends TweakerFunction {
	public static final MiningLaserRemoveOreFunction INSTANCE = new MiningLaserRemoveOreFunction();
	
	private MiningLaserRemoveOreFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("miningLaser.removeOre requires 1 argument");
		TweakerItemPattern pattern =
				notNull(arguments[0], "pattern cannot be null")
				.toItemPattern("pattern must be an item pattern");
		
		if (MFRHacks.laserOres == null) {
			Tweaker.log(Level.WARNING, "miningLaser.removeOre is unavailable");
		} else {
			List<Integer> indices = new ArrayList<Integer>();
			for (int i = MFRHacks.laserOres.size() - 1; i >= 0; i--) {
				WeightedRandomItemStack itemStack = (WeightedRandomItemStack) MFRHacks.laserOres.get(i);
				if (pattern.matches(itemStack.getStack())) indices.add(i);
			}
			for (int i : indices) {
				Tweaker.apply(new MiningLaserRemoveOreAction(i));
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "miningLaser.removeOre";
	}
}
