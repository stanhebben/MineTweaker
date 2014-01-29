package stanhebben.minetweaker.mods.te;

import static stanhebben.minetweaker.api.value.TweakerValue.notNull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.minetweaker.api.Tweaker;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.mods.te.actions.ThermalExpansionAction;

public class ThermalExpansionUtil {
	private ThermalExpansionUtil() {}
	
	public static Integer getInt(TweakerValue[] arguments, int index, String name, Integer _default) {
		return arguments.length >= index ? _default :
				(Integer) (notNull(arguments[index], name + " cannot be null")
				.toInt(name + " must be an int").get());
	}
	public static Integer getInt(TweakerValue[] arguments, int index, String name) {
		return getInt(arguments, index, name, null);
	}
	
	public static Boolean getBool(TweakerValue[] arguments, int index, String name, Boolean _default) {
		return arguments.length >= index ? _default :
				(Boolean) (notNull(arguments[index], name + " cannot be null")
				.toBool(name + " must be a bool").get());
	}
	public static Boolean getBool(TweakerValue[] arguments, int index, String name) {
		return getBool(arguments, index, name, null);
	}
	
	public static ItemStack getItemStack(TweakerValue[] arguments, int index, String name, ItemStack _default) {
		return arguments.length >= index ? _default :
				notNull(arguments[index], name + " cannot be null")
				.toItemStack(name + " must be an item stack").get();
	}
	public static ItemStack getItemStack(TweakerValue[] arguments, int index, String name) {
		return getItemStack(arguments, index, name, null);
	}
	
	public static Fluid getFluid(TweakerValue[] arguments, int index, String name, Fluid _default) {
		return arguments.length >= index ? _default :
				notNull(arguments[index], name + " cannot be null")
				.toFluid(name + " must be a fluid stack").get();
	}
	public static Fluid getFluid(TweakerValue[] arguments, int index, String name) {
		return getFluid(arguments, index, name, null);
	}
	
	public static FluidStack getFluidStack(TweakerValue[] arguments, int index, String name, FluidStack _default) {
		return arguments.length >= index ? _default :
				notNull(arguments[index], name + " cannot be null")
				.toFluidStack(name + " must be a fluid stack").get();
	}
	public static FluidStack getFluidStack(TweakerValue[] arguments, int index, String name) {
		return getFluidStack(arguments, index, name, null);
	}
	
	public static void applyAction(String key, String description, Object... values) {
		NBTTagCompound nbt = new NBTTagCompound();
		
		for (int i = 0; i < values.length; i += 2) {
			String name = (String)values[i];
			Object value = values[i + 1];
			
			if (value instanceof Integer) {
				nbt.setInteger(name, (Integer)value);
			} else if (value instanceof Boolean) {
				nbt.setBoolean(name, (Boolean)value);
			} else if (value instanceof ItemStack) {
				nbt.setCompoundTag(name, ((ItemStack)value).writeToNBT(new NBTTagCompound()));
			} else if (value instanceof FluidStack) {
				nbt.setCompoundTag(name, ((FluidStack)value).writeToNBT(new NBTTagCompound()));
			}
		}
		
		Tweaker.apply(new ThermalExpansionAction(key, nbt, description));
	}
}
