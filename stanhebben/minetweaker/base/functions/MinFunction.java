package stanhebben.minetweaker.base.functions;

import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;

public class MinFunction extends TweakerFunction {
	public static final MinFunction INSTANCE = new MinFunction();
	
	private MinFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			return null;
		} else {
			TweakerValue min = arguments[0];
			for (int i = 1; i < arguments.length; i++) {
				if (min.compare(arguments[i]) > 0) {
					min = arguments[i];
				}
			}
			
			return min;
		}
	}

	@Override
	public String toString() {
		return "MineTweaker:min";
	}

}
