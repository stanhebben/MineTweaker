/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.function;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.mfr.MFRHacks;
import stanhebben.minetweaker.mods.mfr.action.FruitPickerRemoveLogAction;

/**
 *
 * @author Stanneke
 */
public class FruitPickerRemoveLogFunction extends TweakerFunction {
	public static final FruitPickerRemoveLogFunction INSTANCE = new FruitPickerRemoveLogFunction();
	
	private FruitPickerRemoveLogFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("fruitPicker.removeLog requires 1 argument");
		
		TweakerItemPattern pattern =
				notNull(arguments[0], "pattern cannot be null")
				.toItemPattern("pattern must be an item pattern");
		
		if (MFRHacks.fruitLogBlocks == null) {
			Tweaker.log(Level.WARNING, "fruitPicker.removeLog is unavailable");
		} else {
			List<Integer> ids = MFRHacks.fruitLogBlocks;
			List<Integer> toRemove = new ArrayList<Integer>();
			for (int i = ids.size() - 1; i >= 0; i--) {
				if (pattern.matches(ids.get(i))) toRemove.add(i);
			}
			for (int i : toRemove) {
				Tweaker.apply(new FruitPickerRemoveLogAction(i));
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "fruitPicker.removeLog";
	}
}
