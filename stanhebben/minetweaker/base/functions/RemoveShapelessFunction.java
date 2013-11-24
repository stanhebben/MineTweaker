package stanhebben.minetweaker.base.functions;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerBool;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.RemoveRecipeAction;

public class RemoveShapelessFunction extends TweakerFunction {
	public static final RemoveShapelessFunction INSTANCE = new RemoveShapelessFunction();
	
	private RemoveShapelessFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("removeShapeless requires at least one argument");
		} else if (arguments.length == 1) {
			TweakerItemStackPattern output =
					notNull(arguments[0], "cannot use null as removeShapeless argument")
					.toItemStackPattern("removeShapeless argument must be a valid item stack or pattern");
			
			List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
			List<RemoveRecipeAction> actions = new ArrayList<RemoveRecipeAction>();
			for (int i = recipes.size() - 1; i >= 0; i--) {
				if (output.matches(recipes.get(i).getRecipeOutput())) {
					actions.add(new RemoveRecipeAction(i));
				}
			}
			for (RemoveRecipeAction action : actions) {
				Tweaker.apply(action);
			}
			return new TweakerInt(actions.size());
		} else {
			TweakerItemStackPattern output =
					notNull(arguments[0], "cannot use null as removeShapeless argument")
					.toItemStackPattern("removeShapeless argument must be a valid item stack or pattern");
			TweakerArray recipeArray =
					notNull(arguments[1], "cannot use null as recipe")
					.toArray("recipe must be an item pattern array");
			
			boolean wildcard = false;
			if (arguments.length >= 3) {
				TweakerValue wildcardV = arguments[2];
				if (wildcardV == null) {
					throw new TweakerExecuteException("cannot use null as removeShapeless argument");
				}
				TweakerBool wildcardBool = wildcardV.asBool();
				if (wildcardBool == null) {
					throw new TweakerExecuteException("third removeShapeless argument, if any, must be a bool value");
				}
				wildcard = wildcardBool.get();
			}
			TweakerItemPattern[] contents = new TweakerItemPattern[recipeArray.size()];
			for (int i = 0; i < recipeArray.size(); i++) {
				if (recipeArray.get(i) == null) {
					throw new TweakerExecuteException("cannot use null in the removeShapeless recipe pattern");
				}
				TweakerItemPattern pattern = recipeArray.get(i).asItemPattern();
				if (pattern == null) {
					throw new TweakerExecuteException("removeShapeless recipe pattern items must be valid items or item patterns");
				}
				contents[i] = pattern;
			}
			
			List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
			List<RemoveRecipeAction> actions = new ArrayList<RemoveRecipeAction>();
			
			outer: for (int i = recipes.size() - 1; i >= 0; i--) {
				IRecipe recipe = recipes.get(i);
				if (!output.matches(recipe.getRecipeOutput())) {
					continue outer;
				}
				
				if (recipe instanceof ShapelessRecipes) {
					ShapelessRecipes pattern = (ShapelessRecipes) recipe;
					if (!wildcard && pattern.recipeItems.size() != contents.length) {
						continue outer;
					}
					boolean[] tag = new boolean[pattern.recipeItems.size()];
					List<ItemStack> recipeContents = pattern.recipeItems;
					for (TweakerItemPattern stack : contents) {
						out: for (int j = 0; j < pattern.recipeItems.size(); j++) {
							if (!tag[j] && stack.matches(recipeContents.get(j))) {
								tag[j] = true;
								continue out;
							}
							continue outer;
						}
					}
					actions.add(new RemoveRecipeAction(i));
				} else if (recipe instanceof ShapelessOreRecipe) {
					ShapelessOreRecipe pattern = (ShapelessOreRecipe) recipe;
					if (!wildcard && pattern.getInput().size() != contents.length) {
						continue outer;
					}
					boolean[] tag = new boolean[pattern.getInput().size()];
					List recipeContents = pattern.getInput();
					out: for (TweakerItemPattern stack : contents) {
						for (int j = 0; j < recipeContents.size(); j++) {
							if (!tag[j] && stack.matches(recipeContents.get(j))) {
								tag[j] = true;
								continue out;
							}
						}
						continue outer;
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
		return "MineTweaker:recipes.removeShapeless";
	}
}
