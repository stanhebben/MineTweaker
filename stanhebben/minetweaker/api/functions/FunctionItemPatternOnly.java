/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.api.functions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerItemPatternOnly;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class FunctionItemPatternOnly extends TweakerFunction {
	private TweakerItemPattern pattern;
	
	public FunctionItemPatternOnly(TweakerItemPattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("pattern.only requires at least one argument");
		}
		
		TweakerValue test =
				notNull(arguments[0], "pattern.only argument requires one argument");
		return new TweakerItemPatternOnly(pattern, test);
	}

	@Override
	public String toString() {
		return "pattern.only";
	}
}
