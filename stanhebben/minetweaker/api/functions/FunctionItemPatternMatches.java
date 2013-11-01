package stanhebben.minetweaker.api.functions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerBool;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 * Function instanced on pattern.matches. Checks if an item matches this pattern.
 * 
 * @author Stan Hebben
 */
public final class FunctionItemPatternMatches extends TweakerFunction {
	private final TweakerItemPattern pattern;
	
	public FunctionItemPatternMatches(TweakerItemPattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) throw new TweakerExecuteException("pattern.matches requires one argument");
		TweakerValue value =
				notNull(arguments[0], "matches argument cannot be null");
		
		if (value.asItem() != null) {
			return TweakerBool.get(pattern.matches(value.asItem()));
		} else if (value.asItemStack() != null) {
			return TweakerBool.get(pattern.matches(value.asItemStack().get()));
		} else {
			throw new TweakerExecuteException("pattern.matches requires an item or item stack");
		}
	}

	@Override
	public String toString() {
		return "pattern.matches";
	}
}
