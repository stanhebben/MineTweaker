package stanhebben.minetweaker.script.types;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerValue;

public class TweakerTypeBool extends TweakerType {
	public static final TweakerTypeBool INSTANCE = new TweakerTypeBool();
	
	private TweakerTypeBool() {}

	@Override
	public TweakerValue as(TweakerValue original) {
		if (original == null) throw new TweakerExecuteException("Cannot convert a null value to a bool");
		
		return original.asBool();
	}
}
