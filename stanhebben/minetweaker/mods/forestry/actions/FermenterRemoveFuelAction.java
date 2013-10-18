/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.fuels.FermenterFuel;
import forestry.api.fuels.FuelManager;
import java.util.logging.Level;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.Tweaker;

/**
 *
 * @author Stanneke
 */
public class FermenterRemoveFuelAction implements IUndoableAction {
	private final ItemStack item;
	private FermenterFuel fuel = null;
	//#ifndef MC152
	private boolean asItem = false;
	//#endif
	
	public FermenterRemoveFuelAction(ItemStack item) {
		this.item = item;
	}

	public void apply() {
		//#ifndef MC152
		if (FuelManager.fermenterFuel.containsKey(item)) {
			fuel = FuelManager.fermenterFuel.remove(item);
			asItem = false;
		} else if (FuelManager.fermenterFuel.containsKey(item.getItem())) {
			fuel = FuelManager.fermenterFuel.remove(item.getItem());
			asItem = true;
		//#else
		//+if (FuelManager.fermenterFuel.containsKey(item)) {
		//#endif
			fuel = FuelManager.fermenterFuel.remove(item);
		} else {
			Tweaker.log(Level.WARNING, "Could not find " + item.getDisplayName() + " as fermenter fuel");
		}
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		if (fuel != null) {
			//#ifndef MC152
			if (asItem) {
				FuelManager.fermenterFuel.put(item.getItem(), fuel);
			} else {
				FuelManager.fermenterFuel.put(item, fuel);
			}
			//#else
			//+FuelManager.fermenterFuel.put(item, fuel);
			//#endif
		}
	}

	public String describe() {
		return "Removing fermenter fuel " + item.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring fermenter fuel " + item.getDisplayName();
	}
}
