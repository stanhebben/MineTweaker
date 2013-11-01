package stanhebben.minetweaker.api.value;

import stanhebben.minetweaker.api.TweakerExecuteException;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.functions.FunctionItemStackPatternOnly;

/**
 * Represents an item stack.
 * 
 * @author Stan Hebben
 */
public final class TweakerItemStack extends TweakerValue {
	private final ItemStack value;
	
	public TweakerItemStack(ItemStack value) {
		this.value = value;
	}
	
	public ItemStack get() {
		return value;
	}
	
	public String getDisplayName() {
		return value.getDisplayName();
	}
	
	@Override
	public TweakerItem toItem(String error) {
		throw new TweakerExecuteException("Cannot use an item stack as item. Use .item to get the item of this stack.");
	}
	
	@Override
	public TweakerItemPattern toItemPattern(String error) {
		throw new TweakerExecuteException("Cannot use an item stack as item pattern. Use .item to get the item of this stack.");
	}
	
	@Override
	public TweakerItemStack asItemStack() {
		return this;
	}
	
	@Override
	public TweakerItemStackPattern asItemStackPattern() {
		return new TweakerItemStackPatternStack(value);
	}
	
	@Override
	public TweakerValue index(String index) throws TweakerExecuteException {
		switch (TweakerField.get(index)) {
			case TAG:
				return new TweakerNBTCompound(value.stackTagCompound);
			case DAMAGE:
				if (!value.getHasSubtypes()) {
					return new TweakerInt(value.getItemDamage());
				}
				break;
			case MAXDAMAGE:
				if (!value.getHasSubtypes()) {
					return new TweakerInt(value.getMaxDamage());
				}
				break;
			case ITEM:
				if (value.getHasSubtypes()) {
					return new TweakerItemSub(value.itemID, value.getItemDamage());
				} else {
					return new TweakerItemSimple(value.itemID);
				}
			case ONLY:
				return new FunctionItemStackPatternOnly(asItemStackPattern());
		}
		throw new TweakerExecuteException("no such member exists for item stack: " + index);
	}
	
	@Override
	public void indexSet(String index, TweakerValue value) throws TweakerExecuteException {
		switch (TweakerField.get(index)) {
			case TAG:
				this.value.stackTagCompound = value.toTag(null);
				return;
			case DAMAGE:
				if (!this.value.getHasSubtypes()) {
					this.value.setItemDamage(value.toBasicInt());
				}
				return;
		}
		throw new TweakerExecuteException("no such member is settable for item stack: " + index);
	}

	@Override
	public String toString() {
		if (value.getItem().getHasSubtypes()) {
			return "<" + value.itemID + ":" + value.getItemDamage() + ">*" + value.stackSize;
		} else {
			return "<" + value.itemID + ">*" + value.stackSize;
		}
	}
}
