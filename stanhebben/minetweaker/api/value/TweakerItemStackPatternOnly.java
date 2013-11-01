package stanhebben.minetweaker.api.value;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.Tweaker;

/**
 * Implements itemstack.only values.
 * 
 * @author Stan Hebben
 */
public final class TweakerItemStackPatternOnly extends TweakerItemStackPattern {
	private final TweakerItemStackPattern base;
	private final TweakerValue test;
	
	public TweakerItemStackPatternOnly(TweakerItemStackPattern base, TweakerValue test) {
		this.base = base;
		this.test = test;
	}

	@Override
	public boolean matches(ItemStack item) {
		return base.matches(item) && test.call(Tweaker.getGlobalWrapped(), new TweakerItemStack(item)).toBasicBool();
	}
	
	@Override
	public List<TweakerItem> getMatches() {
		List<TweakerItem> baseMatches = base.getMatches();
		List<TweakerItem> result = new ArrayList<TweakerItem>();
		for (TweakerItem match : baseMatches) {
			if (test.call(Tweaker.getGlobalWrapped(), match.asItemStack()).toBasicBool()) {
				result.add(match);
			}
		}
		return result;
	}
	
	@Override
	public int getAmount() {
		return base.getAmount();
	}

	@Override
	public String toPatternString() {
		return base.toPatternString() + ".only(<function>);";
	}

	@Override
	public String toString() {
		return "pattern:" + toPatternString();
	}
}
