/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.fuels.EngineCopperFuel;
import forestry.api.fuels.FuelManager;
import java.util.logging.Level;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.value.TweakerItem;

/**
 *
 * @author Stanneke
 */
public class CopperEngineRemoveFuelAction implements IUndoableAction {
	private final ItemStack item;
	//#ifndef MC152
	private boolean asItem;
	//#endif
	private EngineCopperFuel fuel;
	
	public CopperEngineRemoveFuelAction(TweakerItem item) {
		this.item = item.make(1);
	}

	public void apply() {
		//#ifndef MC152
		if (FuelManager.copperEngineFuel.containsKey(item)) {
			asItem = false;
			fuel = FuelManager.copperEngineFuel.remove(item);
		} else if (FuelManager.copperEngineFuel.containsKey(item.getItem())) {
			asItem = true;
			fuel = FuelManager.copperEngineFuel.remove(item.getItem());
		//#else
		//+if (FuelManager.copperEngineFuel.containsKey(item)) {
			//+fuel = FuelManager.copperEngineFuel.remove(item);
		//#endif
		} else {
			Tweaker.log(Level.WARNING, "Did not found peat-fired engine fuel " + item.getDisplayName());
		}
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		if (fuel != null) {
			//#ifndef MC152
			if (asItem) {
				FuelManager.copperEngineFuel.put(item.getItem(), fuel);
			} else {
				FuelManager.copperEngineFuel.put(item, fuel);
			}
			//#else
			//+FuelManager.copperEngineFuel.put(item, fuel);
			//#endif
		}
	}

	public String describe() {
		return "Removing peat-fired engine fuel " + item.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring peat-fired engine fuel " + item.getDisplayName();
	}
}
