/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.mods.buildcraft.functions;

//#ifdef MC152
import net.minecraft.item.ItemStack;
//#else
//+import buildcraft.api.core.StackWrapper;
//+import net.minecraftforge.fluids.Fluid;
//+import stanhebben.minetweaker.mods.buildcraft.actions.RemoveSolidCoolantAction;
//#endif
import buildcraft.api.fuels.IronEngineCoolant;
import java.util.logging.Level;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.buildcraft.actions.RemoveLiquidCoolantAction;

/**
 *
 * @author Stanneke
 */
public class RemoveCoolantFunction extends TweakerFunction {
	public static final RemoveCoolantFunction INSTANCE = new RemoveCoolantFunction();
	
	private RemoveCoolantFunction() {}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("coolants.remove requires one argument");
		}
		TweakerValue coolant = notNull(arguments[0], "coolants.remove argument cannot be null");
		if (coolant.asFluid() != null) {
			//#ifdef MC152
			ItemStack coolantFluid = coolant.asFluid().get().make(1);
			RemoveLiquidCoolantAction action = null;
			for (IronEngineCoolant ieCoolant : IronEngineCoolant.coolants) {
				if (ieCoolant.liquid.isLiquidEqual(coolantFluid)) {
					action = new RemoveLiquidCoolantAction(ieCoolant);
				}
			}
			if (action != null) Tweaker.apply(action);
			//#else
			//+Fluid coolantFluid = coolant.asFluid().get();
			//+if (IronEngineCoolant.liquidCoolants.containsKey(coolantFluid.getName())) {
				//+Tweaker.apply(new RemoveLiquidCoolantAction(coolantFluid));
			//+} else {
				//+throw new TweakerExecuteException("this coolant does not exist: " + coolantFluid.getName());
			//+}
			//#endif
		} else if (coolant.asItem() != null) {
			//#ifdef MC152
			Tweaker.log(Level.WARNING, "This buildcraft version has no item coolants");
			//#else
			//+StackWrapper coolantItem = new StackWrapper(coolant.asItem().make(1));
			//+if (IronEngineCoolant.solidCoolants.containsKey(coolantItem)) {
				//+Tweaker.apply(new RemoveSolidCoolantAction(coolantItem));
			//+} else {
				//+throw new TweakerExecuteException("this coolant does not exist: " + coolantItem.stack.getDisplayName());
			//+}
			//#endif
		} else {
			throw new TweakerExecuteException("coolants.remove argument must be a fluid or item");
		}
		return null;
	}

	@Override
	public String toString() {
		return "buildcraft.coolants.remove";
	}
}
