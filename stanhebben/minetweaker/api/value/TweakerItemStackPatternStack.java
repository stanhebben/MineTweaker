package stanhebben.minetweaker.api.value;

import java.util.Collections;
import java.util.List;
import net.minecraft.item.ItemStack;

public final class TweakerItemStackPatternStack extends TweakerItemStackPattern {
	private final ItemStack value;
	
	public TweakerItemStackPatternStack(ItemStack value) {
		this.value = value;
	}
	
	@Override
	public boolean matches(ItemStack stack) {
		if (stack == null) return false;
		return stack.itemID == value.itemID
				&& stack.getItemDamage() == value.getItemDamage()
				&& stack.stackSize == value.stackSize;
	}
	
	@Override
	public List<TweakerItem> getMatches() {
		return Collections.singletonList(TweakerItem.get(value));
	}
	
	@Override
	public int getAmount() {
		return value.stackSize;
	}
	
	@Override
	public String toPatternString() {
		if (value.getHasSubtypes()) {
			return "<" + value.itemID + ":" + value.getItemDamage() + "> * " + value.stackSize;
		} else {
			return "<" + value.itemID + "> * " + value.stackSize;
		}
	}

	@Override
	public String toString() {
		if (value.getHasSubtypes()) {
			return "<" + value.itemID + ":" + value.getItemDamage() + ">*" + value.stackSize;
		} else {
			return "<" + value.itemID + ">*" + value.stackSize;
		}
	}
}
