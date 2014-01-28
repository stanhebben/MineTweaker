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
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.mods.mfr.MFRHacks;

/**
 *
 * @author Stanneke
 */
public class MiningLaserAddOreAction implements IUndoableAction {
	private final TweakerItem item;
	private final int weight;
	
	public MiningLaserAddOreAction(TweakerItem item, int weight) {
		this.item = item;
		this.weight = weight;
	}

	public void apply() {
		//#ifdef MC152
		//+FarmingRegistry.registerLaserOre(weight, item.make(1));
		//#else
		FactoryRegistry.registerLaserOre(weight, item.make(1));
		//#endif
	}

	public boolean canUndo() {
		return MFRHacks.laserOres != null;
	}

	public void undo() {
		MFRHacks.laserOres.remove(MFRHacks.laserOres.size() - 1);
	}

	public String describe() {
		return "Adding laser ore " + item.getDisplayName();
	}

	public String describeUndo() {
		return "Removing laser ore " + item.getDisplayName();
	}
}
