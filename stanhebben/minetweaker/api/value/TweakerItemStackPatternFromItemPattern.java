package stanhebben.minetweaker.api.value;

import net.minecraft.item.ItemStack;

public class TweakerItemStackPatternFromItemPattern extends TweakerItemStackPattern {
	private TweakerItemPattern pattern;
	
	public TweakerItemStackPatternFromItemPattern(TweakerItemPattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public boolean matches(ItemStack stack) {
		return pattern.matches(stack);
	}
	
	@Override
	public String toPatternString() {
		return pattern.toPatternString();
	}

	@Override
	public String toString() {
		return pattern.toString();
	}
}
