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
import stanhebben.minetweaker.mods.mfr.action.PlanterRemovePlantableAction;

/**
 *
 * @author Stanneke
 */
public class PlanterRemovePlantableFunction extends TweakerFunction {
	public static final PlanterRemovePlantableFunction INSTANCE = new PlanterRemovePlantableFunction();
	
	private PlanterRemovePlantableFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("planter.removePlantable requires 1 argument");
		TweakerItem item =
				notNull(arguments[0], "item cannot be null")
				.toItem("item must be an item");
		
		if (MFRHacks.plantables == null) {
			Tweaker.log(Level.WARNING, "planter.removePlantable is unavailable", null);
		} else if (!MFRHacks.plantables.containsKey(item.getItemId())) {
			Tweaker.log(Level.WARNING, "no such plantable: " + item.getDisplayName());
		} else {
			Tweaker.apply(new PlanterRemovePlantableAction(item));
		}
		return null;
	}

	@Override
	public String toString() {
		return "planter.removePlantable";
	}
}
