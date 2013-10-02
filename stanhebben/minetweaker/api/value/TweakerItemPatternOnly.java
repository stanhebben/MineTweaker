/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.api.value;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.IPatternListener;
import stanhebben.minetweaker.api.Tweaker;

/**
 *
 * @author Stanneke
 */
public class TweakerItemPatternOnly extends TweakerItemPattern {
	private final TweakerItemPattern base;
	private final TweakerValue test;
	
	public TweakerItemPatternOnly(TweakerItemPattern base, TweakerValue test) {
		this.base = base;
		this.test = test;
	}
	
	@Override
	public boolean matches(TweakerItem item) {
		if (item == null) return false;
		return base.matches(item) && test.call(Tweaker.getGlobalWrapped(), item).toBasicBool();
	}

	@Override
	public boolean matches(ItemStack item) {
		if (item == null) return false;
		return base.matches(item) && test.call(Tweaker.getGlobalWrapped(), TweakerItem.get(item)).toBasicBool();
	}

	@Override
	public boolean matches(Object object) {
		if (object instanceof ItemStack) {
			return matches((ItemStack) object);
		} else {
			return false;
		}
	}

	@Override
	public boolean matches(int id) {
		return matches(new TweakerItemSimple(id));
	}

	@Override
	public boolean matches(int id, int meta) {
		return matches(new TweakerItemSub(id, meta));
	}

	@Override
	public String toPatternString() {
		return base.toPatternString() + ".only(<function>);";
	}

	@Override
	public String toString() {
		return "pattern:" + toPatternString();
	}

	@Override
	public List<TweakerItem> getMatches() {
		List<TweakerItem> baseMatches = base.getMatches();
		List<TweakerItem> result = new ArrayList<TweakerItem>();
		for (TweakerItem item : baseMatches) {
			if (matches(item)) result.add(item);
		}
		return result;
	}

	@Override
	public void addListener(IPatternListener listener) {
		base.addListener(new FilteredListener(listener));
	}

	@Override
	public void removeListener(IPatternListener listener) {
		base.removeListener(new FilteredListener(listener));
	}
	
	private class FilteredListener implements IPatternListener {
		private final IPatternListener next;
		
		FilteredListener(IPatternListener next) {
			this.next = next;
		}

		public void onPatternResultAdded(TweakerItem item) {
			if (matches(item.make(1))) {
				next.onPatternResultAdded(item);
			}
		}

		public void onPatternResultRemoved(TweakerItem item) {
			if (matches(item.make(1))) {
				next.onPatternResultRemoved(item);
			}
		}
	}
}
