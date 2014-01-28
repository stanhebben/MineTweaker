/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import java.util.logging.Level;
import net.minecraft.util.WeightedRandomItem;
import powercrystals.core.random.WeightedRandomItemStack;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class SludgeBoilerListDrops extends TweakerFunction {
	public static final SludgeBoilerListDrops INSTANCE = new SludgeBoilerListDrops();
	
	private SludgeBoilerListDrops() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (MFRHacks.sludgeDrops == null) {
			Tweaker.log(Level.WARNING, "sludgeBoiler.listDrops unavailable");
		} else {
			for (WeightedRandomItem item : MFRHacks.sludgeDrops) {
				WeightedRandomItemStack itemStack = (WeightedRandomItemStack) item;
				Tweaker.log(Level.INFO, "  " + itemStack.getStack() + " (" + itemStack.itemWeight + ")");
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "sludgeBoiler.listDrops";
	}
}
