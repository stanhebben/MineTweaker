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
import stanhebben.minetweaker.mods.mfr.action.FruitPickerRemoveFruitAction;

/**
 *
 * @author Stanneke
 */
public class FruitPickerRemoveFruitFunction extends TweakerFunction {
	public static final FruitPickerRemoveFruitFunction INSTANCE = new FruitPickerRemoveFruitFunction();
	
	private FruitPickerRemoveFruitFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("fruitPicker.removeFruit requires 1 argument");
		TweakerItem item =
				notNull(arguments[0], "item cannot be null")
				.toItem("item must be an item");
		
		if (MFRHacks.fruitBlocks == null) {
			Tweaker.log(Level.WARNING, "fruitPicker.removeFruit unavailable");
		} else if (!MFRHacks.fruitBlocks.containsKey(item.getItemId())) {
			Tweaker.log(Level.WARNING, "no such fruit id: " + item.getItemId());
		} else {
			Tweaker.apply(new FruitPickerRemoveFruitAction(item));
		}
		return null;
	}

	@Override
	public String toString() {
		return "fruitPicker.removeFruit";
	}
}
