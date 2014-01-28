/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import powercrystals.minefactoryreloaded.api.FertilizerType;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.action.FertilizerAddFertilizerAction;

/**
 *
 * @author Stanneke
 */
public class FertilizerAddFertilizerFunction extends TweakerFunction {
	public static final FertilizerAddFertilizerFunction INSTANCE = new FertilizerAddFertilizerFunction();
	
	private FertilizerAddFertilizerFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 2) throw new TweakerExecuteException("fertilizer.addFertilizer requires 2 arguments");
		TweakerItem item =
				notNull(arguments[0], "fertilizer cannot be null")
				.toItem("fertilizer must be an item");
		String type =
				notNull(arguments[1], "type cannot be null")
				.toBasicString();
		
		FertilizerType fertilizerType;
		if (type.equals("grass")) {
			fertilizerType = FertilizerType.Grass;
		} else if (type.equals("normal")) {
			fertilizerType = FertilizerType.GrowPlant;
		} else if (type.equals("magic")) {
			fertilizerType = FertilizerType.GrowMagicalCrop;
		} else {
			throw new TweakerExecuteException("Unknown fertilizer type: " + type);
		}
		
		Tweaker.apply(new FertilizerAddFertilizerAction(item, fertilizerType));
		return null;
	}

	@Override
	public String toString() {
		return "fertilizer.addFertilizer";
	}
}
