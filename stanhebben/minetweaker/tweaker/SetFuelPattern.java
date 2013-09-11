package stanhebben.minetweaker.tweaker;

import stanhebben.minetweaker.api.value.TweakerItemStackPattern;

public class SetFuelPattern {
	private TweakerItemStackPattern pattern;
	private int value;
	
	public SetFuelPattern(TweakerItemStackPattern pattern, int value) {
		this.pattern = pattern;
		this.value = value;
	}
	
	public TweakerItemStackPattern getPattern() {
		return pattern;
	}
	
	public int getValue() {
		return value;
	}
}
