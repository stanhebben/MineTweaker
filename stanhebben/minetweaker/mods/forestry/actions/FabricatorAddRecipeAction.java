package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.recipes.RecipeManagers;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerLiquidStack;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;

/**
 *
 * @author Stanneke
 */
public class FabricatorAddRecipeAction implements IUndoableAction {
	private final TweakerItemStack output;
	private final Object[] input;
	private final TweakerLiquidStack fluid;
	private final TweakerItem cast;
	
	public FabricatorAddRecipeAction(TweakerItemStack output, Object[] input, TweakerLiquidStack fluid, TweakerItem cast) {
		this.output = output;
		this.input = input;
		this.fluid = fluid;
		this.cast = cast;
	}

	public void apply() {
		RecipeManagers.fabricatorManager.addRecipe(cast == null ? null : cast.make(1), fluid.get(), output.get(), input);
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding a fabricator recipe for " + output.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
