package stanhebben.minetweaker.api.functions;

import stanhebben.minetweaker.api.TweakerExecuteException;
import stanhebben.minetweaker.api.TweakerNameSpace;
import stanhebben.minetweaker.api.value.TweakerFunction;
import stanhebben.minetweaker.api.value.TweakerItemWithDamage;
import stanhebben.minetweaker.api.value.TweakerValue;

/**
 * Function instanced of item.withDamage. Creates an item with the specified
 * amount of damage applied to it.
 * 
 * @author Stan Hebben
 */
public class FunctionItemWithDamage extends TweakerFunction {
	private final int id;
	
	public FunctionItemWithDamage(int id) {
		this.id = id;
	}

	@Override
	public TweakerValue call(TweakerNameSpace namespace, TweakerValue... arguments) {
		if (arguments.length != 1) throw new TweakerExecuteException("item.withDamage requires 1 argument");
		int damage =
				notNull(arguments[0], "damage cannot be null")
				.toInt("damage must be an int").get();
		return new TweakerItemWithDamage(id, damage);
	}

	@Override
	public String toString() {
		return "item.withDamage";
	}
}
