/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stanhebben.minetweaker.api.value;

//#ifdef MC152
//+import net.minecraft.item.ItemStack;
//+import net.minecraftforge.liquids.LiquidContainerRegistry;
//+import net.minecraftforge.liquids.LiquidStack;
//#else
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
//#endif
import stanhebben.minetweaker.api.TweakerExecuteException;
import static stanhebben.minetweaker.api.value.TweakerField.DENSITY;
import static stanhebben.minetweaker.api.value.TweakerField.DISPLAYNAME;
import static stanhebben.minetweaker.api.value.TweakerField.GASEOUS;
import static stanhebben.minetweaker.api.value.TweakerField.LUMINOSITY;
import static stanhebben.minetweaker.api.value.TweakerField.NAME;
import static stanhebben.minetweaker.api.value.TweakerField.TEMPERATURE;

/**
 *
 * @author Stanneke
 */
public class TweakerFluidStack extends TweakerValue {
	public static TweakerFluidStack fromLiquidBlock(TweakerItem block) {
		//#ifdef MC152
		//+if (LiquidContainerRegistry.isLiquid(block.make(1))) {
			//+return new TweakerFluidStack(new LiquidStack(block.getItemId(), 1, block.getItemSubId()));
		//+} else {
			//+return null;
		//+}
		//#else
		for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
			if (fluid.getBlockID() == block.getItemId()) {
				return new TweakerFluidStack(new FluidStack(fluid, 1));
			}
		}
		return null;
		//#endif
	}
	//#ifdef MC152
	//+private LiquidStack value;
	
	//+public TweakerFluidStack(LiquidStack value) {
		//+this.value = value;
	//+}
	
	//+public LiquidStack get() {
		//+return value;
	//+}
	//#else
	private FluidStack value;
	
	public TweakerFluidStack(FluidStack value) {
		this.value = value;
	}
	
	public FluidStack get() {
		return value;
	}
	//#endif
	
	public String getName() {
		//#ifdef MC152
		//+return value.asItemStack().getDisplayName();
		//#else
		return value.getFluid().getName();
		//#endif
	}
	
	public String getDisplayName() {
		//#ifdef MC152
		//+return value.asItemStack().getDisplayName();
		//#else
		return value.getFluid().getLocalizedName();
		//#endif
	}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case NAME:
				//#ifdef MC152
				//+return new TweakerString(value.asItemStack().getItemName());
				//#else
				return new TweakerString(value.getFluid().getUnlocalizedName());
				//#endif
			case DISPLAYNAME:
				//#ifdef MC152
				//+return new TweakerString(value.asItemStack().getDisplayName());
				//#else
				return new TweakerString(value.getFluid().getLocalizedName());
				//#endif
			case LUMINOSITY:
				//#ifdef MC152
				//+return new TweakerInt(0);
				//#else
				return new TweakerInt(value.getFluid().getLuminosity(value));
				//#endif
			case DENSITY:
				//#ifdef MC152
				//+return new TweakerInt(0);
				//#else
				return new TweakerInt(value.getFluid().getDensity(value));
				//#endif
			case TEMPERATURE:
				//#ifdef MC152
				//+return new TweakerInt(0);
				//#else
				return new TweakerInt(value.getFluid().getTemperature(value));
				//#endif
			case GASEOUS:
				//#ifdef MC152
				//+return TweakerBool.FALSE;
				//#else
				return TweakerBool.get(value.getFluid().isGaseous(value));
				//#endif
			case AMOUNT:
				return new TweakerInt(value.amount);
			case TAG:
				//#ifdef MC152
				//+return new TweakerNBTCompound(value.extra);
				//#else
				return new TweakerNBTCompound(value.tag);
				//#endif
			case FLUID:
				//#ifdef MC152
				//+ItemStack asStack = value.asItemStack();
				//+TweakerItem item;
				//+if (asStack.getHasSubtypes()) {
					//+item = new TweakerItemSub(asStack.itemID, asStack.getItemDamage());
				//+} else {
					//+item = new TweakerItemSimple(asStack.itemID);
				//+}
				//+return new TweakerFluid(item);
				//#else
				return new TweakerFluid(value.getFluid());
				//#endif
			default:
				throw new TweakerExecuteException("no such field in fluid: " + index);
		}
	}

	@Override
	public String toString() {
		//#ifdef MC152
		//+return "FluidStack:" + value.asItemStack().getItemName() + " * " + value.amount;
		//#else
		return "FluidStack:" + value.getFluid().getUnlocalizedName() + " * " + value.amount;
		//#endif
	}
}
