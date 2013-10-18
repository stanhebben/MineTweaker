/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.fuels.FuelManager;
import forestry.api.fuels.MoistenerFuel;
import java.util.logging.Level;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.Tweaker;

/**
 *
 * @author Stanneke
 */
public class MoistenerRemoveFuelAction implements IUndoableAction {
	private final ItemStack item;
	//#ifndef MC152
	private boolean asItem;
	//#endif
	private MoistenerFuel fuel;
	
	public MoistenerRemoveFuelAction(ItemStack item) {
		this.item = item;
	}

	public void apply() {
		//#ifndef MC152
		if (FuelManager.moistenerResource.containsKey(item)) {
			asItem = false;
			fuel = FuelManager.moistenerResource.remove(item);
		} else if (FuelManager.moistenerResource.containsKey(item.getItem())) {
			asItem = true;
			fuel = FuelManager.moistenerResource.remove(item.getItem());
		//#else
		//+if (FuelManager.moistenerResource.containsKey(item)) {
			//+fuel = FuelManager.moistenerResource.remove(item);
		//#endif
		} else {
			Tweaker.log(Level.WARNING, "Could not find moistener fuel " + item.getDisplayName());
		}
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		if (fuel != null) {
			//#ifndef MC152
			if (asItem) {
				FuelManager.moistenerResource.put(item.getItem(), fuel);
			} else {
				FuelManager.moistenerResource.put(item, fuel);
			}
			//#else
			//+FuelManager.moistenerResource.put(item, fuel);
			//#endif
		}
	}

	public String describe() {
		return "Removing moistener fuel " + item.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring moistener fuel " + item.getDisplayName();
	}
}
