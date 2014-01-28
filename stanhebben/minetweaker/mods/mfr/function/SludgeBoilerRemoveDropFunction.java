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
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.MFRHacks;
import stanhebben.minetweaker.mods.mfr.action.SludgeBoilerRemoveDropAction;

/**
 *
 * @author Stanneke
 */
public class SludgeBoilerRemoveDropFunction extends TweakerFunction {
	public static final SludgeBoilerRemoveDropFunction INSTANCE = new SludgeBoilerRemoveDropFunction();
	
	private SludgeBoilerRemoveDropFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("sludgeBoiler.removeDrop requires 1 argument");
		TweakerItemStackPattern pattern =
				notNull(arguments[0], "pattern cannot be null")
				.toItemStackPattern("pattern must be an item stack pattern");
		
		if (MFRHacks.sludgeDrops == null) {
			Tweaker.log(Level.WARNING, "sludgeBoiler.removeDrop is unavailable");
		} else {
			List<Integer> locations = new ArrayList<Integer>();
			for (int i = MFRHacks.sludgeDrops.size(); i >= 0; i--) {
				WeightedRandomItemStack itemStack = (WeightedRandomItemStack) MFRHacks.sludgeDrops.get(i);
				if (pattern.matches(itemStack.getStack())) {
					locations.add(i);
				}
			}
			for (int i : locations) {
				Tweaker.apply(new SludgeBoilerRemoveDropAction(i));
			}
		}
		return null;
	}

	@Override
	public String toString() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
