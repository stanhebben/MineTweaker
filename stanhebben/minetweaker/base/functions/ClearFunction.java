package stanhebben.minetweaker.base.functions;

import stanhebben.minetweaker.MineTweaker;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.ClearAction;

public class ClearFunction extends TweakerFunction {
	public static final ClearFunction INSTANCE = new ClearFunction();
	
	private ClearFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (!MineTweaker.instance.canClear()) {
			throw new TweakerExecuteException("Cannot perform clear because a non-undoable action has been executed");
		} else {
			Tweaker.apply(new ClearAction());
			return null;
		}
	}

	@Override
	public String toString() {
		return "MineTweaker:minetweaker.clear";
	}
}
