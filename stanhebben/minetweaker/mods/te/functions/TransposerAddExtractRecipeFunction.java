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

public class TransposerAddExtractRecipeFunction extends TweakerFunction {
	public static final TransposerAddExtractRecipeFunction INSTANCE = new TransposerAddExtractRecipeFunction();
	
	private TransposerAddExtractRecipeFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 4 || arguments.length > 6) {
			throw new TweakerExecuteException("adding a transposer extract recipe requires 4-6 arguments");
		}
		
		Integer energy = getInt(arguments, 0, "energy");
		ItemStack input = getItemStack(arguments, 1, "input");
		ItemStack output = getItemStack(arguments, 2, "output");
		FluidStack fluid = getFluidStack(arguments, 3, "fluid");
		Boolean reversible = getBool(arguments, 4, "reversible", true);
		Integer chance = getInt(arguments, 5, "chance", 100);
		
		applyAction("TransposerExtractRecipe", input + " => " + output + " + " + fluid,
				"energy", energy,
				"input", input,
				"output", output,
				"fluid", fluid,
				"reversible", reversible,
				"chance", chance);
		
		return null;
	}
	
	@Override
	public String toString() {
		return "transposer.addExtractRecipe";
	}
}
