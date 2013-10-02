/*
 * To change this template, choose Tools | Templates
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
public class ShapedAdvancedRecipe implements IRecipe {
	private final TweakerItemStackPattern[] contents;
	private final TweakerItemStack output;
	private final int width;
	private final int height;
	private final TweakerValue function;
	
	public ShapedAdvancedRecipe(TweakerItemStack output, TweakerItemStackPattern[] contents, int width, TweakerValue function) {
		this.output = output;
		this.contents = contents;
		this.width = width;
		height = contents.length / width;
		this.function = function;
	}

	public boolean matches(InventoryCrafting ingredients, World world) {
		int size = (int) Math.sqrt(ingredients.getSizeInventory());
		for (int i = 0; i < size - width; i++) {
			for (int j = 0; j < size - height; j++) {
				if (matches(i, j, ingredients, world)) return true;
			}
		}
		return false;
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
		return width * height;
	}

	public ItemStack getRecipeOutput() {
		return output.get();
	}
	
	private boolean matches(int offx, int offy, InventoryCrafting ingredients, World world) {
		for (int i = offx; i < 3; i++) {
			for (int j = offy; j < 3; j++) {
				if (i < height && j < width) {
					int off = i * width + j;
					if (contents[off] == null) {
						return ingredients.getStackInRowAndColumn(i, j) == null;
					} else if (!contents[off].matches(ingredients.getStackInRowAndColumn(i, j))) {
						return false;
					}
				} else {
					if (ingredients.getStackInRowAndColumn(i, j) != null) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
