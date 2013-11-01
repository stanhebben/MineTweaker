package stanhebben.minetweaker.base.actions;

import java.util.List;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 * Implements recipes.removeShaped and recipes.removeShapeless, as well as
 * recipes.remove and minetweaker.remove when recipes are to be removed.
 * 
 * @author Stan Hebben
 */
public final class RemoveRecipeAction implements IUndoableAction {
	private final int index;
	private final IRecipe value;
	
	public RemoveRecipeAction(int index) {
		this.index = index;
		value = (IRecipe) CraftingManager.getInstance().getRecipeList().get(index);
	}
	
	@Override
	public void apply() {
		CraftingManager.getInstance().getRecipeList().remove(index);
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

	@Override
	public String describe() {
		return "Removing a recipe for " + value.getRecipeOutput().getDisplayName();
	}

	@Override
	public String describeUndo() {
		return "Restoring a recipe for " + value.getRecipeOutput().getDisplayName();
	}
}
