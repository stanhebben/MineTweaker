/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.functions;

//#ifdef MC152
//#else
import buildcraft.api.core.StackWrapper;
import net.minecraftforge.fluids.FluidContainerRegistry;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.mods.buildcraft.actions.AddSolidCoolantAction;
//#endif
import buildcraft.api.fuels.IronEngineCoolant;
import buildcraft.api.fuels.IronEngineFuel;

import java.util.LinkedList;
import java.util.logging.Level;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerLiquid;
import stanhebben.minetweaker.api.value.TweakerLiquidStack;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.actions.AddLiquidCoolantAction;

/**
 *
 * @author Stanneke
 */
public class AddCoolantFunction extends TweakerFunction {
	public static final AddCoolantFunction INSTANCE = new AddCoolantFunction();
	
	private AddCoolantFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 2) {
			throw new TweakerExecuteException("coolants.add requires at least 2 arguments");
		}
		
		TweakerValue coolant = notNull(arguments[0], "coolants.add coolant cannot be null");
		if (coolant.asFluid() != null) {
			// fluid coolant
			TweakerLiquid coolantFluid = coolant.asFluid();
			//#ifdef MC152
			//+for (IronEngineCoolant ieCoolant : (LinkedList<IronEngineCoolant>) IronEngineCoolant.coolants) {
				//+if (coolantFluid.equalsFluid(ieCoolant.liquid)) {
					//+throw new TweakerExecuteException(coolantFluid.getName() + " is already a coolant");
				//+}
			//+}
			//#else
			if (IronEngineCoolant.isCoolant(coolantFluid.get())) {
				throw new TweakerExecuteException(coolantFluid.getName() + " is already a coolant");
			}
			//#endif
			float cooling =
					notNull(arguments[1], "coolants.add cooling value cannot be null")
					.toFloat("coolants.add cooling value must be a float value").get();
			
			Tweaker.apply(new AddLiquidCoolantAction(coolantFluid.get(), cooling));
		} else if (coolant.asItem() != null) {
			// item coolant
			//#ifdef MC152
			//+Tweaker.log(Level.WARNING, "This version of BuildCraft has no solid coolants.");
			//#else
			TweakerItem item = coolant.asItem();
			if (IronEngineCoolant.solidCoolants.containsKey(new StackWrapper(item.make(1)))) {
				throw new TweakerExecuteException(item.getDisplayName() + " is already a coolant");
			} else if (FluidContainerRegistry.isContainer(item.make(1))) {
				Tweaker.log(Level.INFO, item.getDisplayName() + " is already registered as fluid");
			} else {
				TweakerLiquidStack cooling =
						notNull(arguments[1], "coolants.add cooling value must not be null")
						.toFluidStack("coolants.add cooling value must be a fluid stack");
				if (IronEngineCoolant.liquidCoolants.containsKey(cooling.get().getFluid().getName())) {
					Tweaker.apply(new AddSolidCoolantAction(item.make(1), cooling.get()));
				} else {
					throw new TweakerExecuteException(cooling.get().getFluid().getLocalizedName() + " must be registered as coolant first");
				}
			}
			//#endif
		} else {
			throw new TweakerExecuteException("coolants.add requires either a fluid or item");
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "buildcraft.coolants.add";
	}
}
