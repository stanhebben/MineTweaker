package stanhebben.minetweaker.api.value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.IPatternListener;
import stanhebben.minetweaker.MineTweakerUtil;
import stanhebben.minetweaker.util.EmptyIterator;

public class TweakerItemPatternAnyMeta extends TweakerItemPattern {
	private int id;
	
	public TweakerItemPatternAnyMeta(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public boolean matches(TweakerItem item) {
		if (item == null) return false;
		return item.getItemId() == id;
	}
	
	@Override
	public boolean matches(ItemStack stack) {
		if (stack == null) return false;
		return stack.itemID == id;
	}

	@Override
	public boolean matches(Object object) {
		if (object == null) return false;
		if (!(object instanceof ItemStack)) return false;
		return matches((ItemStack) object);
	}

	@Override
	public boolean matches(int id) {
		return this.id == id;
	}

	@Override
	public boolean matches(int id, int meta) {
		return this.id == id;
	}
	
	@Override
	public String toPatternString() {
		return "<" + id + ":*>";
	}

	@Override
	public String toString() {
		return "<" + id + ":*>";
	}

	@Override
	public Iterator<TweakerItem> getMatches() {
		Item item = Item.itemsList[id];
		if (item.getHasSubtypes()) {
			final List<ItemStack> subItems = new ArrayList<ItemStack>();
			MineTweakerUtil.getSubItems(id, subItems);
			return new Iterator<TweakerItem>() {
				private Iterator<ItemStack> inner = subItems.iterator();
				
				public boolean hasNext() {
					return inner.hasNext();
				}

				public TweakerItem next() {
					return TweakerItem.get(inner.next());
				}

				public void remove() {}
			};
		} else {
			return new EmptyIterator<TweakerItem>();
		}
	}

	@Override
	public void addListener(IPatternListener listener) {
		// no items are assumed to be added
	}

	@Override
	public void removeListener(IPatternListener listener) {
		// no items are assumed to be added
	}
}
