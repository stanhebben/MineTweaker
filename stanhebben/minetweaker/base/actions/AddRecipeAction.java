package stanhebben.minetweaker.base.actions;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import stanhebben.minetweaker.api.IUndoableAction;

public class AddRecipeAction implements IUndoableAction {
	private IRecipe recipe;
	private int recipeIndex;
	
	public AddRecipeAction(IRecipe recipe) {
		this.recipe = recipe;
	}
	
	@Override
	public void apply() {
		recipeIndex = CraftingManager.getInstance().getRecipeList().size();
		CraftingManager.getInstance().getRecipeList().add(recipe);
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
