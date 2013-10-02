package stanhebben.minetweaker.api.value;

import java.util.List;
import net.minecraft.item.ItemStack;

public final class TweakerItemStackPatternFromItemPattern extends TweakerItemStackPattern {
	private final TweakerItemPattern pattern;
	
	public TweakerItemStackPatternFromItemPattern(TweakerItemPattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public boolean matches(ItemStack stack) {
		return pattern.matches(stack);
	}
	
	@Override
	public List<TweakerItem> getMatches() {
		return pattern.getMatches();
	}
	
	@Override
	public int getAmount() {
		return 1;
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
