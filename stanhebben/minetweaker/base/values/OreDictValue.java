package stanhebben.minetweaker.base.values;

import net.minecraft.nbt.NBTBase;
import stanhebben.minetweaker.api.value.TweakerValue;

public class OreDictValue extends TweakerValue {
	public static final OreDictValue INSTANCE = new OreDictValue();
	
	private OreDictValue() {}
	
	@Override
	public TweakerValue index(String index) {
		return new OreValue(index);
	}

	@Override
	public NBTBase toTagValue(String name) {
		return null;
	}

	@Override
	public String toString() {
		return "MineTweaker:oreDict";
	}
}
