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
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class HarvesterRemoveHarvestableAction implements IUndoableAction {
	private final TweakerItem item;
	private final IFactoryHarvestable old;
	
	public HarvesterRemoveHarvestableAction(TweakerItem item) {
		this.item = item;
		old = MFRHacks.harvestables.get(item.getItemId());
	}

	public void apply() {
		MFRHacks.harvestables.remove(item.getItemId());
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifdef MC152
		//+FarmingRegistry.registerHarvestable(old);
		//#else
		FactoryRegistry.registerHarvestable(old);
		//#endif
	}

	public String describe() {
		return "Removing harvestable " + item.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring harvestable " + item.getDisplayName();
	}
}
