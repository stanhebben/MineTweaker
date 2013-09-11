package stanhebben.minetweaker.base.values;

import java.util.HashMap;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.functions.FurnaceAddRecipe;
import stanhebben.minetweaker.base.functions.FurnaceRemove;
import stanhebben.minetweaker.base.functions.FurnaceRemoveRecipe;

public class FurnaceValue extends TweakerValue {
	public static final FurnaceValue INSTANCE = new FurnaceValue();
	
	private HashMap<String, OreSmeltingRecipe> oreSmeltingRecipes;
	
	private FurnaceValue() {
		oreSmeltingRecipes = new HashMap<String, OreSmeltingRecipe>();
	}
	
	@Override
	public TweakerValue index(String index) throws TweakerExecuteException {
		switch (TweakerField.get(index)) {
			case ADDRECIPE:
				return FurnaceAddRecipe.INSTANCE;
			case REMOVERECIPE:
				return FurnaceRemoveRecipe.INSTANCE;
			case REMOVE:
				return FurnaceRemove.INSTANCE;
		}
		throw new TweakerExecuteException("no such member in furnace: " + index);
	}

	@Override
	public String toString() {
		return "MineTweaker:furnace";
	}
	
	private static class OreSmeltingRecipe {
		private String ore;
		private ItemStack output;
	}
}
