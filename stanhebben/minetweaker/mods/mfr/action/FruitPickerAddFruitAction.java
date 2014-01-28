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
public class FruitPickerAddFruitAction implements IUndoableAction {
	private final TweakerItem item;
	private final IFactoryFruit fruit;
	private final IFactoryFruit old;
	
	public FruitPickerAddFruitAction(TweakerItem item, IFactoryFruit fruit) {
		this.item = item;
		this.fruit = fruit;
		
		old = MFRHacks.fruitBlocks == null ? null : MFRHacks.fruitBlocks.get(item.getItemId());
	}

	public void apply() {
		//#ifdef MC152
		//+FarmingRegistry.registerFruit(fruit);
		//#else
		FactoryRegistry.registerFruit(fruit);
		//#endif
	}

	public boolean canUndo() {
		return MFRHacks.fruitBlocks != null;
	}

	public void undo() {
		if (old == null) {
			MFRHacks.fruitBlocks.remove(item.getItemId());
		} else {
			//#ifdef MC152
			//+FarmingRegistry.registerFruit(old);
			//#else
			FactoryRegistry.registerFruit(old);
			//#endif
		}
	}

	public String describe() {
		return "Adding fruit " + item.getDisplayName();
	}

	public String describeUndo() {
		if (old == null) {
			return "Removing fruit " + item.getDisplayName();
		} else {
			return "Restoring fruit " + item.getDisplayName();
		}
	}
}
