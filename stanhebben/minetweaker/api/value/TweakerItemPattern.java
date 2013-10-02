package stanhebben.minetweaker.api.value;

import java.util.Iterator;
import java.util.List;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.IPatternListener;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.functions.FunctionItemPatternMatches;
import stanhebben.minetweaker.api.functions.FunctionItemPatternOnly;

public abstract class TweakerItemPattern extends TweakerValue {
	public abstract boolean matches(TweakerItem item);
	
	public abstract boolean matches(ItemStack item);
	
	public abstract boolean matches(Object object);
	
	public abstract boolean matches(int id);
	
	public abstract boolean matches(int id, int meta);
	
	public abstract String toPatternString();

	public abstract List<TweakerItem> getMatches();
	
	public abstract void addListener(IPatternListener listener);
	
	public abstract void removeListener(IPatternListener listener);
	
	@Override
	public TweakerItemPattern asItemPattern() {
		return this;
	}
	
	@Override
	public TweakerItemStackPattern asItemStackPattern() {
		return new TweakerItemStackPatternFromItemPattern(this);
	}
	
	@Override
	public TweakerValue mul(TweakerValue value) {
		return new TweakerItemStackPatternMultiItemPattern(this, value.toBasicInt());
	}
	
	@Override
	public TweakerValue index(String index) {
		switch (TweakerField.get(index)) {
			case ONLY:
				return new FunctionItemPatternOnly(this);
			case ITEMS: {
				TweakerArrayValue result = new TweakerArrayValue();
				for (TweakerValue value : getMatches()) {
					result.add(value);
				}
				return result;
			}
			case MATCHES:
				return new FunctionItemPatternMatches(this);
		}
		throw new TweakerExecuteException("No such member in item pattern: " + index);
	}
}
