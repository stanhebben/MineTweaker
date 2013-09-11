package stanhebben.minetweaker.script.types;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerValue;

public class TweakerTypeString extends TweakerType {
	public static final TweakerTypeString INSTANCE = new TweakerTypeString();
	
	private TweakerTypeString() {}

	@Override
	public TweakerValue as(TweakerValue original) {
		if (original == null) throw new TweakerExecuteException("Cannot convert a null value to a string");
		
		return original.asString();
	}
}
