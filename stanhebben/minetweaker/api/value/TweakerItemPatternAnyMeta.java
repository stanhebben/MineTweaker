package stanhebben.minetweaker.api.value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.IPatternListener;
import stanhebben.minetweaker.MineTweakerUtil;

public class TweakerItemPatternAnyMeta extends TweakerItemPattern {
	private final int id;
	
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
	public List<TweakerItem> getMatches() {
		Item item = Item.itemsList[id];
		if (item.getHasSubtypes()) {
			final List<ItemStack> subItems = new ArrayList<ItemStack>();
			MineTweakerUtil.getSubItems(id, subItems);
			
			List<TweakerItem> result  = new ArrayList<TweakerItem>();
			for (ItemStack stack : subItems) {
				result.add(TweakerItem.get(stack));
			}
			return result;
		} else {
			return Collections.emptyList();
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
