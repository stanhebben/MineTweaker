package stanhebben.minetweaker.api.functions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerItemStackPatternOnly;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 * Function instanced of itemstack.only. Creates an item stack pattern that
 * only matches when the corresponding match function returns true.
 * 
 * @author Stan Hebben
 */
public final class FunctionItemStackPatternOnly extends TweakerFunction {
	private final TweakerItemStackPattern pattern;
	
	public FunctionItemStackPatternOnly(TweakerItemStackPattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("stackpattern.only requires at least one argument");
		}
		
		TweakerValue test =
				notNull(arguments[0], "stackpattern.only argument requires one argument");
		return new TweakerItemStackPatternOnly(pattern, test);
	}

	@Override
	public String toString() {
		return "stackpattern.only";
	}
}
