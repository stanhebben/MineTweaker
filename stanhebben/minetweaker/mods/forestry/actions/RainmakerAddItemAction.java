/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.fuels.FuelManager;
import forestry.api.fuels.RainSubstrate;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;

/**
 *
 * @author Stanneke
 */
public class RainmakerAddItemAction implements IUndoableAction {
	private final RainSubstrate substrate;
	
	public RainmakerAddItemAction(TweakerItem item, int duration, float speed, boolean reverse) {
		substrate = new RainSubstrate(item.make(1), duration, speed, reverse);
	}

	public void apply() {
		FuelManager.rainSubstrate.put(substrate.item, substrate);
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		FuelManager.rainSubstrate.remove(substrate.item);
	}

	public String describe() {
		return substrate.reverse
				? "Adding rain stopping item " + substrate.item.getDisplayName() + " to the rainmaker"
				: "Adding rain making item " + substrate.item.getDisplayName() + " to the rainmaker";
	}

	public String describeUndo() {
		return substrate.reverse
				? "Removing rain stopping item " + substrate.item.getDisplayName() + " to the rainmaker"
				: "Removing rain making item " + substrate.item.getDisplayName() + " to the rainmaker";
	}
}
