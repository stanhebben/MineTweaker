package stanhebben.minetweaker.base.values;


import net.minecraft.nbt.NBTBase;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.value.TweakerField;
import stanhebben.minetweaker.api.value.TweakerValue;
import stanhebben.minetweaker.base.functions.AddShapedFunction;
import stanhebben.minetweaker.base.functions.AddShapelessFunction;
import stanhebben.minetweaker.base.functions.RemoveRecipesFunction;
import stanhebben.minetweaker.base.functions.RemoveShapedFunction;
import stanhebben.minetweaker.base.functions.RemoveShapelessFunction;

public class RecipesValue extends TweakerValue {
	public static final RecipesValue INSTANCE = new RecipesValue();
	
	private RecipesValue() {}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ADDSHAPED:
				return AddShapedFunction.INSTANCE;
			case ADDSHAPELESS:
				return AddShapelessFunction.INSTANCE;
			case REMOVESHAPED:
				return RemoveShapedFunction.INSTANCE;
			case REMOVESHAPELESS:
				return RemoveShapelessFunction.INSTANCE;
			case REMOVE:
				return RemoveRecipesFunction.INSTANCE;
		}
		throw new TweakerExecuteException("No such member in recipes: " + index);
	}
	
	@Override
	public NBTBase toTagValue(String name) {
		return null;
	}

	@Override
	public String toString() {
		return "MineTweaker:recipes";
	}
}
