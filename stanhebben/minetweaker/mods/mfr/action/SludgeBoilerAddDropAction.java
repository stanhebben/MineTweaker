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
import stanhebben.minetweaker.api.value.TweakerItemStack;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class SludgeBoilerAddDropAction implements IUndoableAction {
	private final TweakerItemStack item;
	private final int weight;
	
	public SludgeBoilerAddDropAction(TweakerItemStack item, int weight) {
		this.item = item;
		this.weight = weight;
	}

	public void apply() {
		//#ifdef MC152
		//+FarmingRegistry.registerSludgeDrop(weight, item.get());
		//#else
		FactoryRegistry.registerSludgeDrop(weight, item.get());
		//#endif
	}

	public boolean canUndo() {
		return MFRHacks.sludgeDrops != null;
	}

	public void undo() {
		MFRHacks.sludgeDrops.remove(MFRHacks.sludgeDrops.size() - 1);
	}

	public String describe() {
		return "Adding sludge boiler drop " + item.getDisplayName();
	}

	public String describeUndo() {
		return "Removing sludge boiler drop " + item.getDisplayName();
	}
}
