package stanhebben.minetweaker.base.functions;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerArray;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.AddRecipeAction;
import stanhebben.minetweaker.base.functions.recipes.ShapelessAdvancedRecipe;
import stanhebben.minetweaker.base.functions.recipes.ShapelessFunctionRecipes;
import stanhebben.minetweaker.base.functions.recipes.ShapelessOreFunctionRecipe;

public class AddShapelessFunction extends TweakerFunction {
	public static final AddShapelessFunction INSTANCE = new AddShapelessFunction();
	
	private AddShapelessFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2) {
			throw new TweakerExecuteException("addShapeless requires at least 2 arguments");
		}
		
		TweakerItemStack target = 
				notNull(arguments[0], "shapeless recipe output must not be null")
				.toItemStack("shapeless recipe output must be an item stack");
		
		TweakerArray recipe = 
				notNull(arguments[1], "shapeless recipe must not be null")
				.toArray("shapeless recipe must be an array of recipe items");
		
		boolean hasAdvancedItems = false;
		boolean hasOreItems = false;
		
		for (int i = 0; i < recipe.size(); i++) {
			TweakerValue item = recipe.get(i);
			if (item != null) {
				if (item.asRecipeItem() != null) {
					hasOreItems |= item.asRecipeItem().getClass() == String.class;
				} else if (item.asItemStackPattern() != null) {
					hasAdvancedItems = true;
				} else {
					throw new TweakerExecuteException("shapeless recipe items must be a valid item stack pattern");
				}
			}
		}
		
		if (hasAdvancedItems) {
			TweakerItemStackPattern[] recipeArray = new TweakerItemStackPattern[recipe.size()];
			for (int i = 0; i < recipe.size(); i++) {
				TweakerValue value = recipe.get(i);
				recipeArray[i] = (value == null ? null : value.asItemStackPattern());
			}
			
			if (arguments.length >= 3) {
				Tweaker.apply(new AddRecipeAction(new ShapelessAdvancedRecipe(target, recipeArray, arguments[2])));
			} else {
				Tweaker.apply(new AddRecipeAction(new ShapelessAdvancedRecipe(target, recipeArray, null)));
			}
		} else if (hasOreItems) {
			Object[] recipeArray = new Object[recipe.size()];
			for (int i = 0; i < recipeArray.length; i++) {
				TweakerValue value = recipe.get(i);
				recipeArray[i] = value == null ? null : value.toRecipeItem("error");
			}
			if (arguments.length >= 3) {
				Tweaker.apply(new AddRecipeAction(new ShapelessOreFunctionRecipe(target.get(), recipeArray, arguments[2])));
			} else {
				Tweaker.apply(new AddRecipeAction(new ShapelessOreRecipe(target.get(), recipeArray)));
			}
		} else {
			ArrayList<ItemStack> recipeArray = new ArrayList<ItemStack>();
			for (int i = 0; i < recipe.size(); i++) {
				TweakerValue value = recipe.get(i);
				recipeArray.add((ItemStack) (value == null ? null : value.toRecipeItem("error")));
			}
			
			if (arguments.length >= 3) {
				Tweaker.apply(new AddRecipeAction(new ShapelessFunctionRecipes(target.get(), recipeArray, arguments[2])));
			} else {
				Tweaker.apply(new AddRecipeAction(new ShapelessRecipes(target.get(), recipeArray)));
			}
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "MineTweaker:recipes.addShapeless";
	}
}
