package stanhebben.minetweaker.script.types;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerValue;

public class TweakerTypeLong extends TweakerType {
	public static final TweakerTypeLong INSTANCE = new TweakerTypeLong();
	
	private TweakerTypeLong() {}

	@Override
	public TweakerValue as(TweakerValue original) {
		if (original == null) throw new TweakerExecuteException("Cannot convert a null value to a long");
		
		return original.asLong();
	}
}
