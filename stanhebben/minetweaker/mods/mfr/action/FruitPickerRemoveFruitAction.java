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
import powercrystals.minefactoryreloaded.api.IFactoryFruit;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class FruitPickerRemoveFruitAction implements IUndoableAction {
	private final TweakerItem item;
	private final IFactoryFruit old;
	
	public FruitPickerRemoveFruitAction(TweakerItem item) {
		this.item = item;
		old = MFRHacks.fruitBlocks.get(item.getItemId());
	}

	public void apply() {
		MFRHacks.fruitBlocks.remove(item.getItemId());
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifdef MC152
		//+FarmingRegistry.registerFruit(old);
		//#else
		FactoryRegistry.registerFruit(old);
		//#endif
	}

	public String describe() {
		return "Removing fruit " + item.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring fruit " + item.getDisplayName();
	}
}
