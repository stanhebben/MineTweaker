package stanhebben.minetweaker.mods.te.functions;

import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.applyAction;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getFluidStack;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getInt;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getItemStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;

public class CrucibleAddRecipeFunction extends TweakerFunction {
	public static final CrucibleAddRecipeFunction INSTANCE = new CrucibleAddRecipeFunction();
	
	private CrucibleAddRecipeFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 3) {
			throw new TweakerExecuteException("adding a crucible recipe requires 3 arguments");
		}
		
		Integer energy = getInt(arguments, 0, "energy");
		ItemStack input = getItemStack(arguments, 1, "input");
		FluidStack output = getFluidStack(arguments, 2, "output");
		
		applyAction("CrucibleRecipe", input + " => " + output,
				"energy", energy,
				"input", input,
				"output", output);
		
		return null;
	}
	
	@Override
	public String toString() {
		return "crucible.addRecipe";
	}
}
