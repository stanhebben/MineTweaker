/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import powercrystals.core.random.WeightedRandomItemStack;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class SludgeBoilerRemoveDropAction implements IUndoableAction {
	private final int index;
	private final WeightedRandomItemStack item;
	
	public SludgeBoilerRemoveDropAction(int index) {
		this.index = index;
		item = (WeightedRandomItemStack) MFRHacks.sludgeDrops.get(index);
	}

	public void apply() {
		MFRHacks.sludgeDrops.remove(index);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		MFRHacks.sludgeDrops.add(index, item);
	}

	public String describe() {
		return "Removing sludge drop " + item.getStack().getDisplayName();
	}

	public String describeUndo() {
		return "Restoring sludge drop " + item.getStack().getDisplayName();
	}
}
