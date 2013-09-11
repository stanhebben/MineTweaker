package stanhebben.minetweaker.script.types;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerValue;

public class TweakerTypeShort extends TweakerType {
	public static final TweakerTypeShort INSTANCE = new TweakerTypeShort();
	
	private TweakerTypeShort() {}

	@Override
	public TweakerValue as(TweakerValue original) {
		if (original == null) throw new TweakerExecuteException("Cannot convert a null value to a short");
		
		return original.asShort();
	}
}
