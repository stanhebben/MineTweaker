package stanhebben.minetweaker.mods.te.functions;

import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.applyAction;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getInt;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;

public class SmelterAddRecipeFunction extends TweakerFunction {
	public static final SmelterAddRecipeFunction INSTANCE = new SmelterAddRecipeFunction();
	
	private SmelterAddRecipeFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length < 4 || arguments.length > 6) {
			throw new TweakerExecuteException("adding a smelter recipe requires 4-6 arguments");
		}
		
		Integer energy = getInt(arguments, 0, "energy");
		ItemStack primaryInput = getItemStack(arguments, 1, "primary input");
		ItemStack secondaryInput = getItemStack(arguments, 2, "secondary input");
		ItemStack primaryOutput = getItemStack(arguments, 3, "primary output");
		ItemStack secondaryOutput = getItemStack(arguments, 4, "secondary output");
		Integer secondaryChance = getInt(arguments, 5, "secondary chance");
		
		applyAction("SmelterRecipe", primaryInput + " + " + secondaryInput + " => " + primaryOutput,
				"energy", energy,
				"primaryInput", primaryInput,
				"secondaryInput", secondaryInput,
				"primaryOutput", primaryOutput,
				"secondaryOutput", secondaryOutput,
				"secondaryChance", secondaryChance);
		
		return null;
	}
	
	@Override
	public String toString() {
		return "smelter.addRecipe";
	}
}
