package stanhebben.minetweaker.api.value;

import net.minecraft.item.ItemStack;

public class TweakerItemStackPatternMultiItemPattern extends TweakerItemStackPattern {
	private TweakerItemPattern pattern;
	private int amount;
	
	public TweakerItemStackPatternMultiItemPattern(TweakerItemPattern pattern, int amount) {
		this.pattern = pattern;
	}

	@Override
	public boolean matches(ItemStack stack) {
		if (stack == null) return false;
		return stack.stackSize == amount && pattern.matches(stack);
	}
	
	@Override
	public String toPatternString() {
		return pattern.toPatternString() + " * " + amount;
	}

	@Override
	public String toString() {
		return pattern.toString();
	}
}
