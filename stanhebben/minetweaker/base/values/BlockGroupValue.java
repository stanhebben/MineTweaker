package stanhebben.minetweaker.base.values;

import stanhebben.minetweaker.MineTweakerRegistry;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;

public class BlockGroupValue extends TweakerValue {
	private String name;
	
	public BlockGroupValue() {
		this.name = null;
	}
	
	public BlockGroupValue(String name) {
		this.name = name;
	}
	
	@Override
	public TweakerValue mul(TweakerValue value) throws TweakerExecuteException {
		TweakerItem item = MineTweakerRegistry.INSTANCE.getBlock(name);
		if (item == null) return super.mul(value);
		return item.mul(value);
	}
	
	@Override
	public TweakerItem asItem() throws TweakerExecuteException {
		return MineTweakerRegistry.INSTANCE.getBlock(name);
	}
	
	@Override
	public TweakerItemStack asItemStack() throws TweakerExecuteException {
		TweakerItem item = MineTweakerRegistry.INSTANCE.getBlock(name);
		if (item == null) return null;
		return item.asItemStack();
	}
	
	@Override
	public TweakerItemPattern asItemPattern() throws TweakerExecuteException {
		TweakerItem item = MineTweakerRegistry.INSTANCE.getBlock(name);
		if (item == null) return null;
		return item.asItemPattern();
	}
	
	@Override
	public TweakerItemStackPattern asItemStackPattern() throws TweakerExecuteException {
		TweakerItem item = MineTweakerRegistry.INSTANCE.getBlock(name);
		if (item == null) return null;
		return item.asItemStackPattern();
	}
	
	@Override
	public Object asRecipeItem() throws TweakerExecuteException {
		TweakerItem item = MineTweakerRegistry.INSTANCE.getBlock(name);
		if (item == null) return null;
		return item.asRecipeItem();
	}
	
	@Override
	public TweakerValue index(String index) throws TweakerExecuteException {
		String newName = name == null ? index : name + '.' + index;
		if (MineTweakerRegistry.INSTANCE.isBlockPrefix(newName)) {
			return new BlockGroupValue(newName);
		} else if (MineTweakerRegistry.INSTANCE.getBlock(newName) != null) {
			return new BlockValue(MineTweakerRegistry.INSTANCE.getBlock(newName));
		} else if (MineTweakerRegistry.INSTANCE.getBlock(name) != null) {
			return new BlockValue(MineTweakerRegistry.INSTANCE.getBlock(name)).index(index);
		} else {
			throw new TweakerExecuteException("no such block: " + newName);
		}
	}
	
	@Override
	public void indexSet(String index, TweakerValue value) throws TweakerExecuteException {
		TweakerItem item = MineTweakerRegistry.INSTANCE.getBlock(name);
		if (item == null) {
			super.indexSet(index, value);
		} else {
			item.indexSet(index, value);
		}
	}

	@Override
	public String toString() {
		return name == null ? "blocks" : name;
	}
}
