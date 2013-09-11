package stanhebben.minetweaker.base.functions;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;

public class MaxFunction extends TweakerFunction {
	public static final MaxFunction INSTANCE = new MaxFunction();
	
	private MaxFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			return null;
		} else {
			TweakerValue max = arguments[0];
			for (int i = 1; i < arguments.length; i++) {
				if (max.compare(arguments[i]) < 0) {
					max = arguments[i];
				}
			}
			
			return max;
		}
	}

	@Override
	public String toString() {
		return "MineTweaker:max";
	}
}
