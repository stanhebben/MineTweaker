/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.api.value;

import java.util.Iterator;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.IPatternListener;
import stanhebben.minetweaker.api.Tweaker;

/**
 *
 * @author Stanneke
 */
public class TweakerItemPatternOnly extends TweakerItemPattern {
	private TweakerItemPattern base;
	private TweakerValue test;
	
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
	public Iterator<TweakerItem> getMatches() {
		Iterator<TweakerItem> baseIterator = base.getMatches();
		return new FilteredIterator(baseIterator);
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
		private IPatternListener next;
		
		public FilteredListener(IPatternListener next) {
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
	
	private class FilteredIterator implements Iterator<TweakerItem> {
		private Iterator<TweakerItem> base;
		private TweakerItem next;
		
		public FilteredIterator(Iterator<TweakerItem> base) {
			this.base = base;
			
			this.next = null;
			while (base.hasNext()) {
				TweakerItem maybeNext = base.next();
				if (matches(maybeNext)) {
					next = maybeNext;
					break;
				}
			}
		}

		public boolean hasNext() {
			return next != null;
		}

		public TweakerItem next() {
			TweakerItem result = next;
			
			this.next = null;
			while (base.hasNext()) {
				TweakerItem maybeNext = base.next();
				if (matches(maybeNext)) {
					next = maybeNext;
					break;
				}
			}
			
			return result;
		}

		public void remove() {}
	}
}
