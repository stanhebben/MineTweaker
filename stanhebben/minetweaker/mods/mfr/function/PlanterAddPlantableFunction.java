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
import stanhebben.minetweaker.mods.mfr.PrePlantAction;
import stanhebben.minetweaker.mods.mfr.action.PlanterAddPlantableAction;

/**
 *
 * @author Stanneke
 */
public class PlanterAddPlantableFunction extends TweakerFunction {
	public static final PlanterAddPlantableFunction INSTANCE = new PlanterAddPlantableFunction();
	
	private PlanterAddPlantableFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 1 || arguments.length > 3) throw new TweakerExecuteException("planter.addPlantable accepts 1-3 arguments");
		TweakerItem seed =
				notNull(arguments[0], "seed cannot be null")
				.toItem("seed must be an item");
		TweakerItem plant = arguments.length < 2 || arguments[1] == null ? seed :
				arguments[1].toItem("plant must be an item");
		PrePlantAction preAction;
		if (arguments.length < 3 || arguments[2] == null) {
			preAction = PrePlantAction.NONE;
		} else if (arguments[2].toBasicString().equals("till")) {
			preAction = PrePlantAction.TILL;
		} else {
			throw new TweakerExecuteException("Unknown pre planting action: " + arguments[2]);
		}
		
		Tweaker.apply(new PlanterAddPlantableAction(seed, plant, preAction));
		return null;
	}

	@Override
	public String toString() {
		return "planter.addPlantable";
	}
}
