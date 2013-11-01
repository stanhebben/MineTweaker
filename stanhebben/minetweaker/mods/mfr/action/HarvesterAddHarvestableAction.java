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

/**
 *
 * @author Stanneke
 */
public class HarvesterAddHarvestableAction implements IUndoableAction {
	private final TweakerItem item;
	private final IFactoryHarvestable harvestable;
	
	public HarvesterAddHarvestableAction(TweakerItem item, IFactoryHarvestable harvestable) {
		this.item = item;
		this.harvestable = harvestable;
	}

	public void apply() {
		//#ifdef MC152
		//+FarmingRegistry.registerHarvestable(harvestable);
		//#else
		FactoryRegistry.registerHarvestable(harvestable);
		//#endif
	}

	public boolean canUndo() {
		return false;
	}

	public void undo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public String describe() {
		return "Adding " + harvestable.getHarvestType() + " harvestable " + item.getDisplayName();
	}

	public String describeUndo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
