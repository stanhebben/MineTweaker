package stanhebben.minetweaker.script.types;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerValue;

public class TweakerTypeFloat extends TweakerType {
	public static final TweakerTypeFloat INSTANCE = new TweakerTypeFloat();
	
	private TweakerTypeFloat() {}

	@Override
	public TweakerValue as(TweakerValue original) {
		if (original == null) throw new TweakerExecuteException("Cannot convert a null value to a float");
		
		return original.asFloat();
	}
}
