package stanhebben.minetweaker.api.value;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.functions.FunctionSetItemDisplayName;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.minetweaker.api.functions.FunctionItemPatternOnly;
import stanhebben.minetweaker.api.functions.FunctionItemAddTool;
import stanhebben.minetweaker.api.functions.FunctionItemRemoveTool;
import stanhebben.minetweaker.api.functions.FunctionItemSetToolClass;
import stanhebben.minetweaker.api.functions.FunctionItemWithDamage;

/**
 * Abstract superclass for items. Must always be convertible to an ItemStack.
 * 
 * @author Stan Hebben
 */
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
	
	/**
	 * Returns the item id. Must be a valid index to Item.itemList.
	 * 
	 * @return the item id
	 */
	public abstract int getItemId();
	
	/**
	 * Returns the subitem id. Should return 0 if there is none, or OreDictionary.WILDCARD
	 * if it represents "any" subitem.
	 * 
	 * @return item subid
	 */
	public abstract int getItemSubId();
	
	/**
	 * Checks if this item represents a sub-item.
	 * 
	 * @return true if this item instance is a subitem
	 */
	public abstract boolean isSubItem();
	
	/**
	 * Converts this item to an item stack with the specified amount.
	 * 
	 * @param amount amount of items in the stack
	 * @return corresponding item stack
	 */
	public abstract ItemStack make(int amount);
	
	/**
	 * Converts this item to an item stack. The item stack returned by this function
	 * may reuse item stack instances.
	 * 
	 * @return item stack
	 */
	public abstract ItemStack make();
	
	/**
	 * Retrieves the unlocalized item name. That item name should resolve to
	 * this item instance, if possible; or to a similar item.
	 * 
	 * @return unlocalized item name
	 */
	public abstract String getName();
	
	/**
	 * Retrieves the localized item name. Used in log messages.
	 * 
	 * @return localized item name
	 */
	public abstract String getDisplayName();
	
	/**
	 * Sets the item name. Implementations must apply to a tweaker action.
	 * 
	 * @param value new item name
	 */
	public abstract void setDisplayName(String value);
	
	/**
	 * Sets the display name for the specified language. Implementations must apply
	 * to a tweaker action.
	 * 
	 * @param lang language code (ex. nl_NL, en_UK, ...)
	 * @param value new item name
	 */
	public abstract void setDisplayName(String lang, String value);
	
	/**
	 * Returns the maximum damage for this item. Should return null if the item
	 * is not damagable.
	 * 
	 * @return maximum damage
	 */
	public abstract int getMaxDamage();
	
	/**
	 * Returns the amount of ticks this item will burn in a furnace. Returns zero
	 * if this item cannot be burned in a furnace.
	 * 
	 * @return the furnace fuel value of this item
	 */
	public abstract int getFuelValue();
	
	/**
	 * Sets the amount of ticks this item will burn in a furnace. Setting it to
	 * zero will make this item no longer burnable.
	 * 
	 * @param value new fuel value
	 * @throws TweakerExecuteException 
	 */
	public abstract void setFuelValue(int value);
	
	/**
	 * Adds a tool that can mine this block.
	 * 
	 * @param cls tool class (default classes are "axe", "shovel" and "pickaxe"
	 * @param level tool level
	 */
	public abstract void addTool(String cls, int level);
	
	/**
	 * Removes a tool that can mine this block.
	 * 
	 * @param cls tool class
	 */
	public abstract void removeTool(String cls);
	
	/**
	 * Sets the tool class of this item.
	 * 
	 * @param cls tool class
	 * @param level tool level
	 */
	public abstract void setToolClass(String cls, int level);
	
	/**
	 * Converts this item to its corresponding ID string.
	 * 
	 * @return item ID string
	 */
	public abstract String toIdString();
	
	/**
	 * Checks if this item is equal to the item in the specified stack.
	 * 
	 * @param stack item stack to compare to
	 * @return true if the stack matches, false otherwise
	 */
	public boolean matches(ItemStack stack) {
		return stack.itemID == getItemId() 
				&& (!isSubItem() || getItemSubId() == OreDictionary.WILDCARD_VALUE || getItemSubId() == stack.getItemDamage());
	}
	
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
		return new TweakerItemStack(make());
	}
	
	@Override
	public TweakerItemStackPattern asItemStackPattern() {
		return new TweakerItemStackPatternFromItemPattern(asItemPattern());
	}
	
	@Override
	public TweakerLiquid asFluid() {
		return TweakerLiquid.fromLiquidBlock(this);
	}
	
	@Override
	public TweakerLiquidStack asFluidStack() {
		return TweakerLiquidStack.fromLiquidBlock(this);
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
			case WITHDAMAGE:
				if (isSubItem()) throw new TweakerExecuteException("Cannot create a damage subitem");
				return new FunctionItemWithDamage(getItemId());
			case DAMAGED:
				if (isSubItem()) {
					return this;
				} else {
					return new TweakerItemDamaged(getItemId());
				}
			case LIQUID:
				TweakerLiquid liquid = TweakerLiquid.fromLiquidContainer(this);
				if (liquid == null) throw new TweakerExecuteException(getDisplayName() + " is not a liquid container");
				return liquid;
			case ONLY:
				return new FunctionItemPatternOnly(asItemPattern());
			case ADDTOOL:
				return new FunctionItemAddTool(this);
			case REMOVETOOL:
				return new FunctionItemRemoveTool(this);
			case SETTOOLCLASS:
				return new FunctionItemSetToolClass(this);
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
	
	@Override
	public boolean equals(TweakerValue other) {
		TweakerItem otherItem = other.toItem("value is not an item");
		return getItemId() == otherItem.getItemId() && getItemSubId() == otherItem.getItemSubId();
	}
}
