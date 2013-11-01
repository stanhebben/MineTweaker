package stanhebben.minetweaker.base.actions;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import stanhebben.minetweaker.api.IUndoableAction;

/**
 * Implements recipes.addShaped and recipes.addShapeless.
 * 
 * @author Stan Hebben
 */
public final class AddRecipeAction implements IUndoableAction {
	private final IRecipe recipe;
	private final int recipeIndex;
	
	public AddRecipeAction(IRecipe recipe) {
		this.recipe = recipe;
		recipeIndex = CraftingManager.getInstance().getRecipeList().size();
	}
	
	@Override
	public void apply() {
		CraftingManager.getInstance().getRecipeList().add(recipeIndex, recipe);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		CraftingManager.getInstance().getRecipeList().remove(recipeIndex);
	}

	public String describe() {
		return "Adding a recipe for " + recipe.getRecipeOutput().getDisplayName();
	}

	public String describeUndo() {
		return "Removing a recipe for " + recipe.getRecipeOutput().getDisplayName();
	}
}
