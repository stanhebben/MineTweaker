/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.base.functions.recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import stanhebben.minetweaker.MineTweaker;
import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArrayValue;
import stanhebben.minetweaker.api.value.TweakerBool;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 *
 * @author Stanneke
 */
public class ShapelessAdvancedRecipe implements IRecipe {
	private final TweakerItemStackPattern[] contents;
	private final TweakerItemStack output;
	private final TweakerValue function;
	
	public ShapelessAdvancedRecipe(TweakerItemStack output, TweakerItemStackPattern[] contents, TweakerValue function) {
		this.output = output;
		this.contents = contents;
		this.function = function;
	}

	public boolean matches(InventoryCrafting ingredients, World world) {
		boolean[] tag = new boolean[contents.length];
		outer: for (int i = 0; i < ingredients.getSizeInventory(); i++) {
			ItemStack ingredient = ingredients.getStackInSlot(i);
			if (ingredient == null) continue;
			
			for (int j = 0; j < contents.length; j++) {
				if (tag[j]) continue;
				
				if (contents[j].matches(ingredient)) {
					tag[j] = true;
					continue outer;
				}
			}
			return false;
		}
		return true;
	}

	public ItemStack getCraftingResult(InventoryCrafting ingredients) {
		if (function == null) {
			return output.get();
		} else {
			try {
				TweakerArrayValue input = new TweakerArrayValue();
				for (int i = 0; i < ingredients.getSizeInventory(); i++) {
					if (ingredients.getStackInSlot(i) != null) {
						input.addAssign(new TweakerItemStack(ingredients.getStackInSlot(i)));
					}
				}

				TweakerItemStack output2 = new TweakerItemStack(output.get().copy());
				TweakerValue result = function.call(new TweakerNameSpace(MineTweaker.instance.getGlobal()), output2, input);
				if (result == TweakerBool.FALSE) return null;
				return output2.get();
			} catch (TweakerException ex) {
				System.out.println("[MineTweaker] " + ex.getFile().getName() + ":" + ex.getLine() + " - " + ex.getExplanation());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}
	}

	public int getRecipeSize() {
		return contents.length;
	}

	public ItemStack getRecipeOutput() {
		return output.get();
	}
}
