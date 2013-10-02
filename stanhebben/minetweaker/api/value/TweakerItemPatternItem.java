package stanhebben.minetweaker.api.value;

import java.util.Collections;
import java.util.List;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.IPatternListener;

public class TweakerItemPatternItem extends TweakerItemPattern {
	private final TweakerItem item;
	
	public TweakerItemPatternItem(TweakerItem item) {
		this.item = item;
	}
	
	@Override
	public boolean matches(TweakerItem item) {
		if (item == null) return false;
		return item.getItemId() == this.item.getItemId() && item.getItemSubId() == this.item.getItemSubId();
	}

	@Override
	public boolean matches(ItemStack item) {
		if (item == null) return false;
		return this.item.getItemId() == item.itemID && 
				(!this.item.isSubItem() || this.item.getItemSubId() == item.getItemDamage());
	}
	
	@Override
	public boolean matches(Object object) {
		if (object == null) return false;
		if (!(object instanceof ItemStack)) return false;
		return matches((ItemStack) object);
	}

	@Override
	public boolean matches(int id) {
		return !item.isSubItem() && item.getItemId() == id;
	}

	@Override
	public boolean matches(int id, int meta) {
		return this.item.getItemId() == id && 
				(!this.item.isSubItem() || this.item.getItemSubId() == meta);
	}
	
	@Override
	public String toPatternString() {
		return "<" + item.toIdString() + ">";
	}

	@Override
	public String toString() {
		if (item.isSubItem()) {
			return "<" + item.getItemId() + ":" + item.getItemSubId() + ">"; 
		} else {
			return "<" + item.getItemId() + ">";
		}
	}

	@Override
	public List<TweakerItem> getMatches() {
		return Collections.singletonList(item);
	}

	@Override
	public void addListener(IPatternListener listener) {
		// never altered
	}

	@Override
	public void removeListener(IPatternListener listener) {
		// never altered
	}
}
