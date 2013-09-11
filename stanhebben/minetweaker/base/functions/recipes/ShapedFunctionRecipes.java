package stanhebben.minetweaker.base.functions.recipes;

import stanhebben.minetweaker.MineTweaker;
import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArrayValue;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import stanhebben.minetweaker.api.value.TweakerBool;

public class ShapedFunctionRecipes extends ShapedRecipes {
	private TweakerValue function;
	
	public ShapedFunctionRecipes(
			int par1, int par2, ItemStack[] par3ArrayOfItemStack, ItemStack par4ItemStack,
			TweakerValue function) {
		super(par1, par2, par3ArrayOfItemStack, par4ItemStack);
		
		this.function = function;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting crafting) {
		ItemStack result = super.getCraftingResult(crafting);
		
		try {
			TweakerArrayValue input = new TweakerArrayValue();
			for (int i = 0; i < crafting.getSizeInventory(); i++) {
				if (crafting.getStackInSlot(i) != null) {
					input.addAssign(new TweakerItemStack(crafting.getStackInSlot(i)));
				}
			}
			
			TweakerValue ret = function.call(new TweakerNameSpace(MineTweaker.instance.getGlobal()), new TweakerItemStack(result), input);
			if (ret != null && ret == TweakerBool.FALSE) return null;
		} catch (TweakerException ex) {
			System.out.println("[MineTweaker] " + ex.getFile().getName() + ":" + ex.getLine() + " - " + ex.getExplanation());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
