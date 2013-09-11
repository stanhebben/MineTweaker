package stanhebben.minetweaker.base.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.FurnaceRemoveMetaRecipeAction;
import stanhebben.minetweaker.base.actions.FurnaceRemoveRecipeAction;

public class FurnaceRemoveRecipe extends TweakerFunction {
	public static final FurnaceRemoveRecipe INSTANCE = new FurnaceRemoveRecipe();
	
	private FurnaceRemoveRecipe() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("at least one argument needed for furnace.removeRecipe");
		} else if (arguments.length == 1) {
			TweakerItemStackPattern output =
					notNull(arguments[0], "furnace.removeRecipe argument cannot be null")
					.toItemStackPattern("furnace.removeRecipe argument must be an item stack (or pattern)");
			
			List<IUndoableAction> actions = new ArrayList<IUndoableAction>();
			
			Map<Integer, ItemStack> smeltingList = FurnaceRecipes.smelting().getSmeltingList();
			for (Map.Entry<Integer, ItemStack> entry : smeltingList.entrySet()) {
				if (output.matches(entry.getValue())) {
					actions.add(new FurnaceRemoveRecipeAction(entry.getKey()));
				}
			}
			
			Map<List<Integer>, ItemStack> metaSmeltingList = FurnaceRecipes.smelting().getMetaSmeltingList();
			for (Map.Entry<List<Integer>, ItemStack> entry : metaSmeltingList.entrySet()) {
				if (output.matches(entry.getValue())) {
					actions.add(new FurnaceRemoveMetaRecipeAction(entry.getKey()));
				}
			}
			
			for (IUndoableAction action : actions) {
				Tweaker.apply(action);
			}
			return new TweakerInt(actions.size());
		} else {
			TweakerItemStackPattern output =
					notNull(arguments[0], "furnace.removeRecipe argument cannot be null")
					.toItemStackPattern("furnace.removeRecipe argument must be an item stack (or pattern)");
			TweakerItemPattern input =
					notNull(arguments[1], "furnace.removeRecipe recipe input cannot be null")
					.toItemPattern("furnace.removeRecipe recipe input must be an item or item pattern");
			
			List<IUndoableAction> actions = new ArrayList<IUndoableAction>();
			
			Map<Integer, ItemStack> smeltingList = FurnaceRecipes.smelting().getSmeltingList();
			for (Map.Entry<Integer, ItemStack> entry : smeltingList.entrySet()) {
				if (output.matches(entry.getValue()) && input.matches(entry.getKey())) {
					actions.add(new FurnaceRemoveRecipeAction(entry.getKey()));
				}
			}
			
			Map<List<Integer>, ItemStack> metaSmeltingList = FurnaceRecipes.smelting().getMetaSmeltingList();
			for (Map.Entry<List<Integer>, ItemStack> entry : metaSmeltingList.entrySet()) {
				if (output.matches(entry.getValue()) && input.matches(entry.getKey().get(0), entry.getKey().get(1))) {
					actions.add(new FurnaceRemoveMetaRecipeAction(entry.getKey()));
				}
			}
			
			for (IUndoableAction action : actions) {
				Tweaker.apply(action);
			}
			return new TweakerInt(actions.size());
		}
	}

	@Override
	public String toString() {
		return "MineTweaker:furnace.removeRecipe";
	}
}
