/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.recipes.RecipeManagers;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerFluid;
import stanhebben.minetweaker.api.value.TweakerFluidStack;
import stanhebben.minetweaker.api.value.TweakerItem;

/**
 *
 * @author Stanneke
 */
public class FermenterAddRecipeAction implements IUndoableAction {
	private final TweakerFluid output;
	private final float modifier;
	private final int fermentationValue;
	private final TweakerItem input;
	private final TweakerFluidStack inputLiquid;
	
	public FermenterAddRecipeAction(TweakerFluid output, TweakerItem input, int fermentationValue, float modifier, TweakerFluidStack inputLiquid) {
		this.output = output;
		this.input = input;
		this.modifier = modifier;
		this.fermentationValue = fermentationValue;
		this.inputLiquid = inputLiquid;
	}

	public void apply() {
		if (inputLiquid == null) {
			RecipeManagers.fermenterManager.addRecipe(input.make(1), fermentationValue, modifier, output.make(1));
		} else {
			RecipeManagers.fermenterManager.addRecipe(input.make(1), fermentationValue, modifier, output.make(1), inputLiquid.get());
		}
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding a fermenter recipe for " + output.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
