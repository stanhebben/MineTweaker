/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class FruitPickerRemoveLogAction implements IUndoableAction {
	private final int index;
	private final int value;
	
	public FruitPickerRemoveLogAction(int index) {
		this.index = index;
		value = MFRHacks.fruitLogBlocks.get(index);
	}

	public void apply() {
		MFRHacks.fruitLogBlocks.remove(index);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		MFRHacks.fruitLogBlocks.add(index, value);
	}

	public String describe() {
		return "Removing fruit log block id " + value;
	}

	public String describeUndo() {
		return "Restoring fruit log block id " + value;
	}
}
