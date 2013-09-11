package stanhebben.minetweaker.base.values;

import stanhebben.minetweaker.MineTweakerRegistry;
import stanhebben.minetweaker.api.TweakerException;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;

public class ItemGroupValue extends TweakerValue {
	private String name;
	
	public ItemGroupValue() {
		this.name = null;
	}
	
	public ItemGroupValue(String name) {
		this.name = name;
	}
	
	@Override
	public TweakerValue mul(TweakerValue value) throws TweakerExecuteException {
		TweakerItem item = MineTweakerRegistry.INSTANCE.getItem(name);
		if (item == null) return super.mul(value);
		return item.mul(value);
	}
	
	@Override
	public TweakerItem asItem() throws TweakerExecuteException {
		return MineTweakerRegistry.INSTANCE.getItem(name);
	}
	
	@Override
	public TweakerItemStack asItemStack() throws TweakerExecuteException {
		TweakerItem item = MineTweakerRegistry.INSTANCE.getItem(name);
		if (item == null) return null;
		return item.asItemStack();
	}
	
	@Override
	public TweakerItemPattern asItemPattern() throws TweakerExecuteException {
		TweakerItem item = MineTweakerRegistry.INSTANCE.getItem(name);
		if (item == null) return null;
		return item.asItemPattern();
	}
	
	@Override
	public TweakerItemStackPattern asItemStackPattern() throws TweakerExecuteException {
		TweakerItem item = MineTweakerRegistry.INSTANCE.getItem(name);
		if (item == null) return null;
		return item.asItemStackPattern();
	}
	
	@Override
	public Object asRecipeItem() throws TweakerExecuteException {
		TweakerItem item = MineTweakerRegistry.INSTANCE.getItem(name);
		if (item == null) return null;
		return item.asRecipeItem();
	}
	
	@Override
	public TweakerValue index(String index) throws TweakerExecuteException {
		String newName = name == null ? index : name + '.' + index;
		if (MineTweakerRegistry.INSTANCE.isItemPrefix(newName)) {
			return new ItemGroupValue(newName);
		} else if (MineTweakerRegistry.INSTANCE.getItem(newName) != null) {
			return MineTweakerRegistry.INSTANCE.getItem(newName);
		} else if (MineTweakerRegistry.INSTANCE.getItem(name) != null) {
			return MineTweakerRegistry.INSTANCE.getItem(name).index(index);
		} else {
			throw new TweakerExecuteException("no such item: " + newName);
		}
	}
	
	@Override
	public void indexSet(TweakerValue index, TweakerValue value) throws TweakerExecuteException {
		TweakerItem item = MineTweakerRegistry.INSTANCE.getItem(name);
		if (item == null) {
			super.indexSet(index, value);
		} else {
			item.indexSet(index, value);
		}
	}

	@Override
	public String toString() {
		return name == null ? "items" : name;
	}
}
