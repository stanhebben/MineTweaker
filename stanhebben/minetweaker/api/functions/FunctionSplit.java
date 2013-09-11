package stanhebben.minetweaker.api.functions;

import stanhebben.minetweaker.util.Arrays2;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerArrayValue;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerString;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 * Implements the split method of string.
 * 
 * @author Stan Hebben
 */
public class FunctionSplit extends TweakerFunction {
	private String value;
	
	public FunctionSplit(String value) {
		this.value = value;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new IllegalArgumentException("Missing argument for split method");
		} else {
			String[] result = Arrays2.split(value, arguments[0].toBasicString());
			TweakerArray array = new TweakerArrayValue();
			for (String s : result) {
				array.addAssign(new TweakerString(s));
			}
			return array;
		}
	}

	@Override
	public String toString() {
		return "String.split";
	}
}
