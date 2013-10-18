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

/**
 *
 * @author Stanneke
 */
public class FabricatorAddSmeltingAction implements IUndoableAction {
	private final TweakerItem item;
	private final TweakerFluidStack liquid;
	private final int meltingPoint;
	
	public FabricatorAddSmeltingAction(TweakerFluidStack liquid, TweakerItem item, int meltingPoint) {
		this.item = item;
		this.liquid = liquid;
		this.meltingPoint = meltingPoint;
	}

	public void apply() {
		RecipeManagers.fabricatorManager.addSmelting(item.make(1), liquid.get(), meltingPoint);
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding a fabricator smelting liquid: " + item.getDisplayName() + " to " + liquid.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
