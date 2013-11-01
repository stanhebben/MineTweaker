package stanhebben.minetweaker.base.actions;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.minetweaker.api.IUndoableAction;

public final class RemoveOreItemAction implements IUndoableAction {
	private final String ore;
	private final int index;
	private final ItemStack value;
	
	public RemoveOreItemAction(String ore, int index) {
		this.ore = ore;
		this.index = index;
		this.value = OreDictionary.getOres(ore).get(index);
	}
	
	@Override
	public void apply() {
		OreDictionary.getOres(ore).remove(index);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		OreDictionary.getOres(ore).add(index, value);
	}

	@Override
	public String describe() {
		return "Removing " + value.getDisplayName() + " from the " + ore + " ore dictionary entry.";
	}

	@Override
	public String describeUndo() {
		return "Restoring " + value.getDisplayName() + " to the " + ore + " ore dictionary entry.";
	}
}
