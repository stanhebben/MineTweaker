package stanhebben.minetweaker.base.functions;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import stanhebben.minetweaker.MineTweakerUtil;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.RemoveRecipeAction;

public class RemoveShapedFunction extends TweakerFunction {
	public static final RemoveShapedFunction INSTANCE = new RemoveShapedFunction();
	
	private RemoveShapedFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("At least one argument required for removeShaped");
		} else if (arguments.length == 1) {
			TweakerItemStackPattern target = 
					notNull(arguments[0], "removeShaped argument cannot be null")
					.toItemStackPattern("removeShaped argument must be an item stack or item stack pattern");
			
			List<RemoveRecipeAction> actions = new ArrayList<RemoveRecipeAction>();
			List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
			for (int i = recipes.size() - 1; i >= 0; i--) {
				IRecipe recipe = recipes.get(i);
				if (((recipe instanceof ShapedRecipes) || (recipe instanceof ShapedOreRecipe))
						&& target.matches(recipe.getRecipeOutput())) {
					actions.add(new RemoveRecipeAction(i));
				}
			}
			for (RemoveRecipeAction action : actions) {
				Tweaker.apply(action);
			}
			return new TweakerInt(actions.size());
		} else {
			TweakerItemStackPattern target =
					notNull(arguments[0], "removeShaped recipe output cannot be null")
					.toItemStackPattern("removeShaped recipe output must be an item stack or pattern");
			TweakerArray recipeValues =
					notNull(arguments[1], "removeShaped recipe cannot be null")
					.toArray("removeShaped recipe must be a twodimensional array");
			
			ArrayList<ArrayList<TweakerItemPattern>> pattern = new ArrayList<ArrayList<TweakerItemPattern>>();
			for (int i = 0; i < recipeValues.size(); i++) {
				TweakerArray recipeRow = 
						notNull(recipeValues.get(i), "cannot use null and shaped recipe row")
						.toArray("recipe row should be an item pattern array");
				
				ArrayList<TweakerItemPattern> patternRow = new ArrayList<TweakerItemPattern>();
				for (int j = 0; j < recipeRow.size(); j++) {
					TweakerValue itemV = recipeRow.get(j);
					if (itemV == null) {
						patternRow.add(null);
					} else {
						TweakerItemPattern patternItem = itemV.toItemPattern("recipe item should be an item pattern");
						patternRow.add(patternItem);
					}
				}
				pattern.add(patternRow);
			}
			
			List<RemoveRecipeAction> actions = new ArrayList<RemoveRecipeAction>();
			List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
			outer: for (int i = recipes.size() - 1; i >= 0; i--) {
				IRecipe recipe = recipes.get(i);
				if ((recipe instanceof ShapedRecipes) && target.matches(recipe.getRecipeOutput())) {
					ShapedRecipes recipeShaped = (ShapedRecipes) recipe;
					if (recipeShaped.recipeHeight != pattern.size()) continue;
					
					for (int j = 0; j < pattern.size(); j++) {
						ArrayList<TweakerItemPattern> patternRow = pattern.get(j);
						int off = j * recipeShaped.recipeWidth;
						if (patternRow.size() > recipeShaped.recipeWidth) continue outer;
						for (int k = 0; k < patternRow.size(); k++) {
							TweakerItemPattern patternItem = patternRow.get(k);
							ItemStack item = recipeShaped.recipeItems[off + k];
							
							if ((patternItem == null) && (item == null)) continue;
							if (patternItem == null) continue outer;
							if (!patternItem.matches(item)) continue outer;
						}
						for (int k = patternRow.size(); k < recipeShaped.recipeWidth; k++) {
							if (recipeShaped.recipeItems[off + k] != null) {
								continue outer;
							}
						}
					}
					actions.add(new RemoveRecipeAction(i));
				} else if ((recipe instanceof ShapedOreRecipe) && target.matches(recipe.getRecipeOutput())) {
					ShapedOreRecipe recipeShaped = (ShapedOreRecipe) recipe;
					int recipeShapedWidth = MineTweakerUtil.getShapedOreRecipeWidth(recipeShaped);
					
					for (int j = 0; j < pattern.size(); j++) {
						ArrayList<TweakerItemPattern> patternRow = pattern.get(j);
						int off = j * recipeShapedWidth;
						if (patternRow.size() > recipeShapedWidth) continue outer;
						for (int k = 0; k < patternRow.size(); k++) {
							TweakerItemPattern patternItem = patternRow.get(k);
							Object item = recipeShaped.getInput()[off + k];
							
							if ((patternItem == null) && (item == null)) continue;
							if (patternItem == null) continue outer;
							if (!patternItem.matches(item)) continue outer;
						}
						for (int k = patternRow.size(); k < recipeShapedWidth; k++) {
							if (recipeShaped.getInput()[off + k] != null) {
								continue outer;
							}
						}
					}
					actions.add(new RemoveRecipeAction(i));
				}
			}
			for (RemoveRecipeAction action : actions) {
				Tweaker.apply(action);
			}
			return new TweakerInt(actions.size());
		}
	}

	@Override
	public String toString() {
		return "MineTweaker:recipes.removeShaped";
	}
}
