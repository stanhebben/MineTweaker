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
import stanhebben.minetweaker.api.value.TweakerItemStackPattern;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.actions.FurnaceRemoveMetaRecipeAction;
import stanhebben.minetweaker.base.actions.FurnaceRemoveRecipeAction;

public class FurnaceRemove extends TweakerFunction {
	public static final FurnaceRemove INSTANCE = new FurnaceRemove();
	
	private FurnaceRemove() {}
	
	public int remove(TweakerItemStackPattern pattern) {
		List<IUndoableAction> actions = new ArrayList<IUndoableAction>();
		
		Map<Integer, ItemStack> smeltingList = FurnaceRecipes.smelting().getSmeltingList();
		for (Map.Entry<Integer, ItemStack> entry : smeltingList.entrySet()) {
			if (pattern.matches(entry.getValue())) {
				actions.add(new FurnaceRemoveRecipeAction(entry.getKey()));
			}
		}

		Map<List<Integer>, ItemStack> metaSmeltingList = FurnaceRecipes.smelting().getMetaSmeltingList();
		for (Map.Entry<List<Integer>, ItemStack> entry : metaSmeltingList.entrySet()) {
			if (pattern.matches(entry.getValue())) {
				actions.add(new FurnaceRemoveMetaRecipeAction(entry.getKey()));
			}
		}

		for (IUndoableAction action : actions) {
			Tweaker.apply(action);
		}
		
		return actions.size();
	}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("at least one argument needed for furnace.remove");
		} else {
			TweakerItemStackPattern output =
					notNull(arguments[0], "furnace.remove argument cannot be null")
					.toItemStackPattern("furnace.remove argument must be an item stack (or pattern)");
			
			return new TweakerInt(remove(output));
		}
	}

	@Override
	public String toString() {
		return "MineTweaker:furnace.removeRecipe";
	}
}
