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
import stanhebben.minetweaker.mods.mfr.FertilizableType;
import stanhebben.minetweaker.mods.mfr.action.FertilizerAddFertilizableAction;

/**
 *
 * @author Stanneke
 */
public class FertilizerAddFertilizableFunction extends TweakerFunction {
	public static final FertilizerAddFertilizableFunction INSTANCE = new FertilizerAddFertilizableFunction();
	
	private FertilizerAddFertilizableFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 3 || arguments.length > 4) throw new TweakerExecuteException("fertilizer.addFertilizable accepts 3-4 arguments");
		
		TweakerItem block =
				notNull(arguments[0], "block cannot be null")
				.toItem("block must be an item");
		FertilizerType fertilizer;
		String fertilizerString =
				notNull(arguments[1], "fertilizer type cannot be null")
				.toBasicString();
		if (fertilizerString.equals("any")) {
			fertilizer = null;
		} else if (fertilizerString.equals("normal")) {
			fertilizer = FertilizerType.GrowPlant;
		} else if (fertilizerString.equals("grass")) {
			fertilizer = FertilizerType.Grass;
		} else if (fertilizerString.equals("magic")) {
			fertilizer = FertilizerType.GrowMagicalCrop;
		} else {
			throw new TweakerExecuteException("Unknown fertilizer type: " + fertilizerString);
		}
		FertilizableType type;
		TweakerItem replacement = null;
		
		notNull(arguments[0], "fertilizer type cannot be null");
		String typeString = arguments[0].toBasicString();
		if (typeString.equals("bonemeal")) {
			type = FertilizableType.BONEMEAL;
		} else if (typeString.equals("replace")) {
			type = FertilizableType.REPLACE;
			if (arguments.length < 3) throw new TweakerExecuteException("replace method requires a replacement block");
			replacement =
					notNull(arguments[2], "replacement block cannot be null")
					.toItem("replacement block must be a block");
		} else {
			throw new TweakerExecuteException("Unknown fertilization method: " + typeString);
		}
		
		Tweaker.apply(new FertilizerAddFertilizableAction(block, replacement, fertilizer));
		return null;
	}

	@Override
	public String toString() {
		return "fertilizer.addFertilizable";
	}
}
