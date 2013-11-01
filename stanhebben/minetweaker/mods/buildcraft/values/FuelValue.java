/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.values;

//#ifdef MC152
//+import buildcraft.api.fuels.IronEngineFuel;
//#else
import buildcraft.api.fuels.IronEngineFuel.Fuel;
//#endif
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerFloat;
import stanhebben.minetweaker.api.value.TweakerLiquid;
import stanhebben.minetweaker.api.value.TweakerInt;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.actions.SetFuelAction;

/**
 *
 * @author Stanneke
 */
public class FuelValue extends TweakerValue {
	//#ifdef MC152
	//+private IronEngineFuel fuel;
	
	//+public FuelValue(IronEngineFuel fuel) {
		//+this.fuel = fuel;
	//+}
	//#else
	private Fuel fuel;
	
	public FuelValue(Fuel fuel) {
		this.fuel = fuel;
	}
	//#endif
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case LIQUID:
				return new TweakerLiquid(fuel.liquid);
			case POWERPERCYCLE:
				return new TweakerFloat(fuel.powerPerCycle);
			case TOTALBURNINGTIME:
				return new TweakerInt(fuel.totalBurningTime);
		}
		
		throw new TweakerExecuteException("Fuel has no " + index + " field");
	}
	
	@Override
	public void indexSet(String index, TweakerValue value) {
		switch (TweakerField.get(index)) {
			case POWERPERCYCLE: {
				float powerPerCycle =
						notNull(value, "power per cycle cannot be set to null")
						.toFloat("power per cycle must be a float value").get();
				Tweaker.apply(new SetFuelAction(fuel.liquid, powerPerCycle, fuel.totalBurningTime));
				return;
			}
			case TOTALBURNINGTIME: {
				int totalBurningTime =
						notNull(value, "total burning time cannot be set to null")
						.toInt("total burning time must be an int value").get();
				Tweaker.apply(new SetFuelAction(fuel.liquid, fuel.powerPerCycle, totalBurningTime));
				return;
			}
		}
		throw new TweakerExecuteException("No such settable member in fuel: " + index);
	}

	@Override
	public String toString() {
		//#ifdef MC152
		//+return "fuel:" + fuel.liquid.asItemStack().getItemName();
		//#else
		return "fuel:" + fuel.liquid.getName();
		//#endif
	}
}
