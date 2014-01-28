package stanhebben.minetweaker.base.values;

import stanhebben.minetweaker.MineTweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerBool;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.functions.BreakUndoFunction;
import stanhebben.minetweaker.base.functions.ClearFunction;
import stanhebben.minetweaker.base.functions.ModifyArmorDamageFunction;
import stanhebben.minetweaker.base.functions.RemoveFunction;
import stanhebben.minetweaker.base.functions.SetAdminsFunction;

public class MineTweakerValue extends TweakerValue {
	public static final MineTweakerValue INSTANCE = new MineTweakerValue();
	
	private MineTweakerValue() {}
	
	@Override
	public TweakerValue index(String index) throws TweakerExecuteException {
		switch (TweakerField.get(index)) {
			case CLEAR:
				return ClearFunction.INSTANCE;
			case REMOVE:
				return RemoveFunction.INSTANCE;
			case CANCLEAR:
				return TweakerBool.get(MineTweaker.instance.canClear());
			case BREAKUNDO:
				return BreakUndoFunction.INSTANCE;
			case SETADMINS:
				return SetAdminsFunction.INSTANCE;
			case MODIFYARMORDAMAGE:
				return ModifyArmorDamageFunction.INSTANCE;
		}
		throw new TweakerExecuteException("no member " + index + " in minetweaker");
	}
	
	@Override
	public String toString() {
		return "MineTweaker:minetweaker";
	}
}
