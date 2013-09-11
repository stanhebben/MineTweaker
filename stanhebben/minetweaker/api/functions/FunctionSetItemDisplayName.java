package stanhebben.minetweaker.api.functions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItem;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 * Implements the setDisplayName method in items.
 * 
 * @author Stan Hebben
 */
public class FunctionSetItemDisplayName extends TweakerFunction {
	private TweakerItem item;
	
	public FunctionSetItemDisplayName(TweakerItem item) {
		this.item = item;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length == 0) {
			throw new TweakerExecuteException("At least one argument required for setDisplayName");
		} else if (arguments.length == 1) {
			item.setDisplayName(arguments[0].toBasicString());
		} else {
			item.setDisplayName(arguments[0].toBasicString(), arguments[1].toBasicString());
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "MineTweaker:item.setDisplayName";
	}
}
