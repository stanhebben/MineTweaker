package stanhebben.minetweaker.base.actions;

import java.util.List;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import stanhebben.minetweaker.api.IUndoableAction;

public class RemoveRecipeAction implements IUndoableAction {
	private int index;
	private IRecipe value;
	
	public RemoveRecipeAction(int index) {
		this.index = index;
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		value = recipes.get(index);
	}
	
	@Override
	public void apply() {
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		value = recipes.remove(index);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		recipes.add(index, value);
	}

	public String describe() {
		return "Removing a recipe for " + value.getRecipeOutput().getDisplayName();
	}

	public String describeUndo() {
		return "Restoring a recipe for " + value.getRecipeOutput().getDisplayName();
	}
}
