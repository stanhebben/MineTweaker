package stanhebben.minetweaker.mods.te.functions;

import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.applyAction;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getInt;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;

public class SawmillAddRecipeFunction extends TweakerFunction {
	public static final SawmillAddRecipeFunction INSTANCE = new SawmillAddRecipeFunction();
	
	private SawmillAddRecipeFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 3 || arguments.length > 5) {
			throw new TweakerExecuteException("adding a sawmill recipe requires 3-5 arguments");
		}
		
		Integer energy = getInt(arguments, 0, "energy");
		ItemStack input = getItemStack(arguments, 1, "input");
		ItemStack primaryOutput = getItemStack(arguments, 2, "primary output");
		ItemStack secondaryOutput = getItemStack(arguments, 3, "secondary output");
		Integer secondaryChance = getInt(arguments, 4, "secondary chance");
		
		applyAction("SawmillRecipe", input + " => " + primaryOutput,
				"energy", energy,
				"input", input,
				"primaryOutput", primaryOutput,
				"secondaryOutput", secondaryOutput,
				"secondaryChance", secondaryChance);
		
		return null;
	}
	
	@Override
	public String toString() {
		return "sawmill.addRecipe";
	}
}
