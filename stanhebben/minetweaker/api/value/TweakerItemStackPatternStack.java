package stanhebben.minetweaker.api.value;

import net.minecraft.item.ItemStack;

public class TweakerItemStackPatternStack extends TweakerItemStackPattern {
	private ItemStack value;
	
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
