package stanhebben.minetweaker.script.types;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerValue;

public class TweakerTypeByte extends TweakerType {
	public static final TweakerTypeByte INSTANCE = new TweakerTypeByte();
	
	private TweakerTypeByte() {}

	@Override
	public TweakerValue as(TweakerValue original) {
		if (original == null) throw new TweakerExecuteException("Cannot convert a null value to a byte");
		
		return original.asByte();
	}
}
