package stanhebben.minetweaker.base.values;

import net.minecraft.nbt.NBTBase;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerItemPattern;
import stanhebben.minetweaker.api.value.TweakerItemPatternOre;
import stanhebben.minetweaker.api.value.TweakerItemPatternOreAny;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.functions.OreAddFunction;
import stanhebben.minetweaker.base.functions.OreRemoveFunction;

public class OreValue extends TweakerValue {
	private String value;
	
	public OreValue(String value) {
		this.value = value;
	}
	
	@Override
	public TweakerItemPattern asItemPattern() {
		return new TweakerItemPatternOre(value);
	}
	
	@Override
	public Object asRecipeItem() {
		return value;
	}
	
	@Override
	public TweakerValue index(String index) throws TweakerExecuteException {
		switch (TweakerField.get(index)) {
			case ANY:
				return new TweakerItemPatternOreAny(value);
			case ITEMS:
				return new OreItemsValue(value);
			case ADD:
				return new OreAddFunction(value);
			case REMOVE:
				return new OreRemoveFunction(value);
		}
		throw new TweakerExecuteException("no member " + index + " in ore");
	}
	
	@Override
	public NBTBase toTagValue(String name) {
		return null;
	}

	@Override
	public String toString() {
		return "OreValue:" + value;
	}
}
