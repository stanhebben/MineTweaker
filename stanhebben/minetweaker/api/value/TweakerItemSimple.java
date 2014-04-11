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

public class TweakerItemSimple extends TweakerItem {
	private final int id;
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
		return new ItemStack(id, amount, 0);
	}
	
	@Override
	public ItemStack make() {
		return stack;
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

	@Override
	public void addTool(String cls, int level) {
		if (id >= Block.blocksList.length || Block.blocksList[id] == null) {
			throw new TweakerExecuteException("Item " + id + " not not a block");
		}
		Tweaker.apply(new ItemAddToolAction(Block.blocksList[id], 0, cls, level));
	}

	@Override
	public void removeTool(String cls) {
		if (id >= Block.blocksList.length || Block.blocksList[id] == null) {
			throw new TweakerExecuteException("Item " + id + " not not a block");
		}
		Tweaker.apply(new ItemRemoveToolAction(Block.blocksList[id], 0, cls));
	}

	@Override
	public void setToolClass(String cls, int level) {
		Tweaker.apply(new ItemSetToolClassAction(item, cls, level));
	}
}
