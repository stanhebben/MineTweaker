package stanhebben.minetweaker.base.functions;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;

public class RemoveFunction extends TweakerFunction {
	public static final RemoveFunction INSTANCE = new RemoveFunction();
	
	private RemoveFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		TweakerItemStackPattern pattern = 
				notNull(arguments[0], "cannot use null as remove argument")
				.toItemStackPattern("remove argument must be an item, item stack or item stack pattern");
		
		RemoveRecipesFunction.INSTANCE.call(namespace, arguments);
		Tweaker.remove(pattern);
		
		return null;
	}

	@Override
	public String toString() {
		return "MineTweaker:minetweaker.remove";
	}
}
