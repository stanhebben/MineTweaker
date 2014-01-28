/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

//#ifdef MC152
//+import powercrystals.minefactoryreloaded.api.FarmingRegistry;
//#else
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
//#endif
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class FruitPickerAddLogAction implements IUndoableAction {
	private final int id;
	private final boolean existed;
	
	public FruitPickerAddLogAction(int id) {
		this.id = id;
		existed = MFRHacks.fruitLogBlocks == null ? false : MFRHacks.fruitLogBlocks.contains(id);
	}

	public void apply() {
		if (!existed) {
			//#ifdef MC152
			//+FarmingRegistry.registerFruitLogBlockId(id);
			//#else
			FactoryRegistry.registerFruitLogBlockId(id);
			//#endif
		}
	}

	public boolean canUndo() {
		return MFRHacks.fruitLogBlocks != null;
	}

	public void undo() {
		if (!existed) MFRHacks.fruitLogBlocks.remove(MFRHacks.fruitLogBlocks.size() - 1);
	}

	public String describe() {
		return "Add fruit tree log block id: " + id;
	}

	public String describeUndo() {
		return "Remove fruit tree log block id: " + id;
	}
}
