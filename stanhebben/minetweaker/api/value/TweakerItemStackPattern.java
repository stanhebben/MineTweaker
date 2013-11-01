package stanhebben.minetweaker.api.value;

import java.util.List;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.functions.FunctionItemStackPatternMatches;
import stanhebben.minetweaker.api.functions.FunctionItemStackPatternOnly;

/**
 * Represents an item stack pattern.
 * 
 * @author Stan Hebben
 */
public abstract class TweakerItemStackPattern extends TweakerValue {
	/**
	 * Checks if this pattern matches the specified item.
	 * 
	 * @param stack the stack to be matched
	 * @return true if the stack matches this pattern
	 */
	public abstract boolean matches(ItemStack stack);
	
	/**
	 * Converts the item stack pattern to a string representing the pattern.
	 * 
	 * @return the pattern string
	 */
	public abstract String toPatternString();
	
	/**
	 * Returns the amount of items represented by this pattern. If no such 
	 * amount is available (e.g. the pattern matches any amount), 1 should be
	 * returned.
	 * 
	 * @return the stack size, or 1
	 */
	public abstract int getAmount();
	
	/**
	 * Returns the list of all matches for this item stack pattern.
	 * 
	 * @return item stack pattern matches
	 */
	public abstract List<TweakerItem> getMatches();
	
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
