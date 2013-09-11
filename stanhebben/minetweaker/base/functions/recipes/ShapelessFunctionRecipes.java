package stanhebben.minetweaker.base.functions.recipes;

import java.util.Arrays;
import java.util.List;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerArrayValue;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerValue;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import stanhebben.minetweaker.api.value.TweakerBool;

public class ShapelessFunctionRecipes extends ShapelessRecipes {
	private TweakerValue function;
	
	public ShapelessFunctionRecipes(ItemStack par1ItemStack, List par2List, TweakerValue function) {
		super(par1ItemStack, par2List);
		
		this.function = function;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting) {
		ItemStack result = super.getCraftingResult(par1InventoryCrafting);
		
		try {
			TweakerValue[] values = new TweakerValue[recipeItems.size()];
			outer: for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); i++) {
				if (par1InventoryCrafting.getStackInSlot(i) == null) continue;
				ItemStack craftingItem = par1InventoryCrafting.getStackInSlot(i);
				
				for (int j = 0; j < recipeItems.size(); j++) {
					if (values[j] != null) continue;
					ItemStack recipeItem = (ItemStack) recipeItems.get(j);
					if (recipeItem.itemID == craftingItem.itemID
							&& (recipeItem.getItemDamage() == OreDictionary.WILDCARD_VALUE || recipeItem.getItemDamage() == craftingItem.getItemDamage())) {
						values[j] = new TweakerItemStack(craftingItem);
						continue outer;
					}
				}
				
				throw new TweakerExecuteException("No matching found between recipe and crafting");
			}
			
			TweakerArrayValue craftingArray = new TweakerArrayValue(Arrays.asList(values));
			TweakerValue ret = function.call(Tweaker.getGlobalWrapped(), new TweakerItemStack(result), craftingArray);
			if (ret == TweakerBool.FALSE) return null;
		} catch (TweakerException ex) {
			System.out.println("[MineTweaker] " + ex.getFile().getName() + ":" + ex.getLine() + " - " + ex.getExplanation());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
