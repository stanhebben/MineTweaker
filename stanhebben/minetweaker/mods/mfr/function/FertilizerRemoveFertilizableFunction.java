/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import java.util.logging.Level;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.MFRHacks;
import stanhebben.minetweaker.mods.mfr.action.FertilizerRemoveFertilizableAction;

/**
 *
 * @author Stanneke
 */
public class FertilizerRemoveFertilizableFunction extends TweakerFunction {
	public static final FertilizerRemoveFertilizableFunction INSTANCE = new FertilizerRemoveFertilizableFunction();
	
	private FertilizerRemoveFertilizableFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("fertilizer.removeFertilizable requires 1 argument");
		TweakerItem item =
				notNull(arguments[0], "item cannot be null")
				.toItem("item must be an item");
		
		if (MFRHacks.fertilizables == null) {
			Tweaker.log(Level.WARNING, "fertilizer.removeFertilizable is unavailable", null);
		} else if (!MFRHacks.fertilizables.containsKey(item.getItemId())) {
			Tweaker.log(Level.WARNING, "There is no such fertilizer: " + item.getDisplayName());
		} else {
			Tweaker.apply(new FertilizerRemoveFertilizableAction(item));
		}
		return null;
	}

	@Override
	public String toString() {
		return "fertilizer.removeFertilizable";
	}
}
