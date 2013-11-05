/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import powercrystals.minefactoryreloaded.api.IFactoryFertilizable;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class FertilizerRemoveFertilizableAction implements IUndoableAction {
	private final TweakerItem item;
	private final IFactoryFertilizable old;
	
	public FertilizerRemoveFertilizableAction(TweakerItem item) {
		this.item = item;
		old = MFRHacks.fertilizables.get(item.getItemId());
	}

	public void apply() {
		MFRHacks.fertilizables.remove(item.getItemId());
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		FactoryRegistry.registerFertilizable(old);
	}

	public String describe() {
		return "Removing fertilizable " + item.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring fertilizable " + item.getDisplayName();
	}
}
