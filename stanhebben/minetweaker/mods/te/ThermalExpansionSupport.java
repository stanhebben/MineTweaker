package stanhebben.minetweaker.mods.te;

import stanhebben.minetweaker.api.MineTweakerInterface;
import stanhebben.minetweaker.mods.te.values.ThermalExpansionValue;

public class ThermalExpansionSupport extends MineTweakerInterface {
	public static ThermalExpansionSupport INSTANCE = new ThermalExpansionSupport();
	
	public ThermalExpansionSupport() {
		super("thermalexpansion", ThermalExpansionValue.INSTANCE);
	}
}
