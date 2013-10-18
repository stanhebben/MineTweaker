/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.api.value;

//#ifdef MC152
//+import java.util.logging.Level;
//+import net.minecraftforge.liquids.LiquidContainerRegistry;
//+import net.minecraftforge.liquids.LiquidStack;
//#else
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.minetweaker.base.actions.FluidSetDensityAction;
import stanhebben.minetweaker.base.actions.FluidSetGaseousAction;
import stanhebben.minetweaker.base.actions.FluidSetLuminosityAction;
import stanhebben.minetweaker.base.actions.FluidSetTemperatureAction;
import stanhebben.minetweaker.base.actions.SetLocalizedStringAction;
//#endif
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.base.functions.AddFluidContainerFunction;
import stanhebben.minetweaker.base.functions.RemoveFluidContainerFunction;

/**
 *
 * @author Stanneke
 */
public class TweakerFluid extends TweakerValue {
	public static TweakerFluid fromLiquidBlock(TweakerItem block) {
		//#ifdef MC152
		//+if (LiquidContainerRegistry.isLiquid(block.make(1))) {
			//+return new TweakerFluid(block);
		//+} else {
			//+return null;
		//+}
		//#else
		for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
			if (fluid.getBlockID() == block.getItemId()) {
				return new TweakerFluid(fluid);
			}
		}
		return null;
		//#endif
	}
	
	public static TweakerFluid fromLiquidContainer(TweakerItem container) {
		//#ifdef MC152
		//+if (LiquidContainerRegistry.isFilledContainer(container.make(1))) {
			//+return new TweakerFluid(LiquidContainerRegistry.getLiquidForFilledItem(container.make(1)));
		//+} else {
			//+throw new TweakerExecuteException(container.getDisplayName() + " is not a liquid container");
		//+}
		//#else
		if (FluidContainerRegistry.isFilledContainer(container.make(1))) {
			return new TweakerFluid(FluidContainerRegistry.getFluidForFilledItem(container.make(1)).getFluid());
		} else {
			return null;
		}
		//#endif
	}
	
	//#ifdef MC152
	//+private TweakerItem fluid; // equals the still liquid block
	
	//+public TweakerFluid(LiquidStack fluid) {
		//+this.fluid = TweakerItem.get(fluid.asItemStack());
	//+}
	
	//+public TweakerFluid(TweakerItem fluid) {
		//+this.fluid = fluid;
	//+}
	
	//+public TweakerItem get() {
		//+return fluid;
	//+}
	
	//+public LiquidStack make(int amount) {
		//+return new LiquidStack(fluid.getItemId(), amount, fluid.getItemSubId());
	//+}
	
	//+public boolean equalsFluid(TweakerItem fluid) {
		//+if (fluid == null) return false;
		//+return this.fluid.getItemId() == fluid.getItemId() && this.fluid.getItemSubId() == fluid.getItemSubId();
	//+}
	
	//+public boolean equalsFluid(LiquidStack fluid) {
		//+if (fluid == null) return false;
		//+return this.fluid.getItemId() == fluid.itemID && this.fluid.getItemSubId() == fluid.itemMeta;
	//+}
	//#else
	private Fluid fluid;
	
	public TweakerFluid(Fluid fluid) {
		this.fluid = fluid;
	}
	
	public Fluid get() {
		return fluid;
	}
	
	public FluidStack make(int amount) {
		return new FluidStack(fluid, amount);
	}
	
	public boolean equalsFluid(Fluid fluid) {
		if (fluid == null) return false;
		return fluid.getName().equals(this.fluid.getName());
	}
	//#endif
	
	public String getName() {
		return fluid.getName();
	}
	
	public String getDisplayName() {
		//#ifdef MC152
		//+return fluid.getDisplayName();
		//#else
		return fluid.getLocalizedName();
		//#endif
	}
	
	@Override
	public TweakerFluid asFluid() {
		return this;
	}
	
	@Override
	public TweakerFluidStack asFluidStack() {
		//#ifdef MC152
		//+return new TweakerFluidStack(new LiquidStack(fluid.getItemId(), 1, fluid.getItemSubId()));
		//#else
		return new TweakerFluidStack(new FluidStack(fluid, 1));
		//#endif
	}
	
	@Override
	public TweakerValue mul(TweakerValue value) {
		int amount = 
				notNull(value, "cannot multiply a fluid by null")
				.toInt("can only multiply a fluid by an int value")
				.get();
		//#ifdef MC152
		//+return new TweakerFluidStack(new LiquidStack(fluid.getItemId(), amount, fluid.getItemSubId()));
		//#else
		return new TweakerFluidStack(new FluidStack(fluid, amount));
		//#endif
	}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case NAME:
				//#ifdef MC152
				//+return new TweakerString(fluid.getName());
				//#else
				return new TweakerString(fluid.getUnlocalizedName());
				//#endif
			case DISPLAYNAME:
				//#ifdef MC152
				//+return new TweakerString(fluid.getDisplayName());
				//#else
				return new TweakerString(fluid.getLocalizedName());
				//#endif
			case LUMINOSITY:
				//#ifdef MC152
				//+return TweakerInt.ZERO;
				//#else
				return new TweakerInt(fluid.getLuminosity());
				//#endif
			case DENSITY:
				//#ifdef MC152
				//+return TweakerInt.ZERO;
				//#else
				return new TweakerInt(fluid.getDensity());
				//#endif
			case TEMPERATURE:
				//#ifdef MC152
				//+return TweakerInt.ZERO;
				//#else
				return new TweakerInt(fluid.getTemperature());
				//#endif
			case GASEOUS:
				//#ifdef MC152
				//+return TweakerBool.FALSE;
				//#else
				return TweakerBool.get(fluid.isGaseous());
				//#endif
			case ADDCONTAINER:
				return new AddFluidContainerFunction(fluid);
			case REMOVECONTAINER:
				return new RemoveFluidContainerFunction(fluid);
			default:
				throw new TweakerExecuteException("no such field in fluid: " + index);
		}
	}
	
	@Override
	public void indexSet(String index, TweakerValue value) {
		switch (TweakerField.get(index)) {
			case DISPLAYNAME:
				//#ifdef MC152
				//+Tweaker.log(Level.WARNING, "MineCraft Forge 1.5.2 has no separate fluid names, command ignored");
				//#else
				Tweaker.apply(new SetLocalizedStringAction(fluid.getUnlocalizedName(), "en_US", value.toBasicString()));
				//#endif
				return;
			case LUMINOSITY:
				//#ifdef MC152
				//+Tweaker.log(Level.WARNING, "Minecraft Forge 1.5.2 has no fluid luminosity field, command ignored");
				//#else
				Tweaker.apply(new FluidSetLuminosityAction(fluid, 
						notNull(value, "luminosity value cannot be null")
						.toInt("luminosity value must be an int").get()));
				//#endif
				return;
			case DENSITY:
				//#ifdef MC152
				//+Tweaker.log(Level.WARNING, "Minecraft Forge 1.5.2 has no fluid density field, command ignored");
				//#else
				Tweaker.apply(new FluidSetDensityAction(fluid,
						notNull(value, "density value cannot be null")
						.toInt("density value must be an int").get()));
				//#endif
				return;
			case TEMPERATURE:
				//#ifdef MC152
				//+Tweaker.log(Level.WARNING, "Minecraft Forge 1.5.2 has no fluid temperature field, command ignored");
				//#else
				Tweaker.apply(new FluidSetTemperatureAction(fluid,
						notNull(value, "temperature value cannot be null")
						.toInt("temperature value must be an int").get()));
				//#endif
				return;
			case GASEOUS:
				//#ifdef MC152
				//+Tweaker.log(Level.WARNING, "Minecraft Forge 1.5.2 has no fluid gaseous field, command ignored");
				//#else
				Tweaker.apply(new FluidSetGaseousAction(fluid,
						notNull(value, "gaseous value cannot be null")
						.toBool("gaseous value must be a bool").get()));
				//#endif
				return;
			default:
				throw new TweakerExecuteException("no such settable field in fluid: " + index);
		}
	}
	
	@Override
	public boolean equals(TweakerValue other) {
		TweakerFluid asFluid = other.asFluid();
		if (asFluid == null) return false;
		//#ifdef MC152
		//+return asFluid.fluid.getItemId() == fluid.getItemId()
			//+&& asFluid.fluid.getItemSubId() == fluid.getItemSubId();
		//#else
		return asFluid.fluid.getName().equals(fluid.getName());
		//#endif
	}

	@Override
	public String toString() {
		//#ifdef MC152
		//+return "Fluid:" + fluid.getDisplayName();
		//#else
		return "Fluid:" + fluid.getUnlocalizedName();
		//#endif
	}
}
