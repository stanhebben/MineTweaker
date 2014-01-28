/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import java.util.List;
import net.minecraft.item.ItemStack;
//#ifdef MC152
//+import powercrystals.minefactoryreloaded.api.FarmingRegistry;
//#else
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
//#endif
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class BreederAddFoodAction implements IUndoableAction {
	private final Class<?> entityClass;
	private final TweakerItem item;
	
	public BreederAddFoodAction(Class<?> entityClass, TweakerItem item) {
		this.entityClass = entityClass;
		this.item = item;
	}

	public void apply() {
		//#ifdef MC152
		//+FarmingRegistry.registerBreederFood(entityClass, item.make(1));
		//#else
		FactoryRegistry.registerBreederFood(entityClass, item.make(1));
		//#endif
	}

	public boolean canUndo() {
		return MFRHacks.breederFoods != null;
	}

	public void undo() {
		List<ItemStack> list = MFRHacks.breederFoods.get(entityClass);
		list.remove(list.size() - 1);
	}

	public String describe() {
		return "Adding breeder food " + item.getDisplayName() + " for " + entityClass.getCanonicalName();
	}

	public String describeUndo() {
		return "Removing breeder food " + item.getDisplayName() + " for " + entityClass.getCanonicalName();
	}
}
