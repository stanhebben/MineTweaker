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
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import stanhebben.minetweaker.api.value.TweakerBool;

public class ShapelessOreFunctionRecipe extends ShapelessOreRecipe {
	private TweakerValue function;
	
	public ShapelessOreFunctionRecipe(ItemStack result, Object[] recipe, TweakerValue function) {
		super(result, recipe);
		
		this.function = function;
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting) {
		ItemStack result = super.getCraftingResult(par1InventoryCrafting);
		
		try {
			TweakerValue[] values = new TweakerValue[getInput().size()];
			outer: for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); i++) {
				if (par1InventoryCrafting.getStackInSlot(i) == null) continue;
				ItemStack craftingItem = par1InventoryCrafting.getStackInSlot(i);
				
				for (int j = 0; j < getInput().size(); j++) {
					if (values[j] != null) continue;
					Object recipeObject = getInput().get(j);
					if (recipeObject instanceof ItemStack) {
						ItemStack recipeItem = (ItemStack) recipeObject;
						if (recipeItem.itemID == craftingItem.itemID
								&& (recipeItem.getItemDamage() == OreDictionary.WILDCARD_VALUE || recipeItem.getItemDamage() == craftingItem.getItemDamage())) {
							values[j] = new TweakerItemStack(craftingItem);
							continue outer;
						}
					} else {
						List<ItemStack> options = (List<ItemStack>) recipeObject;
						for (ItemStack option : options) {
							if (option.itemID == craftingItem.itemID
									&& (option.getItemDamage() == OreDictionary.WILDCARD_VALUE || option.getItemDamage() == craftingItem.getItemDamage())) {
								values[j] = new TweakerItemStack(craftingItem);
								continue outer;
							}
						}
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
