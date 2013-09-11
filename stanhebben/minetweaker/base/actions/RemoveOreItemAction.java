package stanhebben.minetweaker.base.actions;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.minetweaker.api.IUndoableAction;

public class RemoveOreItemAction implements IUndoableAction {
	private String ore;
	private int index;
	private ItemStack value;
	
	public RemoveOreItemAction(String ore, int index) {
		this.ore = ore;
		this.index = index;
	}
	
	@Override
	public void apply() {
		value = OreDictionary.getOres(ore).remove(index);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		OreDictionary.getOres(ore).add(index, value);
	}

	public String describe() {
		return "Removing " + value.getDisplayName() + " from the " + ore + " ore dictionary entry.";
	}

	public String describeUndo() {
		return "Restoring " + value.getDisplayName() + " to the " + ore + " ore dictionary entry.";
	}
}
