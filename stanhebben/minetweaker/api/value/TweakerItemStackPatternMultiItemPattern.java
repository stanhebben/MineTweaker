package stanhebben.minetweaker.api.value;

import java.util.List;
import net.minecraft.item.ItemStack;

public final class TweakerItemStackPatternMultiItemPattern extends TweakerItemStackPattern {
	private final TweakerItemPattern pattern;
	private final int amount;
	
	public TweakerItemStackPatternMultiItemPattern(TweakerItemPattern pattern, int amount) {
		this.pattern = pattern;
		this.amount = amount;
	}

	@Override
	public boolean matches(ItemStack stack) {
		if (stack == null) return false;
		return stack.stackSize == amount && pattern.matches(stack);
	}
	
	@Override
	public List<TweakerItem> getMatches() {
		return pattern.getMatches();
	}
	
	@Override
	public int getAmount() {
		return amount;
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
