package stanhebben.minetweaker.script.types;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerValue;

public class TweakerTypeInt extends TweakerType {
	public static final TweakerTypeInt INSTANCE = new TweakerTypeInt();
	
	private TweakerTypeInt() {}

	@Override
	public TweakerValue as(TweakerValue original) {
		if (original == null) throw new TweakerExecuteException("Cannot convert a null value to an int");
		
		return original.asInt();
	}
}
