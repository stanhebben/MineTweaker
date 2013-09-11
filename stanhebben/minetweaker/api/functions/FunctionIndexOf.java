package stanhebben.minetweaker.api.functions;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 * Implements the indexOf method in strings.
 * 
 * @author Stan Hebben
 */
public class FunctionIndexOf extends TweakerFunction {
	private String value;
	
	public FunctionIndexOf(String value) {
		this.value = value;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new IllegalArgumentException("At least one argument needed for indexOf method");
		} else {
			return new TweakerInt(value.indexOf(arguments[0].toBasicString()));
		}
	}

	@Override
	public String toString() {
		return "String.indexOf";
	}
}
