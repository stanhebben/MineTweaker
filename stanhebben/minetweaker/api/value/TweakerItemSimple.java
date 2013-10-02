package stanhebben.minetweaker.api.value;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.base.actions.SetFuelItemAction;
import stanhebben.minetweaker.base.actions.SetLocalizedStringAction;

public class TweakerItemSimple extends TweakerItem {
	private int id;
	private Item item;
	private ItemStack stack;
	
	public TweakerItemSimple(int id) throws TweakerExecuteException {
		this.id = id;
		if (Item.itemsList[id] == null) {
			throw new TweakerExecuteException("No item with id " + id);
		}
		item = Item.itemsList[id];
		stack = new ItemStack(id, 1, 0);
	}
	
	@Override
	public int getItemId() {
		return id;
	}

	@Override
	public int getItemSubId() {
		return 0;
	}
	
	@Override
	public boolean isSubItem() {
		return false;
	}

	@Override
	public ItemStack make(int amount) {
		if (amount == 1) {
			return stack;
		} else {
			return new ItemStack(id, amount, 0);
		}
	}

	@Override
	public String getName() {
		return item.getUnlocalizedName();
	}

	@Override
	public String getDisplayName() {
		//#ifdef MC152
		//+return item.getLocalizedName(stack);
		//#else
		return item.getItemStackDisplayName(stack);
		//#endif
	}

	@Override
	public void setDisplayName(String value) {
		Tweaker.apply(new SetLocalizedStringAction(item.getUnlocalizedName() + ".name", "en_US", value));
	}

	@Override
	public void setDisplayName(String lang, String value) {
		Tweaker.apply(new SetLocalizedStringAction(item.getUnlocalizedName() + ".name", lang, value));
	}
	
	@Override
	public int getMaxDamage() {
		return stack.getMaxDamage();
	}

	@Override
	public int getFuelValue() {
		return TileEntityFurnace.getItemBurnTime(stack);
	}

	@Override
	public void setFuelValue(int value) throws TweakerExecuteException {
		Tweaker.apply(new SetFuelItemAction(this, value));
	}
	
	@Override
	public String toIdString() {
		return Integer.toString(id);
	}

	@Override
	public String toString() {
		return "<" + id + ">";
	}
}
