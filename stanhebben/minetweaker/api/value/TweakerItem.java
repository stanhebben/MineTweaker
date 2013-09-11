package stanhebben.minetweaker.api.value;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.functions.FunctionSetItemDisplayName;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.functions.FunctionItemPatternOnly;

public abstract class TweakerItem extends TweakerValue {
	public static TweakerItem get(ItemStack stack) {
		if (stack.getHasSubtypes()) {
			return new TweakerItemSub(stack.itemID, stack.getItemDamage());
		} else {
			return new TweakerItemSimple(stack.itemID);
		}
	}
	
	public static TweakerItem parse(String value) {
		try {
			int colon = value.indexOf(':');
			if (colon < 0) {
				return new TweakerItemSimple(Integer.parseInt(value));
			} else {
				return new TweakerItemSub(
						Integer.parseInt(value.substring(0, colon)),
						Integer.parseInt(value.substring(colon + 1)));
			}
		} catch (NumberFormatException ex) {
			throw new TweakerExecuteException("not a valid item id: " + value);
		}
	}
	
	public abstract int getItemId();
	
	public abstract int getItemSubId();
	
	public abstract boolean isSubItem();
	
	public abstract ItemStack make(int amount);
	
	public abstract String getName();
	
	public abstract String getDisplayName();
	
	public abstract void setDisplayName(String value);
	
	public abstract void setDisplayName(String lang, String value);
	
	public abstract int getMaxDamage();
	
	public abstract int getFuelValue();
	
	public abstract void setFuelValue(int value) throws TweakerExecuteException;
	
	public abstract String toIdString();
	
	@Override
	public TweakerItem asItem() {
		return this;
	}
	
	@Override
	public TweakerItemPattern asItemPattern() {
		return new TweakerItemPatternItem(this);
	}
	
	@Override
	public TweakerItemStack asItemStack() {
		return new TweakerItemStack(make(1));
	}
	
	@Override
	public TweakerItemStackPattern asItemStackPattern() {
		return new TweakerItemStackPatternFromItemPattern(asItemPattern());
	}
	
	@Override
	public TweakerFluid asFluid() {
		return TweakerFluid.fromLiquidBlock(this);
	}
	
	@Override
	public TweakerFluidStack asFluidStack() {
		return TweakerFluidStack.fromLiquidBlock(this);
	}
	
	@Override
	public Object asRecipeItem() {
		return make(1);
	}
	
	@Override
	public TweakerValue mul(TweakerValue value) throws TweakerExecuteException {
		return new TweakerItemStack(make(value.toBasicInt()));
	}

	@Override
	public TweakerValue index(String index) throws TweakerExecuteException {
		switch (TweakerField.get(index)) {
			case ID:
				return new TweakerInt(getItemId());
			case META:
				return new TweakerInt(getItemSubId());
			case DAMAGE:
				return new TweakerInt(getItemSubId());
			case MAXDAMAGE:
				return new TweakerInt(getMaxDamage());
			case NAME:
				return new TweakerString(getName());
			case DISPLAYNAME:
				return new TweakerString(getDisplayName());
			case FUEL:
				return new TweakerInt(getFuelValue());
			case SETDISPLAYNAME:
				return new FunctionSetItemDisplayName(this);
			case DAMAGED:
				if (isSubItem()) {
					return this;
				} else {
					return new TweakerItemDamaged(getItemId());
				}
			case LIQUID:
				TweakerFluid liquid = TweakerFluid.fromLiquidContainer(this);
				if (liquid == null) throw new TweakerExecuteException(getDisplayName() + " is not a liquid container");
				return liquid;
			case ONLY:
				return new FunctionItemPatternOnly(asItemPattern());
			default:
				throw new TweakerExecuteException("No such member in Item: " + index);
		}
	}
	
	@Override
	public void indexSet(String index, TweakerValue value) throws TweakerExecuteException {
		switch (TweakerField.get(index)) {
			case DISPLAYNAME:
				setDisplayName(value.toBasicString());
				return;
			case FUEL:
				setFuelValue(value.toBasicInt());
				return;
		}
		throw new TweakerExecuteException("Not a settable member in Item: " + index);
	}
}
