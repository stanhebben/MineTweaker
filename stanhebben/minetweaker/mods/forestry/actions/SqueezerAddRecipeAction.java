/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.recipes.RecipeManagers;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerFluidStack;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;

/**
 *
 * @author Stanneke
 */
public class SqueezerAddRecipeAction implements IUndoableAction {
	private final TweakerFluidStack output;
	private final TweakerItemStack[] input;
	private final int time;
	private final TweakerItem remnant;
	private final int remnantChance;
	
	public SqueezerAddRecipeAction(
			TweakerFluidStack output,
			TweakerItemStack[] input,
			int time,
			TweakerItem remnant, int remnantChance) {
		this.output = output;
		this.input = input;
		this.time = time;
		this.remnant = remnant;
		this.remnantChance = remnantChance;
	}

	public void apply() {
		ItemStack[] inputs = new ItemStack[input.length];
		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = input[i].get();
		}
		if (remnant == null) {
			RecipeManagers.squeezerManager.addRecipe(time, inputs, output.get());
		} else {
			RecipeManagers.squeezerManager.addRecipe(time, inputs, output.get(), remnant.make(1), remnantChance);
		}
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding a squeezer recipe for " + output.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
