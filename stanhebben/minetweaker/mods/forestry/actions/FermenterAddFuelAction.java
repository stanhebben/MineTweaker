/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.fuels.FermenterFuel;
import forestry.api.fuels.FuelManager;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.value.TweakerItem;

/**
 *
 * @author Stanneke
 */
public class FermenterAddFuelAction implements IUndoableAction {
	private final TweakerItem fuel;
	private final int fermentPerCycle;
	private final int cycles;
	
	private ItemStack fuelItemStack;
	
	public FermenterAddFuelAction(TweakerItem fuel, int fermentPerCycle, int cycles) {
		this.fuel = fuel;
		this.fermentPerCycle = fermentPerCycle;
		this.cycles = cycles;
	}

	public void apply() {
		fuelItemStack = fuel.make(1);
		
		FuelManager.fermenterFuel.put(fuelItemStack, new FermenterFuel(fuelItemStack, fermentPerCycle, cycles));
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		FuelManager.fermenterFuel.remove(fuelItemStack);
	}

	public String describe() {
		return "Adding " + fuel.getDisplayName() + " as fermenter fuel";
	}

	public String describeUndo() {
		return "Removing fermenter fuel " + fuel.getDisplayName();
	}
}
