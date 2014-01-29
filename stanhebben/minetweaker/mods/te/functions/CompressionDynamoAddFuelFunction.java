package stanhebben.minetweaker.mods.te.functions;

import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.applyAction;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getFluid;
import static stanhebben.minetweaker.mods.te.ThermalExpansionUtil.getInt;
import net.minecraftforge.fluids.Fluid;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerValue;

public class CompressionDynamoAddFuelFunction extends TweakerFunction {
	public static final CompressionDynamoAddFuelFunction INSTANCE = new CompressionDynamoAddFuelFunction();
	
	private CompressionDynamoAddFuelFunction() {}
	
	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 2) {
			throw new TweakerExecuteException("adding a compression dynamo fuel requires 2 arguments");
		}
		
		Fluid fluid = getFluid(arguments, 0, "fuel");
		Integer energy = getInt(arguments, 1, "energy");
		
		applyAction("CompressionFuel", fluid.toString(),
				"fluidName", fluid.getName(),
				"energy", energy);
		
		return null;
	}
	
	@Override
	public String toString() {
		return "compressionDynamo.addFuel";
	}
}
