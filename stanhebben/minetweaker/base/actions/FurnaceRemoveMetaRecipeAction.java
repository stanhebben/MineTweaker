package stanhebben.minetweaker.base.actions;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import stanhebben.minetweaker.api.IUndoableAction;

/**
 * Implements furnace.removeRecipe with subitems.
 * 
 * @author Stan Hebben
 */
public final class FurnaceRemoveMetaRecipeAction implements IUndoableAction {
	private final List<Integer> key;
	private final ItemStack value;
	
	public FurnaceRemoveMetaRecipeAction(List<Integer> key) {
		this.key = key;
		value = FurnaceRecipes.smelting().getMetaSmeltingList().get(key);
	}

	@Override
	public void apply() {
		FurnaceRecipes.smelting().getMetaSmeltingList().remove(key);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		FurnaceRecipes.smelting().getMetaSmeltingList().put(key, value);
	}

	public String describe() {
		return "Removing the furnace recipe turning " + (new ItemStack(key.get(0), 1, key.get(1)).getDisplayName() + " into " + value.getDisplayName());
	}

	public String describeUndo() {
		return "Restoring the furnace recipe turning " + (new ItemStack(key.get(0), 1, key.get(1)).getDisplayName() + " into " + value.getDisplayName());
	}
}
