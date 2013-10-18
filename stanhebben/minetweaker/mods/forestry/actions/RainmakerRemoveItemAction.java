/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.fuels.FuelManager;
import forestry.api.fuels.RainSubstrate;
import java.util.logging.Level;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.Tweaker;

/**
 *
 * @author Stanneke
 */
public class RainmakerRemoveItemAction implements IUndoableAction {
	private final ItemStack item;
	//#ifndef MC152
	private boolean asItem;
	//#endif
	private RainSubstrate substrate;
	
	public RainmakerRemoveItemAction(ItemStack item) {
		this.item = item;
	}

	public void apply() {
		//#ifndef MC152
		if (FuelManager.rainSubstrate.containsKey(item)) {
			asItem = false;
			substrate = FuelManager.rainSubstrate.remove(item);
		} else if (FuelManager.rainSubstrate.containsKey(item.getItem())) {
			asItem = true;
			substrate = FuelManager.rainSubstrate.remove(item.getItem());
		//#else
		//+if (FuelManager.rainSubstrate.containsKey(item)) {
			//+substrate = FuelManager.rainSubstrate.remove(item);
		//#endif
		} else {
			Tweaker.log(Level.WARNING, "Rainmaker item " + item.getDisplayName() + " not found");
		}
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		if (substrate != null) {
			//#ifndef MC152
			if (asItem) {
				FuelManager.rainSubstrate.put(item.getItem(), substrate);
			} else {
				FuelManager.rainSubstrate.put(item, substrate);
			}
			//#else
			//+FuelManager.rainSubstrate.put(item, substrate);
			//#endif
		}
	}

	public String describe() {
		return "Removing rainmaker item " + item.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring rainmaker item " + item.getDisplayName();
	}
}
