/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.MFRHacks;
import stanhebben.minetweaker.mods.mfr.action.MiningLaserRemovePreferredOreAction;

/**
 *
 * @author Stanneke
 */
public class MiningLaserRemovePreferredOreFunction extends TweakerFunction {
	public static final MiningLaserRemovePreferredOreFunction INSTANCE = new MiningLaserRemovePreferredOreFunction();
	
	private MiningLaserRemovePreferredOreFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 1 || arguments.length > 2) throw new TweakerExecuteException("miningLaser.removePreferredOre requires 1-2 arguments");
		
		if (MFRHacks.laserPreferredOres == null) {
			Tweaker.log(Level.WARNING, "miningLaser.removePreferredOre unavailable");
			return null;
		}
		
		if (arguments.length == 1) {
			TweakerItemPattern pattern =
					notNull(arguments[0], "pattern cannot be null")
					.toItemPattern("pattern must be an item pattern");
			
			for (int color : MFRHacks.laserPreferredOres.keySet()) {
				List<ItemStack> items = MFRHacks.laserPreferredOres.get(color);
				List<Integer> toRemove = new ArrayList<Integer>();
				for (int i = items.size() - 1; i >= 0; i--) {
					if (pattern.matches(items.get(i))) toRemove.add(i);
				}
				for (int i : toRemove) {
					Tweaker.apply(new MiningLaserRemovePreferredOreAction(color, i));
				}
			}
		} else {
			int color =
					notNull(arguments[0], "color cannot be null")
					.toInt("color must be an int").get();
			TweakerItemPattern pattern =
					notNull(arguments[1], "pattern must be an item pattern")
					.toItemPattern("pattern must be an item pattern");
			
			List<ItemStack> items = MFRHacks.laserPreferredOres.get(color);
			List<Integer> toRemove = new ArrayList<Integer>();
			for (int i = items.size() - 1; i >= 0; i--) {
				if (pattern.matches(items.get(i))) toRemove.add(i);
			}
			for (int i : toRemove) {
				Tweaker.apply(new MiningLaserRemovePreferredOreAction(color, i));
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "miningLaser.removePreferredOre";
	}
}
