/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import net.minecraft.util.WeightedRandomItem;
import powercrystals.core.random.WeightedRandomItemStack;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class MiningLaserRemoveOreAction implements IUndoableAction {
	private final int index;
	private final WeightedRandomItem item;
	
	public MiningLaserRemoveOreAction(int index) {
		this.index = index;
		item = MFRHacks.laserOres.get(index);
	}

	public void apply() {
		MFRHacks.laserOres.remove(index);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		MFRHacks.laserOres.add(item);
	}

	public String describe() {
		return "Removing laser ore " + ((WeightedRandomItemStack) item).getStack().getDisplayName();
	}

	public String describeUndo() {
		return "Restoring laser ore " + ((WeightedRandomItemStack) item).getStack().getDisplayName();
	}
}
