package stanhebben.minetweaker.api.value;

import net.minecraft.inventory.IInventory;
//#ifdef MC152
//+import net.minecraft.item.ItemStack;
//+import net.minecraftforge.liquids.LiquidContainerRegistry;
//+import net.minecraftforge.liquids.LiquidStack;
//#else
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
//#endif
import stanhebben.minetweaker.api.TweakerExecuteException;

/**
 * Represents a liquid amount. Both the liquid amount and type are specified.
 * 
 * @author Stan Hebben
 */
public final class TweakerLiquidStack extends TweakerValue {
	public static TweakerLiquidStack fromLiquidBlock(TweakerItem block) {
		//#ifdef MC152
		//+if (LiquidContainerRegistry.isLiquid(block.make(1))) {
			//+return new TweakerLiquidStack(new LiquidStack(block.getItemId(), 1, block.getItemSubId()));
		//+} else {
			//+return null;
		//+}
		//#else
		for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
			if (fluid.getBlockID() == block.getItemId()) {
				return new TweakerLiquidStack(new FluidStack(fluid, 1));
			}
		}
		return null;
		//#endif
	}
	//#ifdef MC152
	//+private final LiquidStack value;
	
	//+public TweakerLiquidStack(LiquidStack value) {
		//+this.value = value;
	//+}
	
	//+public LiquidStack get() {
		//+return value;
	//+}
	//#else
	private final FluidStack value;
	
	public TweakerLiquidStack(FluidStack value) {
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
	
	public TweakerLiquidStack fill(IInventory inventory, TweakerItem[] containers) {
		//#ifdef MC152
		//+LiquidStack remaining = new LiquidStack(value.itemID, value.amount);
		//#else
		FluidStack remaining = new FluidStack(value.fluidID, value.amount);
		//#endif
		int size = inventory.getSizeInventory();
		for (int i = 0; i < size; i++) {
			ItemStack stack = inventory.getStackInSlot(i);
			if (stack != null) {
				for (TweakerItem container : containers) {
					if (container.matches(stack)) {
						//#ifdef MC152
						//+if (LiquidContainerRegistry.containsLiquid(stack, value)) {
						//#else
						if (FluidContainerRegistry.containsFluid(stack, value)) {
						//#endif
							while (stack.stackSize > 0) {
								//#ifdef MC152
								//+ItemStack filled = LiquidContainerRegistry.fillLiquidContainer(remaining, stack);
								//#else
								ItemStack filled = FluidContainerRegistry.fillFluidContainer(remaining, stack);
								//#endif
								if (filled == null) break;
								
								boolean stored = false;
								int empty = -1;
								for (int j = 0; j < size; j++) {
									ItemStack stack2 = inventory.getStackInSlot(j);
									if (stack2 != null) {
										if (filled.itemID == stack2.itemID
											&& filled.getItemDamage() == stack2.getItemDamage()
											&& stack2.stackSize < stack2.getMaxStackSize()) {
											stack2.stackSize++;
											stored = true;
										}
									} else {
										if (empty < 0) empty = j;
									}
								}
								if (!stored) {
									if (stack.stackSize == 1) empty = i;
									if (empty >= 0) {
										inventory.setInventorySlotContents(empty, stack);
										stored = true;
									}
								}
								
								if (stored) {
									//#ifdef MC152
									//+remaining.amount -= LiquidContainerRegistry.getLiquidForFilledItem(filled).amount;
									//#else
									remaining.amount -= FluidContainerRegistry.getFluidForFilledItem(filled).amount;
									//#endif
								} else {
									break;
								}
							}
						}
					}
				}
			}
		}
		return new TweakerLiquidStack(remaining);
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
				//+return new TweakerLiquid(item);
				//#else
				return new TweakerLiquid(value.getFluid());
				//#endif
			default:
				throw new TweakerExecuteException("no such field in fluid: " + index);
		}
	}
	
	@Override
	public TweakerLiquidStack asFluidStack() {
		return this;
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
