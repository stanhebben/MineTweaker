package stanhebben.minetweaker.mods.te.functions;

import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.applyAction;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getInt;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;

public class FurnaceAddRecipeFunction extends TweakerFunction {
	public static final FurnaceAddRecipeFunction INSTANCE = new FurnaceAddRecipeFunction();
	
	private FurnaceAddRecipeFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 3) {
			throw new TweakerExecuteException("adding a furnace recipe requires 3 arguments");
		}
		
		Integer energy = getInt(arguments, 0, "energy");
		ItemStack input = getItemStack(arguments, 1, "input");
		ItemStack output = getItemStack(arguments, 2, "output");
		
		applyAction("FurnaceRecipe", input + " => " + output,
				"energy", energy,
				"input", input,
				"output", output);
		
		return null;
	}
	
	@Override
	public String toString() {
		return "furnace.addRecipe";
	}
}
