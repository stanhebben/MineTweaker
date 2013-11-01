package stanhebben.minetweaker.base.actions;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 * Implements oreDict.xxx.add .
 * 
 * @author Stan Hebben
 */
public final class AddOreItemAction implements IUndoableAction {
	private final String ore;
	private final ItemStack item;
	
	public AddOreItemAction(String ore, ItemStack item) {
		this.ore = ore;
		this.item = item;
	}
	
	@Override
	public void apply() {
		OreDictionary.getOres(ore).add(item);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		List<ItemStack> ores = OreDictionary.getOres(ore);
		ores.remove(ores.size() - 1);
	}

	public String describe() {
		return "Adding " + item.getDisplayName() + " to the " + ore + " ore dictionary entry.";
	}

	public String describeUndo() {
		return "Removing " + item.getDisplayName() + " from the " + ore + " ore dictionary entry.";
	}
}
