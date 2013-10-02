/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.values;

import java.util.LinkedList;

import buildcraft.api.fuels.IronEngineFuel;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerFluid;
import stanhebben.minetweaker.api.value.TweakerString;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.functions.AddFuelFunction;
import stanhebben.minetweaker.mods.buildcraft.functions.ContainsFuelFunction;
import stanhebben.minetweaker.mods.buildcraft.functions.RemoveFuelFunction;

/**
 *
 * @author Stanneke
 */
public class FuelsValue extends TweakerValue {
	public static final FuelsValue INSTANCE = new FuelsValue();
	
	private FuelsValue() {}

	@Override
	public TweakerValue index(TweakerValue index) {
		if (index.getClass() == TweakerString.class) {
			return index(index.toBasicString());
		} else if (index.asFluid() != null) {
			TweakerFluid fluid = index.asFluid();
			//#ifdef MC152
			//+for (IronEngineFuel fuel : (LinkedList<IronEngineFuel>) IronEngineFuel.fuels) {
				//+if (fluid.equalsFluid(fuel.liquid)) {
					//+return new FuelValue(fuel);
				//+}
			//+}
			//+throw new TweakerExecuteException("No such fuel: " + fluid.getDisplayName());
			//#else
			if (!IronEngineFuel.fuels.containsKey(fluid.get().getName())) {
				throw new TweakerExecuteException("No such fuel: " + fluid.get().getLocalizedName());
			}
			return new FuelValue((IronEngineFuel.Fuel) IronEngineFuel.fuels.get(fluid.get().getName()));
			//#endif
		} else {
			return super.index(index);
		}
	}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADD:
				return AddFuelFunction.INSTANCE;
			case REMOVE:
				return RemoveFuelFunction.INSTANCE;
			case CONTAINS:
				return ContainsFuelFunction.INSTANCE;
		}
		throw new TweakerExecuteException("No such member in buildcraft.fuels: " + index);
	}
	
	@Override
	public String toString() {
		return "buildcraft.fuels";
	}
}
