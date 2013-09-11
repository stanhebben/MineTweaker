package stanhebben.minetweaker.script.types;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerValue;

public class TweakerTypeDouble extends TweakerType {
	public static final TweakerTypeDouble INSTANCE = new TweakerTypeDouble();
	
	private TweakerTypeDouble() {}

	@Override
	public TweakerValue as(TweakerValue original) {
		if (original == null) throw new TweakerExecuteException("Cannot convert a null value to a double");
		
		return original.asDouble();
	}
}
