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
public class HarvesterAddHarvestableAction implements IUndoableAction {
	private final TweakerItem item;
	private final IFactoryHarvestable harvestable;
	private final IFactoryHarvestable old;
	
	public HarvesterAddHarvestableAction(TweakerItem item, IFactoryHarvestable harvestable) {
		this.item = item;
		this.harvestable = harvestable;
		
		old = MFRHacks.harvestables == null ? null : MFRHacks.harvestables.get(item.getItemId());
	}

	public void apply() {
		//#ifdef MC152
		//+FarmingRegistry.registerHarvestable(harvestable);
		//#else
		FactoryRegistry.registerHarvestable(harvestable);
		//#endif
	}

	public boolean canUndo() {
		return MFRHacks.harvestables != null;
	}

	public void undo() {
		if (old == null) {
			MFRHacks.harvestables.remove(item.getItemId());
		} else {
			FactoryRegistry.registerHarvestable(harvestable);
		}
	}

	public String describe() {
		return "Adding " + harvestable.getHarvestType() + " harvestable " + item.getDisplayName();
	}

	public String describeUndo() {
		if (old == null) {
			return "Removing harvestable " + item.getDisplayName();
		} else {
			return "Restoring harvestable " + item.getDisplayName();
		}
	}
}
