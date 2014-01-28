/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class BreederRemoveFoodAction implements IUndoableAction {
	private final Class<?> entityClass;
	private final int index;
	private final ItemStack item;
	
	public BreederRemoveFoodAction(Class<?> entityClass, int index) {
		this.entityClass = entityClass;
		this.index = index;
		item = MFRHacks.breederFoods.get(entityClass).get(index);
	}
	
	public void apply() {
		MFRHacks.breederFoods.get(entityClass).remove(index);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		MFRHacks.breederFoods.get(entityClass).add(index, item);
	}

	public String describe() {
		return "Removing breeder food " + item.getDisplayName() + " for " + entityClass.getCanonicalName();
	}

	public String describeUndo() {
		return "Restoring breeder food " + item.getDisplayName() + " for " + entityClass.getCanonicalName();
	}
}
