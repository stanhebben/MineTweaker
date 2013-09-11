package stanhebben.minetweaker.api.value;

import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.functions.FunctionItemStackPatternMatches;
import stanhebben.minetweaker.api.functions.FunctionItemStackPatternOnly;

public abstract class TweakerItemStackPattern extends TweakerValue {
	public abstract boolean matches(ItemStack stack);
	
	public abstract String toPatternString();
	
	@Override
	public TweakerItemStackPattern asItemStackPattern() {
		return this;
	}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ONLY:
				return new FunctionItemStackPatternOnly(this);
			case MATCHES:
				return new FunctionItemStackPatternMatches(this);
		}
		
		throw new TweakerExecuteException("No such member in stackpattern.matches");
	}
}
