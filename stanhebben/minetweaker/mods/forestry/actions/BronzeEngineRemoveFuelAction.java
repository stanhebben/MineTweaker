/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.forestry.actions;

import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import java.util.logging.Level;
import stanhebben.minetweaker.api.IUndoableAction;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.value.TweakerLiquid;

/**
 *
 * @author Stanneke
 */
public class BronzeEngineRemoveFuelAction implements IUndoableAction {
	private final TweakerLiquid liquid;
	private EngineBronzeFuel fuel;
	
	public BronzeEngineRemoveFuelAction(TweakerLiquid liquid) {
		this.liquid = liquid;
	}

	public void apply() {
		//#ifdef MC152
		//+fuel = FuelManager.bronzeEngineFuel.remove(liquid.get().make(1));
		//#else
		fuel = FuelManager.bronzeEngineFuel.remove(liquid.get());
		//#endif
		if (fuel == null) Tweaker.log(Level.WARNING, "Biogas engine fuel " + liquid.getDisplayName() + " not found");
	}

	public boolean canUndo() {
		return true;
	}

	public void undo() {
		//#ifdef MC152
		//+if (fuel != null) FuelManager.bronzeEngineFuel.put(fuel.liquid, fuel);
		//#else
		if (fuel != null) FuelManager.bronzeEngineFuel.put(liquid.get(), fuel);
		//#endif
	}

	public String describe() {
		return "Removing biogas engine fuel " + liquid.getDisplayName();
	}

	public String describeUndo() {
		return "Restoring biogas engine fuel " + liquid.getDisplayName();
	}
}
