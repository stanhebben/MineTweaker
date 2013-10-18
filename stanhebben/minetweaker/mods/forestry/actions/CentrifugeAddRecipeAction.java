/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.recipes.RecipeManagers;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;

/**
 *
 * @author Stanneke
 */
public class CentrifugeAddRecipeAction implements IUndoableAction {
	private final TweakerItem input;
	private final TweakerItemStack[] output;
	private final int[] outputChances;
	private final int time;
	
	public CentrifugeAddRecipeAction(TweakerItemStack[] output, TweakerItem input, int time, int[] outputChances) {
		this.input = input;
		this.output = output;
		this.outputChances = outputChances;
		this.time = time;
	}

	public void apply() {
		ItemStack[] output2 = new ItemStack[output.length];
		for (int i = 0; i < output.length; i++) {
			output2[i] = output[i].get();
		}
		RecipeManagers.centrifugeManager.addRecipe(time, input.make(1), output2, outputChances);
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding a recipe to centrifuge " + input.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
