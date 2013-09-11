package stanhebben.minetweaker.api.value;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.base.actions.SetFuelItemAction;
import stanhebben.minetweaker.base.actions.SetLocalizedStringAction;

public class TweakerItemDamaged extends TweakerItem {
	private int id;
	
	public TweakerItemDamaged(int id) {
		this.id = id;
		if (Item.itemsList[id] == null) {
			throw new TweakerExecuteException("No item with id " + id);
		}
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
		return new ItemStack(id, 0, OreDictionary.WILDCARD_VALUE);
	}

	@Override
	public String getName() {
		return Item.itemsList[id].getUnlocalizedName();
	}

	@Override
	public String getDisplayName() {
		return new ItemStack(id, 1, 0).getDisplayName();
	}

	@Override
	public void setDisplayName(String value) {
		Tweaker.apply(new SetLocalizedStringAction(Item.itemsList[id].getUnlocalizedName() + ".name", "en_US", value));
	}

	@Override
	public void setDisplayName(String lang, String value) {
		Tweaker.apply(new SetLocalizedStringAction(Item.itemsList[id].getUnlocalizedName() + ".name", lang, value));
	}
	
	@Override
	public int getMaxDamage() {
		return Item.itemsList[id].getMaxDamage();
	}

	@Override
	public int getFuelValue() {
		return TileEntityFurnace.getItemBurnTime(new ItemStack(id, 1, 0));
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
		return "<" + id + ">.damaged";
	}
}
