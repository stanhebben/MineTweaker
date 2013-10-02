package stanhebben.minetweaker.base.functions.recipes;

import stanhebben.minetweaker.MineTweaker;
import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArrayValue;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import stanhebben.minetweaker.api.value.TweakerBool;

public class ShapedOreFunctionRecipe extends ShapedOreRecipe {
	private final TweakerValue function;
	
	public ShapedOreFunctionRecipe(ItemStack result, Object[] recipe, TweakerValue function) {
		super(result, recipe);
		
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
			if (ret == TweakerBool.FALSE) return null;
		} catch (TweakerException ex) {
			System.out.println("[MineTweaker] " + ex.getFile().getName() + ":" + ex.getLine() + " - " + ex.getExplanation());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
