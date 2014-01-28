package stanhebben.minetweaker.api.value;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.base.actions.ItemAddToolAction;
import stanhebben.minetweaker.base.actions.ItemRemoveToolAction;
import stanhebben.minetweaker.base.actions.ItemSetToolClassAction;
import stanhebben.minetweaker.base.actions.SetFuelItemAction;
import stanhebben.minetweaker.base.actions.SetLocalizedStringAction;

public final class TweakerItemWithDamage extends TweakerItem {
	private final int id;
	private final int damage;
	private final Item item;
	private final ItemStack stack;
	
	public TweakerItemWithDamage(int id, int damage) throws TweakerExecuteException {
		this.id = id;
		this.damage = damage;
		if (Item.itemsList[id] == null) {
			item = null;
			stack = null;
			throw new TweakerExecuteException("No item with id " + id);
		}
		item = Item.itemsList[id];
		stack = new ItemStack(id, 1, damage);
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
			return new ItemStack(id, amount, damage);
		}
	}

	@Override
	public String getName() {
		return item.getUnlocalizedName(stack);
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
		Tweaker.apply(new SetLocalizedStringAction(item.getUnlocalizedName(stack) + ".name", "en_US", value));
	}

	@Override
	public void setDisplayName(String lang, String value) {
		Tweaker.apply(new SetLocalizedStringAction(item.getUnlocalizedName(stack) + ".name", lang, value));
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
		return id + ".withDamage(" + damage + ")";
	}

	@Override
	public String toString() {
		return toIdString();
	}

	@Override
	public void addTool(String cls, int level) {
		if (id >= Block.blocksList.length || Block.blocksList[id] == null) {
			throw new TweakerExecuteException("Item " + id + " not not a block");
		}
		Tweaker.apply(new ItemAddToolAction(Block.blocksList[id], damage, cls, level));
	}

	@Override
	public void removeTool(String cls) {
		if (id >= Block.blocksList.length || Block.blocksList[id] == null) {
			throw new TweakerExecuteException("Item " + id + " not not a block");
		}
		Tweaker.apply(new ItemRemoveToolAction(Block.blocksList[id], damage, cls));
	}

	@Override
	public void setToolClass(String cls, int level) {
		Tweaker.apply(new ItemSetToolClassAction(Item.itemsList[id], cls, level));
	}
}
