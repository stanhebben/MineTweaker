package stanhebben.minetweaker.base.actions;

import java.util.List;
import net.minecraft.item.Item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.minetweaker.api.IUndoableAction;

public class AddOreItemWildcardAction implements IUndoableAction {
	private String ore;
	private int id;
	
	public AddOreItemWildcardAction(String ore, int id) {
		this.ore = ore;
		this.id = id;
	}

	@Override
	public void apply() {
		OreDictionary.getOres(ore).add(new ItemStack(id, 1, OreDictionary.WILDCARD_VALUE));
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
		return "Adding " + Item.itemsList[id].getLocalizedName(new ItemStack(id, 1, OreDictionary.WILDCARD_VALUE)) + " to the " + ore + " ore dictionary entry.";
	}

	public String describeUndo() {
		return "Removing " + Item.itemsList[id].getLocalizedName(new ItemStack(id, 1, OreDictionary.WILDCARD_VALUE)) + " from the " + ore + " ore dictionary entry.";
	}
}
