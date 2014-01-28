/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.mfr.action;

import java.util.List;
import net.minecraft.item.ItemStack;
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
public class MiningLaserAddPreferredOreAction implements IUndoableAction {
	private final int color;
	private final TweakerItem ore;
	
	public MiningLaserAddPreferredOreAction(int color, TweakerItem ore) {
		this.color = color;
		this.ore = ore;
	}

	public void apply() {
		//#ifdef MC152
		//+FarmingRegistry.setLaserPreferredOre(color, ore.make(1));
		//#else
		FactoryRegistry.addLaserPreferredOre(color, ore.make(1));
		//#endif
	}

	public boolean canUndo() {
		return MFRHacks.laserPreferredOres != null;
	}

	public void undo() {
		List<ItemStack> ores = MFRHacks.laserPreferredOres.get(color);
		ores.remove(ores.size() - 1);
	}

	public String describe() {
		return "Adding preferred laser ore " + ore.getDisplayName();
	}

	public String describeUndo() {
		return "Removing preferred laser ore " + ore.getDisplayName();
	}
}
