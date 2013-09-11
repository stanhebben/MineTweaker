package stanhebben.minetweaker.base.values;

//#ifdef MC152
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
//#else
//+import net.minecraftforge.fluids.Fluid;
//+import net.minecraftforge.fluids.FluidRegistry;
//+import net.minecraftforge.fluids.FluidStack;
//#endif
import stanhebben.minetweaker.MineTweakerRegistry;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerFluid;
import stanhebben.minetweaker.api.value.TweakerFluidStack;
import stanhebben.minetweaker.api.value.TweakerValue;

public class FluidGroupValue extends TweakerValue {
	private String name;
	
	public FluidGroupValue() {
		this.name = null;
	}
	
	public FluidGroupValue(String name) {
		this.name = name;
	}
	
	@Override
	public TweakerValue mul(TweakerValue value) throws TweakerExecuteException {
		//#ifdef MC152
		LiquidStack fluid = LiquidDictionary.getCanonicalLiquid(name);
		//#else
		//+Fluid fluid = FluidRegistry.getFluid(name);
		//#endif
		if (fluid == null) return super.mul(value);
		return new TweakerFluid(fluid).mul(value);
	}
	
	@Override
	public TweakerFluid asFluid() {
		//#ifdef MC152
		LiquidStack fluid = LiquidDictionary.getCanonicalLiquid(name);
		//#else
		//+Fluid fluid = FluidRegistry.getFluid(name);
		//#endif
		if (fluid == null) return null;
		return new TweakerFluid(fluid);
	}
	
	@Override
	public TweakerFluidStack asFluidStack() {
		//#ifdef MC152
		LiquidStack fluid = LiquidDictionary.getCanonicalLiquid(name);
		if (fluid == null) return null;
		return new TweakerFluidStack(new LiquidStack(fluid.itemID, 1, fluid.itemMeta));
		//#else
		//+Fluid fluid = FluidRegistry.getFluid(name);
		//+if (fluid == null) return null;
		//+return new TweakerFluidStack(new FluidStack(fluid, 1));
		//#endif
	}
	
	@Override
	public TweakerValue index(String index) throws TweakerExecuteException {
		String newName = name == null ? index : name + '.' + index;
		
		//#ifdef MC152
		if (MineTweakerRegistry.INSTANCE.isFluidPrefix(newName)) {
			return new FluidGroupValue(newName);
		} else if (LiquidDictionary.getCanonicalLiquid(newName) != null) {
			return new TweakerFluid(LiquidDictionary.getCanonicalLiquid(newName));
		} else if (LiquidDictionary.getCanonicalLiquid(name) != null) {
			return new TweakerFluid(LiquidDictionary.getCanonicalLiquid(name)).index(index);
		} else {
			throw new TweakerExecuteException("no such fluid: " + newName);
		}
		//#else
		//+if (MineTweakerRegistry.INSTANCE.isFluidPrefix(newName)) {
			//+return new FluidGroupValue(newName);
		//+} else if (FluidRegistry.getFluid(newName) != null) {
			//+return new TweakerFluid(FluidRegistry.getFluid(newName));
		//+} else if (FluidRegistry.getFluid(name) != null) {
			//+return new TweakerFluid(FluidRegistry.getFluid(name)).index(index);
		//+} else {
			//+throw new TweakerExecuteException("no such fluid: " + newName);
		//+}
		//#endif
	}
	
	@Override
	public void indexSet(TweakerValue index, TweakerValue value) throws TweakerExecuteException {
		//#ifdef MC152
		LiquidStack fluid = LiquidDictionary.getCanonicalLiquid(name);
		//#else
		//+Fluid fluid = FluidRegistry.getFluid(name);
		//#endif
		if (fluid == null) {
			super.indexSet(index, value);
		} else {
			new TweakerFluid(fluid).indexSet(index, value);
		}
	}

	@Override
	public String toString() {
		return name == null ? "fluids" : name;
	}
}
