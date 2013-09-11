package stanhebben.minetweaker.api.functions;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerString;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 * Implements the substring method of string.
 * 
 * @author Stan Hebben
 */
public class FunctionSubstring extends TweakerFunction {
	private String value;

	public FunctionSubstring(String value) {
		this.value = value;
	}
	
	@Override
	public TweakerValue call(TweakerNameSpace nameSpace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new IllegalArgumentException("substring method requires at least one argument");
		} else if (arguments.length == 1) {
			return new TweakerString(
							value.substring(arguments[0].toBasicInt()));
		} else {
			return new TweakerString(
							value.substring(arguments[0].toBasicInt(),
							arguments[1].toBasicInt()));
		}
	}

	@Override
	public String toString() {
		return "String.substring";
	}
}
