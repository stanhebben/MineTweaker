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
public class MiningLaserRemovePreferredOreAction implements IUndoableAction {
	private final int color;
	private final int index;
	private final ItemStack item;
	
	public MiningLaserRemovePreferredOreAction(int color, int index) {
		this.color = color;
		this.index = index;
		item = MFRHacks.laserPreferredOres.get(color).get(index);
	}

	public void apply() {
		MFRHacks.laserPreferredOres.get(color).remove(index);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		MFRHacks.laserPreferredOres.get(color).add(index, item);
	}

	public String describe() {
		return "Removing laser preferred ore " + item.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring laser preferred ore " + item.getDisplayName();
	}
}
