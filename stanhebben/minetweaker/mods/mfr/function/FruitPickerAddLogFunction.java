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
import stanhebben.minetweaker.mods.mfr.action.FruitPickerAddLogAction;

/**
 *
 * @author Stanneke
 */
public class FruitPickerAddLogFunction extends TweakerFunction {
	public static final FruitPickerAddLogFunction INSTANCE = new FruitPickerAddLogFunction();
	
	private FruitPickerAddLogFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("fruitPicker.addLog requires 1 argument");
		TweakerItem item =
				notNull(arguments[0], "log cannot be null")
				.toItem("log must be an item");
		
		Tweaker.apply(new FruitPickerAddLogAction(item.getItemId()));
		return null;
	}

	@Override
	public String toString() {
		return "fruitPicker.addLog";
	}
}
