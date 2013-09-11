/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.api.functions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerBool;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class FunctionItemStackPatternMatches extends TweakerFunction {
	private TweakerItemStackPattern pattern;
	
	public FunctionItemStackPatternMatches(TweakerItemStackPattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) throw new TweakerExecuteException("stackpattern.matches requires one argument");
		TweakerValue value =
				notNull(arguments[0], "matches argument cannot be null");
		
		if (value.asItem() != null) {
			return TweakerBool.get(pattern.matches(value.asItem().make(1)));
		} else if (value.asItemStack() != null) {
			return TweakerBool.get(pattern.matches(value.asItemStack().get()));
		} else {
			throw new TweakerExecuteException("stackpattern.matches requires an item or item stack");
		}
	}

	@Override
	public String toString() {
		return "stackpattern.matches";
	}
}
