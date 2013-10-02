package stanhebben.minetweaker.base.functions;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;

import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerException;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.AddRecipeAction;
import stanhebben.minetweaker.base.functions.recipes.ShapedAdvancedRecipe;
import stanhebben.minetweaker.base.functions.recipes.ShapedFunctionRecipes;
import stanhebben.minetweaker.base.functions.recipes.ShapedOreFunctionRecipe;

public class AddShapedFunction extends TweakerFunction {
	public static final AddShapedFunction INSTANCE = new AddShapedFunction();
	
	private AddShapedFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace,
			TweakerValue... arguments) throws TweakerExecuteException,
			TweakerException {
		if (arguments.length < 2) {
			throw new TweakerExecuteException("Missing arguments for addShaped");
		}
		
		TweakerItemStack target = 
				notNull(arguments[0], "cannot use null as addShaped recipe output")
				.toItemStack("addShaped recipe output must be an item stack");
		
		TweakerArray recipe = 
				notNull(arguments[1], "cannot use null as addShaped recipe")
				.toArray("addShaped recipe must be a twodimensional item array");
		
		int height = recipe.size();
		int width = 0;
		boolean hasOreEntries = false;
		boolean hasAdvancedEntries = false;
		
		for (int i = 0; i < height; i++) {
			TweakerArray recipeRow = 
					notNull(recipe.get(i), "addShaped recipe row must not be null")
					.toArray("addShaped recipe row must be an item array");
			
			width = Math.max(width, recipeRow.size());
			
			for (int j = 0; j < recipeRow.size(); j++) {
				if (recipeRow.get(j) != null) {
					Object item = recipeRow.get(j).asRecipeItem();
					if (item != null) {
						if (item.getClass() == String.class) {
							hasOreEntries = true;
						}
					} else if (recipeRow.get(j).asItemStackPattern() != null) {
						hasAdvancedEntries = true;
					} else {
						throw new TweakerExecuteException("each item in a shaped recipe must be a valid item stack pattern, or null");
					}
				}
			}
		}
		
		if (hasAdvancedEntries) {
			TweakerItemStackPattern[] stacks = new TweakerItemStackPattern[width * height];
			for (int i = 0; i < height; i++) {
				TweakerArray row = recipe.get(i).asArray();
				for (int j = 0; j < row.size(); j++) {
					int ix = i * width + j;
					if (row.get(j) != null) {
						stacks[ix] = row.get(j).asItemStackPattern();
					}
				}
			}
			if (arguments.length >= 3) {
				Tweaker.apply(new AddRecipeAction(new ShapedAdvancedRecipe(target, stacks, width, arguments[2])));
			} else {
				Tweaker.apply(new AddRecipeAction(new ShapedAdvancedRecipe(target, stacks, width, null)));
			}
		} else if (hasOreEntries) {
			int counter = 0;
			String[] parts = new String[height];
			ArrayList rarguments = new ArrayList();
			for (int i = 0; i < height; i++) {
				StringBuilder pattern = new StringBuilder();
				TweakerArray row = recipe.get(i).asArray();
				for (int j = 0; j < row.size(); j++) {
					if (row.get(j) != null) {
						pattern.append((char) ('A' + counter));
						rarguments.add(Character.valueOf((char) ('A' + counter)));
						counter++;
						rarguments.add(row.get(j).toRecipeItem("error"));
					} else {
						pattern.append(' ');
					}
				}
				for (int j = row.size(); j < width; j++) {
					pattern.append(' ');
				}
				parts[i] = pattern.toString();
			}
			rarguments.add(0, parts);
			
			if (arguments.length >= 3) {
				Tweaker.apply(new AddRecipeAction(new ShapedOreFunctionRecipe(target.get(), rarguments.toArray(), arguments[2])));
			} else {
				Tweaker.apply(new AddRecipeAction(new ShapedOreRecipe(target.get(), rarguments.toArray())));
			}
		} else {
			ItemStack[] stacks = new ItemStack[width * height];
			for (int i = 0; i < height; i++) {
				TweakerArray row = recipe.get(i).asArray();
				for (int j = 0; j < row.size(); j++) {
					int ix = i * width + j;
					if (row.get(j) != null) {
						stacks[ix] = (ItemStack) row.get(j).toRecipeItem("error");
					}
				}
			}
			if (arguments.length >= 3) {
				Tweaker.apply(new AddRecipeAction(new ShapedFunctionRecipes(width, height, stacks, target.get(), arguments[2])));
			} else {
				Tweaker.apply(new AddRecipeAction(new ShapedRecipes(width, height, stacks, target.get())));
			}
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "MineTweaker:recipes.addShaped";
	}
}
