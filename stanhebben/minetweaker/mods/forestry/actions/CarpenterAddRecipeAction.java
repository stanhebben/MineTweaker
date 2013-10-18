/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.recipes.RecipeManagers;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerFluidStack;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;

/**
 *
 * @author Stanneke
 */
public class CarpenterAddRecipeAction implements IUndoableAction {
	private final TweakerItemStack output;
	private final Object[] input;
	private final TweakerItem box;
	private final int packagingTime;
	private final TweakerFluidStack liquid;
	
	public CarpenterAddRecipeAction(TweakerItemStack output, Object[] input, TweakerItem box, int packagingTime, TweakerFluidStack liquid) {
		this.output = output;
		this.input = input;
		this.box = box;
		this.packagingTime = packagingTime;
		this.liquid = liquid;
	}

	public void apply() {
		if (liquid != null) {
			RecipeManagers.carpenterManager.addRecipe(packagingTime, liquid.get(), box == null ? null : box.make(1), output.get(), input);
		} else if (packagingTime > 0) {
			RecipeManagers.carpenterManager.addRecipe(packagingTime, box == null ? null : box.make(1), output.get(), input);
		} else {
			RecipeManagers.carpenterManager.addRecipe(box == null ? null : box.make(1), output.get(), input);
		}
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding a carpenter recipe for " + output.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
