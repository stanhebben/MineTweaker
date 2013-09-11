package stanhebben.minetweaker.base.actions;

import java.util.Arrays;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerItemStack;

public class FurnaceAddRecipeAction implements IUndoableAction {
	private ItemStack output;
	private TweakerItem input;
	private float xp;
	
	public FurnaceAddRecipeAction(TweakerItemStack output, TweakerItem input, float xp) {
		this.output = output.get();
		this.input = input;
		this.xp = xp;
	}
	
	@Override
	public void apply() {
		FurnaceRecipes.smelting().addSmelting(input.getItemId(), input.getItemSubId(), output, xp);
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public void undo() {
		FurnaceRecipes.smelting()
			.getMetaSmeltingList()
			.remove(Arrays.asList(input.getItemId(), input.getItemSubId()));
	}

	public String describe() {
		return "Adding a furnace recipe turning " + input.getDisplayName() + " into " + output.getDisplayName();
	}

	public String describeUndo() {
		return "Removing a furnace recipe turning " + input.getDisplayName() + " into " + output.getDisplayName();
	}
}
