package stanhebben.minetweaker.mods.te.functions;

import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.applyAction;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getBool;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getFluidStack;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getInt;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getItemStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;

public class TransposerAddFillRecipeFunction extends TweakerFunction {
	public static final TransposerAddFillRecipeFunction INSTANCE = new TransposerAddFillRecipeFunction();
	
	private TransposerAddFillRecipeFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 4 || arguments.length > 5) {
			throw new TweakerExecuteException("adding a transposer fill recipe requires 4-5 arguments");
		}
		
		Integer energy = getInt(arguments, 0, "energy");
		ItemStack input = getItemStack(arguments, 1, "input");
		FluidStack fluid = getFluidStack(arguments, 2, "fluid");
		ItemStack output = getItemStack(arguments, 3, "output");
		Boolean reversible = getBool(arguments, 4, "reversible", true);
		
		applyAction("TransposerFillRecipe", input + " + " + fluid + " => " + output,
				"energy", energy,
				"input", input,
				"fluid", fluid,
				"output", output,
				"reversible", reversible);
		
		return null;
	}
	
	@Override
	public String toString() {
		return "transposer.addFillRecipe";
	}
}
