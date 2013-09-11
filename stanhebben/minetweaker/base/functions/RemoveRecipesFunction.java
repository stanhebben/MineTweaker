package stanhebben.minetweaker.base.functions;

import java.util.List;

import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.RemoveRecipeAction;

public class RemoveRecipesFunction extends TweakerFunction {
	public static final RemoveRecipesFunction INSTANCE = new RemoveRecipesFunction();
	
	private RemoveRecipesFunction() {}
	
	public int remove(TweakerItemStackPattern pattern) {
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
		int numRecipesRemoved = 0;
		for (int i = recipes.size() - 1; i >= 0; i--) {
			if (pattern.matches(recipes.get(i).getRecipeOutput())) {
				Tweaker.apply(new RemoveRecipeAction(i));
				numRecipesRemoved++;
			}
		}
		return numRecipesRemoved;
	}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("remove requires one argument");
		} else {
			TweakerItemStackPattern pattern = 
					notNull(arguments[0], "cannot use null as remove argument")
					.toItemStackPattern("remove argument must be an item or item pattern");
			
			return new TweakerInt(remove(pattern));
		}
	}

	@Override
	public String toString() {
		return "MineTweaker:recipes.remove";
	}
}
