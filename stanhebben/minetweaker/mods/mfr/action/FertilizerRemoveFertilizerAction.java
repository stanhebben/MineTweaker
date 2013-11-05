/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import powercrystals.minefactoryreloaded.api.IFactoryFertilizer;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class FertilizerRemoveFertilizerAction implements IUndoableAction {
	private final TweakerItem item;
	private final IFactoryFertilizer old;
	
	public FertilizerRemoveFertilizerAction(TweakerItem item) {
		this.item = item;
		this.old = MFRHacks.fertilizers.get(item.getItemId());
	}

	public void apply() {
		MFRHacks.fertilizers.remove(item.getItemId());
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		FactoryRegistry.registerFertilizer(old);
	}

	public String describe() {
		return "Removing fertilizer " + item.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring fertilizer " + item.getDisplayName();
	}
}
