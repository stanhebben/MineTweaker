/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class PlanterRemovePlantableAction implements IUndoableAction {
	private final TweakerItem item;
	private final IFactoryPlantable old;
	
	public PlanterRemovePlantableAction(TweakerItem item) {
		this.item = item;
		old = MFRHacks.plantables.get(item.getItemId());
	}

	public void apply() {
		MFRHacks.plantables.remove(item.getItemId());
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		FactoryRegistry.registerPlantable(old);
	}

	public String describe() {
		return "Removing plantable " + item.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring plantable " + item.getDisplayName();
	}
}
