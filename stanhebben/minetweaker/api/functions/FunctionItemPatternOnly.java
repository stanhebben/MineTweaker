package stanhebben.minetweaker.api.functions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerItemPatternOnly;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 * Function instanced on item.only. Indicated thas only items matches a certain
 * function should be matched.
 * 
 * @author Stan Hebben
 */
public final class FunctionItemPatternOnly extends TweakerFunction {
	private final TweakerItemPattern pattern;
	
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
